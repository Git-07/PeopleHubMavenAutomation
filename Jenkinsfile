pipeline {
    agent any 
    stages {
    /*  stage('compile') {
            steps {
                    withMaven(maven : 'apache-maven-3.5.3') {
                            sh 'mvn clean compile'
                    }       
                }                
            } */
        stage('run the remote web driver') {
            steps{
             d:
            cd D:\software\jenkins_run
            java -jar selenium-server-standalone-2.40.0.jar -Dwebdriver.chrome.driver="chromedriver.exe"
            }
        }
        stage('test') {
            steps {
                    withMaven(maven : 'apache-maven-3.5.3') {
                
                            bat 'mvn test -PRegression'
                  }                
            }         
        }
    }
}
