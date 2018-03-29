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
                sh 'mvn sonar:sonar -Dsonar.host.url=http://192.168.1.100:9000 -Dsonar.login=f113fbd6878f37ac884bf4ef0d4b39a56881d7c7'
                input 'Qualidade aprovada?';   
            }
        }     
        
        stage('Build Docker Image') {
            steps {
               withCredentials([string(credentialsId: 'fdsdev-docker-hub', variable: 'PASSWORD')]) {
                   sh 'cat settings.xml'; 
                   sh 'mvn package -DskipTests';
                   input 'Publicar imagem no docker hub?';
               
               } 
            }
        }   
    }
}
