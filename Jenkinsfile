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
                      withSonarQubeEnv('Sonar') {
                              sh """
                                  ./mvnw sonar:sonar \
                                  -Dsonar.projectKey=Sonar \
                                  -Dsonar.host.url=http://192.168.1.55:9000/ \
                                  -Dsonar.login=squ_b4673ea432bf65de1c22ad3f6827268d5b084e22
                              """
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
