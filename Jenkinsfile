pipeline {
    agent any
    stages {
        stage('build') {
            steps {
                echo 'start build project....'
                sh '#!/bin/sh -l'
                sh 'mvn clean install'
            }
        }
    }
}
