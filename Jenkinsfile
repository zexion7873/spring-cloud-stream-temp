pipeline {
  agent any
  stages {
    stage('commit') {
      steps {
        git(branch: 'master', url: 'https://github.com/zexion7873/spring-cloud-stream-temp', changelog: true, credentialsId: '21e7d6b42039aed910b7909b34188dfdb8c3fb7b', poll: true)
      }
    }

  }
}