pipeline {
    agent any
    stages {
        stage('build') {
            steps {
                echo 'start build project....'
                sh './mvnw clean package'
            }
        }
    }
}
