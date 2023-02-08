pipeline {
    agent any

    environment {
        repository = "alswn4516/test"  //docker hub id와 repository 이름
        DOCKERHUB_CREDENTIALS = credentials('docker_hub_user_credential2') // jenkins에 등록해 놓은 docker hub credentials 이름
        dockerImage = ''
    }
    stages {
        stage('chekcout') {
          steps {
            echo 'Clonning Repository'
            git branch: 'main',
                url: 'https://github.com/donghun93/jenkins-ci-cd.git',
                credentialsId: 'github_access_token_credential'
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
            success {
              echo 'Successfully Bulid Gradle'
            }
            failure {
              error 'This pipeline stops here...'
            }
          }
        }

        stage("Dockerize") {
            steps {
                sh "docker build -t $repository ."
                sh 'echo $DOCKERHUB_CREDENTIALS_PSW | docker login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin' // docker hub 로그인
                sh "docker push $repository"
                sh "docker rmi $repository"
            }
        }
    }
}