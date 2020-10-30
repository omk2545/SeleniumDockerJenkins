
pipeline{
agent any
 parameters {
        choice(name: 'SUITE_NAME', choices: ['search-module.xml', 'book-flight-module.xml'], description: 'Pick something')
    }
    stages{
        stage("Strat Docker Container"){
            steps{
                step([$class: 'DockerComposeBuilder', dockerComposeFile: 'docker-compose.yml', option: [$class: 'StartAllServices'], useCustomDockerComposeFile: true])            }
          }
        stage("Run Maven command"){
           steps{
                script{
                        sh("chmod +x testrun.sh && ./testrun.sh ${params.SUITE_NAME}")
                     }
                }
        }
    }
    post {
        always {
            archiveArtifacts artifacts: 'target/surefire-reports/**'
        }
    }

}