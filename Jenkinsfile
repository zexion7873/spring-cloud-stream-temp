pipeline {
    agent any
    options {
        retry(3)
        timestamps()
        timeout(time: 1, unit: 'HOURS')
    }
    parameters {
        choice(name: 'CHOICE', choices: ['DEV', 'SIT', 'UAT', 'PROD'], description: 'Pick something')
    }
    stages {
        stage('Build') {
            steps {
                echo "Build Pass !"
            }
        }
        stage('Parallel Test') {
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
    		}
        }
        stage('Deploy') {
            input {
                message "Test Success Deploy ?"
                ok "Yes."
                submitter "alice,bob"
            }
            steps {
                echo "Deploy Success !"
            }
        }
    }
}