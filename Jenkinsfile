pipeline {
    agent any

    stages {
        // git에서 repository clone
        stage('Prepare') {
          steps {
            echo 'Clonning Repository'
            git url: 'https://github.com/donghun93/jenkins-ci-cd.git',
              branch: 'main'
            }
            post {
             success {
               echo 'Successfully Cloned Repository'
             }
           	 failure {
               error 'This pipeline stops here...'
             }
          }
        }

        // gradle build
        stage('Bulid Gradle') {
          agent any
          steps {
            echo 'Bulid Gradle'
            dir ('.'){
                sh "chmod +x gradlew"
                sh """
                 ./gradlew clean build --exclude-task test
                """
            }
          }
          post {
            failure {
              error 'This pipeline stops here...'
            }
          }
        }

       stage('========== Build image ==========') {
          app = docker.build("alswn4516/test")
        }
        stage('========== Push image ==========') {
          docker.withRegistry('https://registry.hub.docker.com', 'alswn4516') {
            app.push("${env.BUILD_NUMBER}")
            app.push("latest")
          }
        }
    }
}