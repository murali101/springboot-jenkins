pipeline {
    agent any

    triggers {
        pollSCM '* * * * *'
    }

    stages {
        stage('env setup') {
            steps{
                tools {
                    gradle 'gradle-711'
                }
            }
        }

        stage('Source') {
            steps {
                git 'https://github.com/murali101/springboot-jenkins.git'
            }
        }
        stage('Compile') {
            steps {
                sh 'gradle clean build'
            }
        }
     }
}