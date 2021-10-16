pipeline {
    agent any
    stages {
        stage('build') {
            steps {
                echo 'start build project....'
                sh '/usr/apache-maven-3.6.3/bin/mvn clean package'
            }
        }
    }
}
