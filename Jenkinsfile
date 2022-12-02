pipeline {
    agent any
    stages {
        stage('Git Checkout') {
            steps {
               git branch: 'main', url: 'https://github.com/hamzasarraj/DevopsProject.git' 
                
            }
        }
         stage('prepare package') {
            steps {
                sh 'mvn prepare-package'
            }
        }
        stage('Clean Build') {
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
     stage('Maven Build') { 
            steps {
                sh 'mvn clean install' 
            }
     }
     stage("mvn Pckage") {
            steps {
                script {
                    sh "mvn package -DskipTests=true"
                }
            }
        }
         
    }
    }


