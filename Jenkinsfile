pipeline {
    agent any
    stages {
        stage('build') {
            steps {
                echo "start build project...."
                mvn clean install
            }
        }
    }
}
