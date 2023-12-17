pipeline {
  agent any
   tools {
          maven "Maven"
      }
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
    stage('Build and Unit Tests') {
      steps {
        sh './mvnw clean verify'
      }
    }
    stage('SonarQube analysis') {
    steps {
            withSonarQubeEnv('Sonar') {
                script {
                    def scannerHome = tool name: 'Maven', type: 'maven'
                    def mvnHome = tool name: 'Maven', type: 'maven'

                    withEnv(["PATH+MAVEN=${mvnHome}/bin"]) {
                        sh "${scannerHome}/bin/sonar-scanner"
                    }
                }
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
