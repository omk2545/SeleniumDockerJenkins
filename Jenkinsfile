
pipeline{
agent any
    stages{
        stage("Set MVN path"){
            steps{
                echo 'Setting Maven Path '
                sh "source ~/.bash_profile"
                }
          }
        stage("Run Maven command"){
           steps{
                        sh('chmod +x testrun.sh && ./testrun.sh')
                  }
        }
    }
}