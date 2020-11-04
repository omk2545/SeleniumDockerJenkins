pipeline{
    agent any
    parameters {
        choice(name: 'SUITE_NAME', choices: ['search-module.xml', 'book-flight-module.xml'], description: 'Pick something')
    }
    stages {

        stage("Start Docker Compose") {
            steps {
                sh("/usr/local/bin/docker-compose up -d  --scale firefox=5")
            }
        }
        stage("Run Maven command") {
            steps {
                script {
                    sh("chmod +x testrun.sh && ./testrun.sh ${params.SUITE_NAME}")
                }
            }
        }

        stage("Bring Down Selenium Jar") {
            steps {
                sh("/usr/local/bin/docker-compose down")
            }
        }
    }
    post {
        always {
            archiveArtifacts artifacts: 'reports/current/**'
//            archiveArtifacts artifacts: 'target/surefire-reports/**'
//            archiveArtifacts extent : 'reports/current/**'

            publishHTML (target : [allowMissing: false,
                                   alwaysLinkToLastBuild: true,
                                   keepAll: true,
                                   reportDir: 'reports/current',
                                   reportFiles: 'index.html',
                                   reportName: 'Extent report',
                                   reportTitles: 'The Report'])

        }
    }
}




