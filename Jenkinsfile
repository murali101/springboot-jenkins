pipeline {
    agent any

    triggers {
        pollSCM '* * * * *'
    }

    stages {
            stage('Checkout') {
                steps { //Checking out the repo
                    git 'https://github.com/murali101/springboot-jenkins.git'
                    def gradleHome = tool 'gradle-711'
                    sh "'${gradleHome}/bin/gradle' --v"
                    sh "'${gradleHome}/bin/gradle' clean compileJava test"
                }
            }
      }
}