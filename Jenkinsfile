pipeline {
    agent any

    environment {
        IMAGE = 'sahil1318/flightbooking:latest'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/nikunjchhatani/flightbooking_honor_vi_sem'
            }
        }

        stage('Build') {
            steps {
                script {
                    echo """
                    docker run --rm -v ${pwd()}:/app -w /app ${IMAGE} mvn clean package -DskipTests
                    """
                }
            }
        }

        stage('Test') {
            steps {
                script {
                    echo """
                    docker run --rm -v ${pwd()}:/app -w /app ${IMAGE} mvn test
                    """
                }
            }
        }
    }
}