pipeline {
  agent any
  stages {
    stage('build') {
      steps {
        sh 'mvn clean package'
      }
    }

  }
  tools {
    maven 'maven'
  }
}