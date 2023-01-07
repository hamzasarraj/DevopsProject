pipeline {
    environment{
      registry="hamza1991/tpachatprojctbackend"
      registryCredential='hamza-dockerhub'
      dokerImage="tpachatprojctbackend"
 } 
    agent any
    stages {
        stage('Git Checkout') {
            steps {
               git branch: 'main', url: 'https://github.com/hamzasarraj/DevopsProject.git' 
                
            }
        }
        stage('Clean package') {
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }
        stage('Test') { 
            steps {
                sh 'mvn test' 
            }
     }
      stage('IntegrationTest') { 
            steps {
                sh 'mvn verify -DskipTests' 
            }
     }
     stage('Maven clean install') { 
            steps {
                sh 'mvn clean install' 
            }
     }
       
     stage("mvn Package") {
            steps {
                script {
                    sh "mvn package -DskipTests=true"
                }
            }
        }
       
    
        stage("docker build") {
                       steps{
                         script {
                             dockerImage = docker.build registry + ":$BUILD_NUMBER"
                       }
                 }
       }
       
        stage("docker push") {
           steps{
              script {
                docker.withRegistry( '', registryCredential ) {
                dockerImage.push()
             }
           }
       }
      }   
       stage("DockerHub login ") {
              steps{
                  sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u hamza1991 -p Hamzasarrajoacaatc'
            }
          }
          stage('Docker-compose file') {

              steps {
                   sh 'docker-compose up -d';
                    sh 'sleep 300'
              
             }  
        }
         
    }
}


