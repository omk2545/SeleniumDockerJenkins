pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
               echo 'Building project'
               withMaven {
                     sh "mvn clean package"
                   } // withMaven will discover the generated Maven artifacts, JUnit Surefire & FailSafe reports and FindBugs reports
            }
        }
    }
}