pipeline {
    agent any

    parameters {
            string(name: 'Username', defaultValue: 'Ross', description: 'Who is running the build')
        }
    stages {   
        stage('Build') { 
                steps {
                    echo "Hello ${params.Username}, we are building $BUILD_TAG"
                    sh 'pwd'
                    // Builds and excludes tests
                    sh './gradlew build -x test'
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
        stage('Deploy to Jetty') {
            steps {
                parallel (
                    'Deploy 1': {
                        echo "Deploying $BUILD_TAG on Jetty"
                        sh './gradle jettyRunWar &'
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
            }
            post {
                failure {
                    echo "FAILURE IN DEPLOYMENT OF $BUILD_TAG"
                }
            }
        }
        stage('Exit') {
            steps {
                sh 'gradle jettyStop'
            }
            post {
                failure {
                    echo "FAILURE IN DEPLOYMENT OF $BUILD_TAG"
                }
            }
        }
    }
}
