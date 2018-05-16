
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
                    sh "java -cp /var/jenkins_home/workspace/Sonar\\ utils/target/sonar-utils-*-jar-with-dependencies.jar br.com.syncode.sonarutils.ValidadorDeMetasDeQualidade http://192.168.1.100:9000 br.edu.faculdadedelta:delta-rent-a-car ${USERNAME} ${PASSWORD}"
               }
               /* script { 
                  def response = httpRequest 'http://192.168.1.100:9000/api/issues/search?severities=BLOCKER,CRITICAL&componentRoots=br.edu.faculdadedelta:delta-rent-a-car'

                  def issues = readJSON text : response.content

                  if(issues.total  > 0) {
                      mail (to: 'fdsdev@gmail.com',
                           subject: "Qualidade falhou '${env.JOB_NAME}' (${env.BUILD_NUMBER}) is waiting for input",
                           body: "Please go to ${env.BUILD_URL}.")
                     input 'Qualidade aprovada?'   
                  } 
                    
                  response = httpRequest 'http://192.168.1.100:9000/api/measures/search_history?component=br.edu.faculdadedelta:delta-rent-a-car&metrics=coverage'  
                    
                  def historicoCobertura = readJSON text : response.content
                  
                  if(historicoCobertura.measures.size() > 0) {
                     def posicaoUltima = historicoCobertura.measures[0].history.size() - 1
                     def ultimaMetrica =  historicoCobertura.measures[0].history[posicaoUltima]
                     echo ultimaMetrica.value
                  }
               }
               */
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
        
        stage('Deploy Homolog') {
            steps {
             sshagent (credentials: ['homolog-ssh']) {
                sh 'ssh -o StrictHostKeyChecking=no -p 922 root@192.168.1.100';
                sh 'ls /etc/ssh';
                sh 'docker';
                sh 'exit';
              }
            }
        }
    }
}
