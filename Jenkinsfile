
pipeline {
    agent any
    tools {
        maven 'mvn3'
        jdk 'jdk8'
    }
    stages {
        stage('Git & Build') {
           steps {
               git branch: 'master', url: 'https://github.com/flaviodev/delta-rent-a-car.git'
               sh 'mvn clean package -DskipTests'
            }
        }  
        
        stage('Testes') {
           steps {
               sh 'mvn test'
            }
        }
        
        stage('Sonar') {
            steps {
                withSonarQubeEnv('sonar-rancher') {
                   withCredentials([string(credentialsId: 'token-sonar-rancher', variable: 'TOKEN')]) {
                      sh 'mvn sonar:sonar -Dsonar.host.url=http://172.16.0.10:9000 -Dsonar.login=${TOKEN}';
                   }
               }
            }
        }           
    }
}


