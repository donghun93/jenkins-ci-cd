pipeline {
    agent any

    environment{
        registry = 'alswn4516/test'
        registryCredential = 'alswn4516'
    }
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

//         // docker build
//         stage('Bulid Docker') {
//           agent any
//           steps {
//             echo 'Bulid Docker'
//             script {
//                 dockerImage = docker.build imagename
//             }
//           }
//           post {
//             failure {
//               error 'This pipeline stops here...'
//             }
//           }
//         }
//
//         // docker push
//         stage('Push Docker') {
//           agent any
//           steps {
//             echo 'Push Docker'
//             script {
//                 docker.withRegistry('https://registry.hub.docker.com', registryCredential) {
//                     dockerImage.push("1.0")  // ex) "1.0"
//                 }
//             }
//           }
//           post {
//             failure {
//               error 'This pipeline stops here...'
//             }
//           }
//         }

            stages{
               stage('Building image') {
              steps{
                script {
                  dockerImage = docker.build registry + ":$BUILD_NUMBER"
                }
              }
            }
               stage('Deploy Image') {
              steps{
                 script {
                    docker.withRegistry( '', registryCredential ) {
                    dockerImage.push("1.0")
                  }
                }
              }
            }
    }
}