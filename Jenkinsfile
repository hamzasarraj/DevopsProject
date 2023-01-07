pipeline {
    environment{
      registry="hamza1991/tpachatprojctbackend"
      registryCredential='darinpope-dockerhub'
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
        stage("Upload Jar  To Nexus") {
            steps {  
               nexusArtifactUploader artifacts: [ 
                 [ 
                    artifactId: 'tpAchatProject',  
                      classifier: '',  
                      file: 'target/tpAchatProject-1.0.jar',   
                      type: 'jar' 
                   ]  

            ],  
            credentialsId: 'nexus3', 
            groupId: 'com.esprit.examen', 
            nexusUrl: '192.168.43.20:8081', 
            nexusVersion: 'nexus3', 
            protocol: 'http', 
            repository: 'deploymentRepo',  
            version: '1.0' 


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
       
         
    }
}


