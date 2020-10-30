pipeline {
    agent any
    stages {
        stage('Set Maven Path') {
            steps {
               echo 'Setting Maven Path '
                     sh "source ~/.bash_profile"
            }
        }
    stage('Run Maven Command'){
             steps {
                   echo 'Setting Maven Path '
                         sh "mvn clean package"
                }

    }
}