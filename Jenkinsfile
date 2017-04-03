pipeline {
    agent any

    parameters {
            string(name: 'Username', defaultValue: 'Ross', description: 'Who is running the build')
        }
    stages {   
        stage('Build') { 
                steps {
                    echo "Hello ${params.Username}, we are building $BUILD_TAG"
                    // Builds and excludes tests
                    sh './gradlew clean build -x test'
                }
                post {
                    success {
                        echo "$BUILD_TAG SUCCESSFULLY BUILT"
                    }
                    failure {
                        echo "FAILURE IN BUILD $BUILD_TAG"
                    }
                }
            }
        stage('Start tomcat server') {
            steps {
                echo "Starting tomcat server"
                sh './gradlew startTomcat'
            }
        }
        stage('Deploy') {
            steps {
                parallel (
                    'Deploy to Tomcat': {
                        echo "Deploying $BUILD_TAG on Tomcat"
                        sh './gradlew deployToTomcat'
                    },
                    'Deploy 2': {
                        echo 'Deploy 2 ...'
                    }
                )
            }
            post {
                failure {
                    echo "FAILURE WHEN RUNNING BUILD $BUILD_TAG"
                }
            }
        }
        stage('Test') {
            steps {
                echo "Testing $BUILD_TAG"
                sh './gradlew test'
            }
            post {
                failure {
                    echo "FAILURE IN DEPLOYMENT OF $BUILD_TAG"
                }
            }
        }
        stage('Exit') {
            steps {
                echo "Stopping Tomcat Server"
                sh './gradlew stopTomcat'
                sh './gradlew cleanTomcatWebapps'
            }
            post {
                failure {
                    echo "FAILURE IN EXITING $BUILD_TAG"
                }
            }
        }
    }
}
