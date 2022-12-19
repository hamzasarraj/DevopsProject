pipeline {
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
        stage("Deploy to nexus") {
            steps {
                nexusArtifactUploader artifacts: [[artifactId: 'tpAchatProject', 
                classifier: '', 
                file: 'target/tpAchatProject-1.0.jar', type: 'jar']],
                 credentialsId: 'nexus3', groupId: 'com.esprit.examen', 
                 nexusUrl: '192.168.43.20:8081', nexusVersion: 'nexus3', protocol: 'http',
                  repository: 'simpleapp-release', version: '1.0'
            }
        }
        stage("Build Docker image") {
            steps {
                script {
                    sh "docker build -t devopsproject/tpAchatProject-1.0 ."
                }
            }
        }
       
         
    }
    }


