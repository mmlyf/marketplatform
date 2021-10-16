pipeline {
    agent any
    stages {
        stage('build') {
            steps {
                echo 'start build project....'
                sh 'bin/mvn clean package'
            }
        }
    }
}
