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
            }
        }
    }
}