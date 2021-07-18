pipeline {
    agent any

    environment {
        registry = "https://registry.hub.docker.com/mkrishnap"
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

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t .'
            }
        }

        stage('Deploy Image') {
            steps {
                script {
                    docker.withRegistry( 'https://registry.hub.docker.com', 'dockerhub' ) {
                         dockerImage.push()
                    }
                }
            }
        }
        stage('Clean Up') {
            steps {
                sh 'docker rmi $registry:$BUILD_NUMBER'
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