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
                   input 'Qualidade aprovada?';   
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
                try{
                    mail bcc: '', body: msgSonarGateway +  "</br> Link <a href='${env.BUILD_URL}input'>${env.BUILD_URL}input</a>" , cc: '', charset: 'UTF-8', from: 'appflaviodev@gmail.com', mimeType: 'text/html', replyTo: '', subject: 'Análise Sonar', to: 'fdsdev@gmail.com'
                }catch(Exception e){
                    echo "Falha no envio de email"
                }            
            }
        }
    }
}
