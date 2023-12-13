pipeline {
  agent any
  stages {
    stage('Checkout') {
      steps {
        checkout scm
      }
    }
    stage('Set Script Permissions') {
      steps {
        sh 'chmod +x mvnw'
      }
    }
    stage('Build and Test') {
      steps {
        sh './mvnw clean verify'
      }
    }
    stage('SonarQube analysis') {
      steps {
        withSonarQubeEnv('DevOpsTutoSonar') {
          sh 'mvn sonar:sonar'
        }
      }
    }
    stage('Package') {
      steps {
        sh './mvnw package -DskipTests'
      }
    }
  }
  post {
    always {
      junit testResults: 'target/surefire-reports/*.xml', allowEmptyResults: true
    }
  }
}
