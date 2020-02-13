pipeline {
    agent any
    options {
        retry(3)
        timestamps()
        timeout(time: 1, unit: 'MINUTES')
    }
    stages {
        stage('Build') {
            steps {
                echo "Build Pass !"
            }
        }
        stage('Parallel Test') {
			when {
				anyOf {
					branch 'master'
					branch 'sit'
				}
			}
            failFast true
    		parallel {
    			stage('Test A') {
    				steps {
    					echo "Test A Pass"
    				}
    			}
    			stage('Test B') {
    				steps {
    					echo "Test B Pass"
    				}
    			}
    			stage('Test C') {
    				steps {
    					echo "Test C Pass"
    				}
    			}
    		}
        }
        stage('Deliver') {
            input {
        		message "Test Success, To Deliver ?"
        		ok "Yes."
        	}
            steps {
            	echo "Deliver Success !"    
            	echo "BUILD_ID : ${env.BUILD_ID}" 
            	echo "BUILD_NUMBER : ${env.BUILD_NUMBER}" 
            	echo "BUILD_TAG : ${env.BUILD_TAG}" 
            	echo "BUILD_URL : ${env.BUILD_URL}" 
            	echo "EXECUTOR_NUMBER : ${env.EXECUTOR_NUMBER}" 
            	echo "JAVA_HOME : ${env.JAVA_HOME}" 
            	echo "JENKINS_URL : ${env.JENKINS_URL}" 
            	echo "JOB_NAME : ${env.JOB_NAME}" 
            	echo "NODE_NAME : ${env.NODE_NAME}" 
            	echo "WORKSPACE : ${env.WORKSPACE}" 
            	echo "BRANCH_NAME : ${env.BRANCH_NAME}"
            	echo "CHANGE_ID : ${env.CHANGE_ID}"
            }
        }
    }
}