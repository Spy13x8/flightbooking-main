pipeline {
    agent any

    environment {
            JAR_NAME = 'flightBooking-0.0.1-SNAPSHOT.jar'
            DOCKER_IMAGE_NAME = 'sahil1318/flightbooking'  // Replace with your Docker Hub repo or desired image name
            DOCKER_IMAGE_TAG = 'latest' // or use build number or git commit hash for tagging
            EC2_USER = 'ec2-user'
            EC2_HOST = '35.174.123.219'
            WORKSPACE_DIR = 'C:/ProgramData/Jenkins/.jenkins/workspace/flightbooking'
            DOCKERFILE_DIR = 'flightbooking-main/Dockerfile flightbooking'
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
                    bat """
                    docker run --rm -u 0 ^
                        -v C:/ProgramData/Jenkins/.jenkins/workspace/flightbooking:/app ^
                        -w /app/flightbooking ^
                        maven:3.8.5-openjdk-17 mvn clean package -DskipTests
                    """
                }
            }
        }

        stage('Test') {
            steps {
                script {
                    bat """
                    docker run --rm -u 0 ^
                        -v C:/ProgramData/Jenkins/.jenkins/workspace/flightbooking:/app ^
                        -w /app/flightbooking ^
                        maven:3.8.5-openjdk-17 mvn test
                    """
                }
            }
        }

        stage('Build Docker Image') {
                    steps {
                        bat """
                        cd ${WORKSPACE_DIR}
                        docker build -t ${DOCKER_IMAGE_NAME}:${DOCKER_IMAGE_TAG} -f  ${DOCKERFILE_DIR}.
                        """
                    }
                }

        stage('Deploy and Run on EC2') {
            steps {
                withCredentials([file(credentialsId: 'flightbooking-key', variable: 'PEM_PATH')]) {
                    script {
                        bat """
                        for /f "delims=" %%u in ('whoami') do (
                            icacls "%PEM_PATH%" /inheritance:r
                            icacls "%PEM_PATH%" /grant:r "%%u:R"
                        )
                        """

                        bat """
                        scp -o StrictHostKeyChecking=no -i "%PEM_PATH%" ^
                            flightbooking/target/flightbooking-0.0.1-SNAPSHOT.jar ec2-user@35.175.140.93:~/
                        """

                        bat """
                        cmd /c ssh -o StrictHostKeyChecking=no -i "%PEM_PATH%" ${EC2_USER}@${EC2_HOST} ^
                                                       "docker pull ${DOCKER_IMAGE_NAME}:${DOCKER_IMAGE_TAG} && docker stop flightbooking || true && docker rm flightbooking || true && docker run -d --name flightbooking -p 9090:9090 ${DOCKER_IMAGE_NAME}:${DOCKER_IMAGE_TAG}"
                        """



                    }
                }


            }
        }
    }
}