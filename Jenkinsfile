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
              bat 'start cmd.exe'
                bat 'run.bat'
               return
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
