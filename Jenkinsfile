pipeline {
    agent {
        docker {
            image 'maven:3-alpine'
            args  '--network host -v /var/jenkins_home/.m2:/root/.m2'
        }
    }
    
    stages {
        
        stage('Git') {
           steps {
                git branch: 'master', url: 'https://github.com/flaviodev/delta-rent-a-car.git'
            }
        }  

        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }        
        
        stage('Teste') {
            steps {
                sh 'mvn test'
            }
        }  
        
        stage('Sonar') {
            steps {
                sh 'mvn sonar:sonar -Dsonar.host.url=http://localhost:9000 -Dsonar.login=7b382a83406d742776aaab5ec10a15658319f0e7'
            }
        }     
        
        stage('Docker Image') {
            steps {
                sh 'cp -f /var/jenkins_home/.m2/settings.xml /var/jenkins_home/workspace/Delta\\ rent-a-car/\\?/.m2'                
                sh 'mvn install -DskipTests'
            }
        }   
        
        stage('Docker Push') {
            steps {
                sh 'mvn deploy -DskipTests'
            }
        }  
    }
}
