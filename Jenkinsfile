pipeline {
    agent any 
    stages {
       stage('compile') {
            steps {
                    withMaven(maven : 'apache-maven-3.5.3') {
                            sh 'mvn clean'
                    }       
                }                
            } 
       
        stage('test') {
            steps {
                    withMaven(maven : 'apache-maven-3.5.3') {
                            sh 'mvn test'
                  }                
            }         
        }
    }
}
