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
                sh 'mvn sonar:sonar -Dsonar.host.url=http://192.168.1.100:9000 -Dsonar.login=1d56dbaa3dbc806238bdc82a31031ccf7b47de0b'
                input 'Qualidade aprovada?';   
            }
        }     
        
        stage('Push Nexus') {
            steps {
               withCredentials([string(credentialsId: 'fdsdev-nexus', variable: 'PASSWORD')]) {
                   sh 'rm -rf /var/jenkins_home/workspace/Delta\\ rent-a-car/.m2'
                   sh 'mkdir /var/jenkins_home/workspace/Delta\\ rent-a-car/.m2'
                   sh 'cp -f settings.xml /var/jenkins_home/workspace/Delta\\ rent-a-car/.m2';
                   sh 'mvn install -DskipTests -Dnexus.password=${PASSWORD}';
               }
            }
        }           
    }
}
