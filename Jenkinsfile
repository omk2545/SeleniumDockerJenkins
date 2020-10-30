
pipeline{
agent any
 parameters {
        choice(name: 'SUITE_NAME', defaultValue: 'search-module.xml', description: 'Enter a password')
    }
    stages{
        stage("Set MVN path"){
            steps{
                echo 'Setting Maven Path '
                sh "source ~/.bash_profile"
                }
          }
        stage("Run Maven command"){
           steps{
                echo "Running suite on Hello ${params.suiteName}"
                script{
                        sh('chmod +x testrun.sh && ./testrun.sh ${params.suiteName}')
                     }
                }
        }
    }
}