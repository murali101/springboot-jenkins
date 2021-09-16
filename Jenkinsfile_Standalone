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
                sh 'docker build -t springboot-jenkins:latest .'

            }
        }

        stage('Publish Docker Image') {
            steps {
                sh 'docker login -u mkrishnap -p abcd@1234'
                sh 'docker tag springboot-jenkins mkrishnap/springboot-jenkins'
                sh 'docker push mkrishnap/springboot-jenkins'
            }
        }

        stage('Chart') {
                    steps {
                        sh 'helm create springboot-jenkins'
                        sh 'helm lint ./springboot-jenkins'
                        sh 'helm install --dry-run -name springboot-jenkins ./springboot-jenkins'
                    }
                }

        stage('Deploy') {
            steps {
                sh 'helm install -name springboot-jenkins ./springboot-jenkins'
            }
        }
     }
}