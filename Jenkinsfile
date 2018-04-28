
pipeline {
    agent any
    tools {
        maven 'mvn3'
        jdk 'jdk8'
    }
    stages {
        stage('Build') {
           steps {
               git branch: 'master', url: 'https://github.com/flaviodev/delta-rent-a-car.git'
               sh 'mvn clean compile -DskipTests'
               sh 'mvn test'
            }
        }  
        
        stage('Sonar') {
            steps {
                withCredentials([string(credentialsId: 'token-sonar-rancher', variable: 'TOKEN')]) {
                   sh 'mvn sonar:sonar -Dsonar.host.url=http://192.168.1.100:9000 -Dsonar.login=${TOKEN}';
                }
               /* script { 
                  def response = httpRequest 'http://192.168.1.100:9000/api/issues/search?severities=BLOCKER,CRITICAL&componentRoots=br.edu.faculdadedelta:delta-rent-a-car'

                  def issues = readJSON text : response.content

                  if(issues.total  > 0) {
                      mail (to: 'fdsdev@gmail.com',
                           subject: "Qualidade falhou '${env.JOB_NAME}' (${env.BUILD_NUMBER}) is waiting for input",
                           body: "Please go to ${env.BUILD_URL}.")
                     input 'Qualidade aprovada?'   
                  } 
                    
                  response = httpRequest 'http://192.168.1.100:9000/api/measures/search_history?component=br.edu.faculdadedelta:delta-rent-a-car&metrics=coverage'  
                    
                  def historicoCobertura = readJSON text : response.content
                  
                  if(historicoCobertura.measures.size() > 0) {
                     def posicaoUltima = historicoCobertura.measures[0].history.size() - 1
                     def ultimaMetrica =  historicoCobertura.measures[0].history[posicaoUltima]
                     echo ultimaMetrica.value
                  }
               }
               */
               sh 'java -cp /var/jenkins_home/.m2/repository/br/com/flaviodev/sonar-util/0.0.1-SNAPSHOT/sonar-util-0.0.1-SNAPSHOT-jar-with-dependencies.jar  br.com.flaviodev.sonar.VerifySeverityIssues sonarConfig.yml'
               
            }
        }     
        
        stage('Push Nexus') {
            steps {
               withCredentials([string(credentialsId: 'fdsdev-nexus', variable: 'PASSWORD')]) {
                   sh 'cp -f settings.xml /var/jenkins_home/.m2';
                   sh 'mvn install -DskipTests -Dnexus.password=${PASSWORD}';
               }
            }
        }
    }
}
