pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        // sh "mvn liquibase:update -Dliquibase.contexts=${BRANCH_NAME}"
        sh "mvn -DskipTests clean package -P ${BRANCH_NAME}"
        echo 'Build Pass !'
      }
    }

    stage('Parallel Test') {
      when {
        anyOf {
          branch 'sit'
          branch 'uat'
          branch 'prod'
        }

      }
      failFast true
      parallel {
        stage('Test A') {
          post {
            always {
              echo 'Test Report'
            }

          }
          steps {
            sh 'mvn test'
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

    stage('Check Deploy') {
      when {
        beforeInput true
        anyOf {
          branch 'sit'
          branch 'uat'
          branch 'prod'
        }

      }
      input {
        message 'Test Success, To Deploy ?'
        id 'Yes.'
      }
      steps {
        echo 'Check Deploy Pass'
        mail(subject: 'aaa', body: 'bb', to: 'kevin.lin@thinkpower-info.com')
      }
    }

    stage('Deploy') {
      steps {
        sh "/Users/linjingkai/Applications/apache-tomcat-8.5.51/bin/shutdown.sh"
        sleep(time:20, unit:"SECONDS")
        sh "cd /Users/linjingkai/Applications/apache-tomcat-8.5.51/webapps"
        sh "rm -f spring-cloud-stream-kafka.war"
        sh "cp ${WORKSPACE}/target/spring-cloud-stream-kafka.war /Users/linjingkai/Desktop/Build/${BRANCH_NAME}/"
        sh "/Users/linjingkai/Applications/apache-tomcat-8.5.51/bin/startup.sh"

        echo 'Deploy Success !'
        echo "BUILD_ID : ${BUILD_ID}"
        echo "BUILD_NUMBER : ${BUILD_NUMBER}"
        echo "BUILD_TAG : ${BUILD_TAG}"
        echo "BUILD_URL : ${BUILD_URL}"
        echo "EXECUTOR_NUMBER : ${EXECUTOR_NUMBER}"
        echo "JENKINS_URL : ${JENKINS_URL}"
        echo "JOB_NAME : ${JOB_NAME}"
        echo "NODE_NAME : ${NODE_NAME}"
        echo "WORKSPACE : ${WORKSPACE}"
        echo "BRANCH_NAME : ${BRANCH_NAME}"
        echo "CHANGE_ID : ${env.CHANGE_ID}"
      }
    }

  }
  tools {
    maven 'apache-maven-3.6.3'
  }
  options {
    retry(1)
    timestamps()
    timeout(time: 1, unit: 'HOURS')
  }
}