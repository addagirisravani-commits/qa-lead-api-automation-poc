pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean install -DskipTests'
            }
        }

        stage('Run Tests') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Archive Reports') {
            steps {
                junit '**/target/surefire-reports/*.xml'
            }
        }
    }

    post {
        always {
            emailext (
                subject: "API Automation Pipeline Status: ${currentBuild.currentResult}",
                body: "Build finished with result: ${currentBuild.currentResult}",
                to: "addagirisravani@gmail.com"
            )
        }
    }
}
