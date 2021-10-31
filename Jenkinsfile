pipeline {
  agent any
  stages {
    stage('check') {
      steps {
        sh '''echo $JAVA_HOME
echo $MAVEN_HOME
'''
      }
    }

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