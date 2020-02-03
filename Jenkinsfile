node{
        stage('SCM Checkout') {                 
                git 'https://github.com/Git-07/PeopleHubMavenAutomation' 
            }
        stage('Compile-Package') { 
                def mvnhome = tool name: 'apache-maven-3.5.3', type: 'maven'
                sh "${mvnhome}/bin/mvn package"
        }        
    }
