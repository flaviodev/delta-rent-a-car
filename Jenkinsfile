import groovy.json.JsonSlurper

@NonCPS
def parseJsonText(String json) {
  def object = new JsonSlurper().parseText(json)
  if(object instanceof groovy.json.internal.LazyMap) {
      return new HashMap<>(object)
  }
  return object
}

pipeline {
    agent any
    tools {
        maven 'mvn3'
        jdk 'jdk8'
    }
    stages {
        
        stage('Checkout Git') {
           steps {
                git branch: 'master', url: 'https://github.com/flaviodev/delta-rent-a-car.git'
            }
        }  

        stage('Build Projeto') {
            steps {
                sh 'mvn clean compile -DskipTests'
            }
        }        
        
        stage('Testes') {
            steps {
                sh 'mvn test'
            }
        }  
        
        stage('Sonar') {
            steps {
                withCredentials([string(credentialsId: 'token-sonar-rancher', variable: 'TOKEN')]) {
                   sh 'mvn sonar:sonar -Dsonar.host.url=http://192.168.1.100:9000 -Dsonar.login=${TOKEN}';
                }
                script { 
                  def response = httpRequest 'http://192.168.1.100:9000/api/issues/search?severities=BLOCKER,CRITICAL&componentRoots=br.edu.faculdadedelta:delta-rent-a-car'

                  def issues = parseJsonText(response.content)

                  echo "Sonar total blocker and critical issues: "+ issues.total 

                  if(issues.total  > 0) {
                      mail (to: 'fdsdev@gmail.com',
                           subject: "Qualidade falhou '${env.JOB_NAME}' (${env.BUILD_NUMBER}) is waiting for input",
                           body: "Please go to ${env.BUILD_URL}.")
                     input 'Qualidade aprovada?'   
                  } 
               }
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
        
        stage('e-mail') {
            steps {
                 mail (to: 'fdsdev@gmail.com',
                       subject: "Job '${env.JOB_NAME}' (${env.BUILD_NUMBER}) is waiting for input",
                       body: "Please go to ${env.BUILD_URL}.");
           }
        }
    }
}
