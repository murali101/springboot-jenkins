pipeline {
    agent any

    triggers {
        pollSCM '* * * * *'
    }

    withGradle {
        sh './gradlew build'
      }
}