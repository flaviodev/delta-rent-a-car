
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
                withSonarQubeEnv('sonar-rancher') {
                   withCredentials([string(credentialsId: 'token-sonar-rancher', variable: 'TOKEN')]) {
                      sh 'mvn sonar:sonar -Dsonar.host.url=http://192.168.1.100:9000 -Dsonar.login=${TOKEN}';
                   }
               }
                
               sleep time: 20, unit: 'SECONDS'
               withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'sonar-user',
                    usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']]) {
                    sh 'java -cp /var/jenkins_home/.m2/repository/br/com/syncode/sonar-utils/0.0.1-SNAPSHOT/sonar-utils-0.0.1-SNAPSHOT-jar-with-dependencies  br.com.syncode.sonarutils.ValidadorDeMetasDeQualidade http://192.168.1.100:9000 br.edu.faculdadedelta:delta-rent-a-car ${USERNAME} ${PASSWORD}'
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
    }
}
