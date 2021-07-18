pipeline {
    agent any

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
            tools {
                gradle 'gradle-711'
            }
            steps {
                sh 'gradle clean assemble'
            }
        }
        stage('Build') {
            tools {
                gradle 'gradle-711'
            }
            steps {
                sh 'gradle clean build'
            }
        }
     }
}