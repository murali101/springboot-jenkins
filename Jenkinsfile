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
        stage('Compile') {
            tools {
                gradle 'gradle-711'
            }
            steps {
                sh './gradlew clean build'
            }
        }
     }
}