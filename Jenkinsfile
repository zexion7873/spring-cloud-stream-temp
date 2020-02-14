pipeline {
  agent any
  stages {
    stage('Build') {
      environment {
        testVal = 'aaa'
      }
      steps {
        echo 'Build Pass !'
      }
    }

    stage('Parallel Test') {
      when {
        anyOf {
          branch 'master'
          branch 'release'
        }

      }
      failFast true
      parallel {
        stage('Test A') {
          steps {
            echo 'Test A Pass'
          }
        }

        stage('Test B') {
          steps {
            echo 'Test B Pass'
          }
        }

        stage('Test C') {
          steps {
            echo 'Test C Pass'
          }
        }

      }
    }

    stage('Deploy') {
      input {
        message 'Test Success, To Deploy ?'
        id 'Yes.'
      }
      steps {
        echo 'Deploy Success !'
        echo "BUILD_ID : ${BUILD_ID}"
        echo "BUILD_NUMBER : ${BUILD_NUMBER}"
        echo "BUILD_TAG : ${env.BUILD_TAG}"
        echo "BUILD_URL : ${env.BUILD_URL}"
        echo "EXECUTOR_NUMBER : ${env.EXECUTOR_NUMBER}"
        echo "JAVA_HOME : ${env.JAVA_HOME}"
        echo "JENKINS_URL : ${env.JENKINS_URL}"
        echo "JOB_NAME : ${env.JOB_NAME}"
        echo "NODE_NAME : ${env.NODE_NAME}"
        echo "WORKSPACE : ${env.WORKSPACE}"
        echo "BRANCH_NAME : ${BRANCH_NAME}"
        echo "CHANGE_ID : ${env.CHANGE_ID}"
      }
    }

  }
  options {
    retry(1)
    timestamps()
    timeout(time: 1, unit: 'HOURS')
  }
}