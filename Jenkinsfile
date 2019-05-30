pipeline {
	agent { label 'AKS'}
     options { 
    skipDefaultCheckout()
    disableConcurrentBuilds()
   }
	   
    stages {
    stage('Clear workspace') {
      steps {
        cleanWs()
      }
    }  
    stage('Source Checkout') {
      steps {
        checkout([$class: 'GitSCM', branches: [[name: '*/master']], 
        doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], 
        userRemoteConfigs: [[credentialsId: 'jvenkat255', url: 'https://github.com/jvenkat255/gradle-git-sonarqube.git']]])
        }
    }
    
 stage('Sonarqube') {
    
    steps {
        withSonarQubeEnv('sonarqube_atg') {
            //bat "${scannerHome}/bin/sonar-scanner"
            bat "gradlew sonarqube"
        }
	    sleep(time:1,unit:"MINUTES")
       
    }
 }
 stage('Quality Gate') {
            steps {
              timeout(time: 2, unit: 'MINUTES') {
                waitForQualityGate abortPipeline: true
             }
            }
          }
     
             
     
   
   
    
  }
 
   
} 
