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
       stage("SonarQube Analysis") {
          agent any  
           steps {
                  sh 'mvn clean verify sonar:sonar -Dsonar.projectKey=Keypipeline  -Dsonar.host.url=http://192.168.43.20:9000 -Dsonar.login=1359a62bebe49fb69d3d1c14cb2f739844f5deaa'
                  echo 'sonar static analysis done'
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
                 nexusUrl: '192.168.43.20:8081', nexusVersion: 'nexus2', protocol: 'http', repository: 'http://192.168.43.20:8081/repository/simpleapp-release/', version: '1.0'
            }
        }
       
         
    }
    }


