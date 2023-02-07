pipeline {
    agent any

    stages {
        // git에서 repository clone
        stage('Prepare') {
          steps {
            echo 'Clonning Repository'
            git url: 'https://github.com/donghun93/jenkins-ci-cd.git',
              branch: 'main',
              credentialsId: 'github_access_token_credentials'
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

//         // gradle build
//         stage('Bulid Gradle') {
//           agent any
//           steps {
//             echo 'Bulid Gradle'
//             dir ('.'){
//                 sh """
//                  ./gradlew clean build --exclude-task test
//                 """
//             }
//           }
//           post {
//             failure {
//               error 'This pipeline stops here...'
//             }
//           }
//         }
        stage('build gradle') {
            steps {
                sh  './gradlew build'


                sh 'ls -al ./build'
            }
            post {
                success {
                    echo 'gradle build success'
                }

                failure {
                    echo 'gradle build failed'
                }
            }
        }


        // docker build
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
//                 docker.withRegistry( '', registryCredential) {
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
    }
}