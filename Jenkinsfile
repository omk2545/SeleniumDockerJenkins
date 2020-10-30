
pipeline{
agent any
 parameters {
        choice(name: 'SUITE_NAME', choices: ['search-module.xml', 'book-flight-module.xml'], description: 'Pick something')
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
                script{
                        sh("chmod +x testrun.sh && ./testrun.sh ${params.SUITE_NAME}")
                     }
                }
        }
    }
}