pipeline {
    agent any
    tools {
        gradle 'gradle-711'
    }

    triggers {
        pollSCM '* * * * *'
    }

    stages {
        stage('Source') {
            steps {
                git 'https://github.com/murali101/springboot-jenkins.git'
            }
        }
        stage('Assemble') {
            steps {
                sh 'gradle clean assemble'
            }
        }
        stage('Build') {
            steps {
                sh 'gradle clean build'
            }
        }
     }
     stages {
             stage('input stage') {
                 input {
                     message "Should we continue?"
                     ok "Yes, we should."
                     submitter "alice,bob"
                     parameters {
                         string(name: 'PERSON', defaultValue: 'Mr Jenkins', description: 'Who should I say hello to?')
                     }
                 }
                 steps {
                     echo "Hello, ${PERSON}, nice to meet you."
                 }
             }
         }
}