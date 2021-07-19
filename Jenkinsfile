pipeline {
    agent any

    environment {
        registry = "mkrishnap/springboot-jenkins"
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
                dockerImage = docker.build("mkrishnap/springboot-jenkins", ".")
            }
        }

        stage('Publish Docker Image') {
            steps {
                script {
                    docker.withRegistry( 'https://registry.hub.docker.com', 'dockerhub' ) {
                         dockerImage.push("${env.BUILD_NUMBER}")
                         echo "Pushing 2..."
                         // Push latest-tagged version
                         dockerImage.push("latest")
                         echo "Pushed!"
                    }
                }
            }
        }

        stage('Deploy') {
            steps {
                sh 'kubectl apply -f Kubernetes.yaml'
            }
        }
     }
}