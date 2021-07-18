pipeline {
    agent any

    def app

    tools {
        gradle 'gradle-711'
    }

    triggers {
        pollSCM '* * * * *'
    }

    stages {
        stage('Source') {
            steps {
                checkout SCM
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
                    app = docker.build("springboot-jenkins")
                }
            }
        }
        stage('Deploy Image') {
            steps {
                script {
                    docker.withRegistry( 'https://registry.hub.docker.com', 'dockerhub' ) {
                        app.push("${env.BUILD_NUMBER}")
                        app.push("latest")
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