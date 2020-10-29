pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
               echo 'Building project'
                     sh "mvn clean package"
            }
        }
    }
}