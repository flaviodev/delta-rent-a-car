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
        
        stage('Tests & Install') {
           steps {
               sh 'mvn install'
            }
        }
        
        stage('Sonar') {
            steps {
                withSonarQubeEnv('sonar') {
                   sh 'mvn sonar:sonar'
               }
            }
        }   
        
        stage('Deploy') {
            steps {
                script {
                    sshPublisher(publishers: [sshPublisherDesc(configName: 'server-producao', 
                        transfers: [
                            sshTransfer(
                              sourceFiles: "target/*.jar,Dockerfile,build-and-run-docker.sh",
                              execCommand: "sudo chmod 777 build-and-run-docker.sh && ./build-and-run-docker.sh && rm -rf target && rm -rf Dockerfile && rm -rf build-and-run-docker.sh")
                        ])])
                }        
            }
        }        
    }   
}
