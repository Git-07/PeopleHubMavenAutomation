node{
        stage('SCM Checkout') { 
                git 'https://github.com/Git-07/PeopleHubMavenAutomation' 
            }
        stage('Compile-Package') { 
            sh 'mvn package'
        }        
    }
