class httpRequest {
    String body="";    
    String message="";    
    String cookie="";   
    String url="";   
    String userid="";
    String pw=""; 
    Integer statusCode;    
    boolean failure = false;
    
 
    def String msg() {
     return "Loading dhactions";
    }
    
    def parseResponse(HttpURLConnection connection){    
        this.statusCode = connection.responseCode;    
        this.message = connection.responseMessage;    
        this.failure = false;
        
        if(statusCode == 200 || statusCode == 201){    
            this.body = connection.content.text;//this would fail the pipeline if there was a 400    
        }else{    
            this.failure = true;    
            this.body = connection.getErrorStream().text;    
        }
        
        if (cookie.length() == 0)
        {            
         String headerName=null;
        
         for (int i=1; (headerName = connection.getHeaderFieldKey(i))!=null; i++) {
          if (headerName.equals("Set-Cookie")) {                  
            String c = connection.getHeaderField(i); 
            cookie += c + "; ";
          }
         }  
        }    
    }   
    
    def doGetHttpRequest(String requestUrl){    
        URL url = new URL(requestUrl);    
        HttpURLConnection connection = url.openConnection();    
       
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Cookie", cookie); 
        connection.doOutput = true;   
 
        //get the request    
        connection.connect();    
 
        //parse the response    
        parseResponse(connection);    
 
        if(failure){    
            error("\nGET from URL: $requestUrl\n  HTTP Status: $resp.statusCode\n  Message: $resp.message\n  Response Body: $resp.body");    
        }    
 
        this.printDebug("Request (GET):\n  URL: $requestUrl");    
        this.printDebug("Response:\n  HTTP Status: $resp.statusCode\n  Message: $resp.message\n  Response Body: $resp.body");    
    }  
 
    /**    
     * Gets the json content to the given url and ensures a 200 or 201 status on the response.    
     * If a negative status is returned, an error will be raised and the pipeline will fail.    
     */    
    def Object doGetHttpRequestWithJson(String requestUrl){    
        return doHttpRequestWithJson("", requestUrl, "GET");    
    } 
 
    /**    
     * Posts the json content to the given url and ensures a 200 or 201 status on the response.    
     * If a negative status is returned, an error will be raised and the pipeline will fail.    
     */    
    def Object doPostHttpRequestWithJson(String json, String requestUrl){    
        return doHttpRequestWithJson(json, requestUrl, "POST");    
    }    
 
    /**    
     * Posts the json content to the given url and ensures a 200 or 201 status on the response.    
     * If a negative status is returned, an error will be raised and the pipeline will fail.    
     */    
    def Object doPutHttpRequestWithJson(String json, String requestUrl){    
        return doHttpRequestWithJson(json, requestUrl, "PUT");    
    }
 
    /**    
     * Post/Put the json content to the given url and ensures a 200 or 201 status on the response.    
     * If a negative status is returned, an error will be raised and the pipeline will fail.    
     * verb - PUT or POST    
     */    
    def String enc(String p)
    {
     return java.net.URLEncoder.encode(p, "UTF-8");
    }
    
    def Object doHttpRequestWithJson(String json, String requestUrl, String verb){ 
          
        URL url = new URL(requestUrl);    
        HttpURLConnection connection = url.openConnection();    
 
        connection.setRequestMethod(verb);    
        connection.setRequestProperty("Content-Type", "application/json");   
        if (cookie.length() > 0)
          connection.setRequestProperty("Cookie", cookie); 
        connection.doOutput = true;    
 
        if (json.length() > 0)
        {
         //write the payload to the body of the request    
         def writer = new OutputStreamWriter(connection.outputStream);    
         writer.write(json);    
         writer.flush();    
         writer.close();    
        }
    
        //post the request    
        connection.connect();    
 
        //parse the response    
        parseResponse(connection);    
 
        if(failure){    
            error("\n$verb to URL: $requestUrl\n    JSON: $json\n    HTTP Status: $statusCode\n    Message: $message\n    Response Body: $body");
            return null;    
        }    
        
        def jsonSlurper = new groovy.json.JsonSlurper();
        return jsonSlurper.parseText(body);
        
  //      println("Request ($verb):\n  URL: $requestUrl\n  JSON: $json");    
  //      println("Response:\n  HTTP Status: $statusCode\n  Message: $message\n  Response Body: $body");      
    }
}
