pipeline {
    agent any

    environment {
        IMAGE = 'sahil1318/flightbooking:latest'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/Spy13x8/flightbooking-main'
            }
        }

        stage('Build') {
            steps {
                script {
                    def workspace = pwd()
                    bat """docker run --rm -v "${workspace}:/app" -w /app ${IMAGE} mvn clean package -DskipTests"""
                }
            }
        }

        stage('Test') {
            steps {
                script {
                    def workspace = pwd()
                    bat """docker run --rm -v "${workspace}:/app" -w /app ${IMAGE} mvn test"""
                }
            }
        }
    }
}
