pipeline {
    agent {
        docker {
            image 'maven:3-alpine'
            args  '--network host'
        }
    }
    
    stages {
        
        stage('Checkout Git repository') {
           steps {
                git branch: 'master', url: 'https://github.com/flaviodev/delta-rent-a-car.git'
            }
        }  

        stage('Build') {
            steps {
                sh 'mvn clean install -DskipTests'
            }
        }        
        
        stage('Sonar') {
            steps {
                sh 'mvn sonar:sonar -Dsonar.host.url=http://localhost:9000 -Dsonar.login=7b382a83406d742776aaab5ec10a15658319f0e7'
            }
        }           
    }
}
