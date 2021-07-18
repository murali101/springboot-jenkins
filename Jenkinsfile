pipeline {
    agent any

    environment {
        registry = "https://registry.hub.docker.com"
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
                sh 'gradle bootBuildImage'
                sh 'docker build -t springboot-jenkins:1.0.0 .'
            }
        }

        stage('Deploy Image') {
            steps {
                script {
                    docker.withRegistry( 'https://registry.hub.docker.com', 'dockerhub' ) {
                         def app = docker.build("mkrishnap/springboot-jenkins", ".")
                         app.push("${env.BUILD_NUMBER}")
                         echo "Pushing 2..."
                         // Push latest-tagged version
                         app.push("latest")
                         echo "Pushed!"
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