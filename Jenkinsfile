pipeline {
    agent any

    environment {
        imagename = "mkrishnap/springboot-jenkins"
        registryCredential = 'dockerhub'
        dockerImage = ''
    }

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
        stage('Build Image') {
            steps {
                script {
                    dockerImage = docker.build imagename
                }
            }
        }
        stage('Deploy Image') {
            steps {
                script {
                    docker.withRegistry( '', registryCredential ) {
                        dockerImage.push("$BUILD_NUMBER")
                        dockerImage.push('latest')
                    }
                }
            }
        }

         stage('Input Details') {
            when {
                     branch 'production'
                 }
             input {
                 message "Should we continue?"
                 ok "Yes, we should."
                 submitter "admin"
                 parameters {
                     string(name: 'PERSON', defaultValue: '', description: 'Request Number?')
                 }
             }
             steps {
                 echo "Request approved with the number ${PERSON}"
             }
         }
     }
}