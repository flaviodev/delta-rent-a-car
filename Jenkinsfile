
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
                   sh 'mvn sonar:sonar;
               }
            }
        }           
    }
}


