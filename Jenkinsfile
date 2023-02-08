pipeline {
    agent any

    environment {
        repository = "alswn4516/test"
        DOCKERHUB_CREDENTIALS = credentials('docker_hub_user_credential')
        dockerImage = ''
        SSH_CMD = 'ssh -i private-key.pem ubuntu@{15.164.219.133}'
        DOCKER = 'sudo docker'
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
                sh "$DOCKER build -t $repository ."
                sh "echo $DOCKERHUB_CREDENTIALS_PSW | $DOCKER login -u $DOCKERHUB_CREDENTIALS_USR --password-stdin" // docker hub 로그인
                sh "$DOCKER push $repository"
                sh "$DOCKER rmi $repository"
            }
        }

//         stage("Deploy to EC2") {
//             steps {
//                 sh "$SSH_CMD '$DOCKER pull $repository'"
//                 sh "$SSH_CMD '$DOCKER stop test-container || true'"
//                 sh "$SSH_CMD '$DOCKER rm test-container || true'"
//                 sh "$SSH_CMD '$DOCKER run --name test-container -p 8080:8080 $repository'"
//             }
//         }
    }
}