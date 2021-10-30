pipeline {
    agent any
    tools {
        maven 'maven'
        java 'java8'
    }
    stages {
        stage('build') {
            steps {
                sh 'mvn clean package'
            }
        }
    }
}
