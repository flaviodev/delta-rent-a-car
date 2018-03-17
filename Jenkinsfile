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
                mail (to: 'fdsdev@gmail.com',
                   subject: "Tarefa '${env.JOB_NAME}' (${env.BUILD_NUMBER}) está aguardando aprovação",
                   body: "Varificar a qualidade em http://localhost:9000 e para aprovar ${env.BUILD_URL}.");
                input 'Qualidade aprovada?';   
            }
        }     
        
        stage('Build Docker Image') {
            steps {
                sh 'cp -f /var/jenkins_home/.m2/settings.xml /var/jenkins_home/workspace/Delta\\ rent-a-car/\\?/.m2'                
                sh 'mvn package -DskipTests'
                mail (to: 'fdsdev@gmail.com',
                   subject: "Tarefa '${env.JOB_NAME}' (${env.BUILD_NUMBER}) está aguardando aprovação",
                   body: "Para aprovar ${env.BUILD_URL}.");
                input 'Publicar imagem no docker hub?';                  
            }
        }   
        
        stage('Push DockerHub') {
            steps {
                sh 'mvn install -DskipTests'
            }
        }         
    }
}
