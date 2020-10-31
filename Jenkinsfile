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
}
post {
    always {
        archiveArtifacts artifacts: 'target/surefire-reports/**'
    }
}




