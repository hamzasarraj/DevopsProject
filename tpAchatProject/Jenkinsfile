pipeline {
    agent any
    tools {
        maven "MAVEN"
        jdk "JDK"
    }
    stages {
        stage('Initialize'){
            steps{
                echo "PATH = ${M2_HOME}/bin:${PATH}"
                echo "M2_HOME = /opt/maven"
            }
        }
        stage('Git Checkout') {
            steps {
               git branch: 'main', url: 'https://github.com/hamzasarraj/DevopsProject.git' 
                
            }
        }
     }
}