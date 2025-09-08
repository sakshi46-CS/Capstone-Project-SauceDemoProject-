pipeline {
    agent any

    tools {
        jdk 'Java-17'          // Must match Jenkins JDK name
        maven 'Maven-3.9.11'   // Must match Jenkins Maven name
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/likhi-th123/SauceDemoAutomationProject.git'
            }
        }

        stage('Build & Test') {
            steps {
                // Build dependencies and run tests
                bat 'mvn clean test -Dheadless=true'
            }
        }
    }

    post {
        always {
            // Archive all test-output files
            archiveArtifacts artifacts: 'test-output/**', fingerprint: true

            // Publish Extent Report
            publishHTML([
                allowMissing: false,
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: 'test-output',
                reportFiles: 'dashboard.html',
                reportName: 'Extent Report'
            ])
        }
    }
}

