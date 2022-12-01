pipeline {
    agent any
    stages {
        stage('Git Checkout') {
            steps {
               git branch: 'main', url: 'https://github.com/hamzasarraj/DevopsProject.git' 
                
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
     stage('Sonarqube analysis') { 
            steps {
                mvn clean verify sonar:sonar -Dsonar.projectKey=DevopsProject -Dsonar.host.url =http://192.168.109.151:9000 -Dsonar.login = 5ca807a1504a9aa1b2831b618f98b802e30bca71
               }
     }
}
}
