pipeline {
	agent { label 'ATG'}
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
        checkout([$class: 'GitSCM', branches: [[name: '*/$BRANCH_NAME']], 
        doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], 
        userRemoteConfigs: [[credentialsId: 'jvenkat255', url: 'prdsrc1:/git/repos/ecom/B2BCommerce.git']]])
        }
    }
    
 stage('Sonarqube') {
    environment {
        scannerHome = tool 'SonarQubeScanner'
    }
    steps {
        withSonarQubeEnv('http://localhost:9000') {
            bat "${scannerHome}/bin/sonar-scanner"
            bat "gradlew sonarqube"
        }
        timeout(time: 10, unit: 'MINUTES') {
            waitForQualityGate abortPipeline: true
        }
    }
}
             
     
   
   
    
  }
 
   
} 
