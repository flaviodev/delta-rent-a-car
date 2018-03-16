pipeline {
    agent {
        docker {
            image 'maven:3-alpine'
            args  '--network host -v /var/jenkins_home/.m2:/root/.m2'
        }
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
                sh 'mvn sonar:sonar -Dsonar.host.url=http://localhost:9000 -Dsonar.login=7b382a83406d742776aaab5ec10a15658319f0e7'
            }
        }     
        
        stage('Build Docker Image') {
            steps {
                sh 'cp -f /var/jenkins_home/.m2/settings.xml /var/jenkins_home/workspace/Delta\\ rent-a-car/\\?/.m2'                
                sh 'mvn package -DskipTests'
            }
        }   
        
        stage('Push DockerHub') {
            steps {
                sh 'mvn install -DskipTests'
            }
        }  
        
        stage('Deploy Homolog') {
           agent {
              docker {
                image 'fdsdev/delta-rent-a-car'
                args  '-d -p 8888:8888 --net host'
              } 
           }
        }         
    }
}
