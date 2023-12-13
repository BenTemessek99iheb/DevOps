pipeline {
  // This pipeline requires the following plugins:
  // * Git: https://plugins.jenkins.io/git/
  // * Workflow Aggregator: https://plugins.jenkins.io/workflow-aggregator/
  // * JUnit: https://plugins.jenkins.io/junit/
  agent 'any'
  stages {
  stage('Set Script Permissions') {
              steps {
                  sh 'chmod +x mvnw'
              }
          }
   stage('SonarQube analysis') {
              steps {
                  withSonarQubeEnv('My SonarQube Server') {
                    sh 'mvn clean verify sonar:sonar'
                          }
                      }
                  }
    stage('Checkout') {
      steps {
        script {
            checkout([$class: 'GitSCM', branches: [[name: '*/main']], userRemoteConfigs: [[url: 'https://github.com/BenTemessek99iheb/DevOps.git']]])
        }
      }
    }
    stage('Test') {
      steps {
        sh(script: './mvnw --batch-mode -Dmaven.test.failure.ignore=true test')

      }
    }
    stage('Package') {
      steps {
        sh(script: './mvnw --batch-mode package -DskipTests')
      }
    }
  }
  post {
    always {
      junit(testResults: 'target/surefire-reports/*.xml', allowEmptyResults : true)
    }
  }
}
