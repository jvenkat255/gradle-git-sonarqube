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
        checkout([$class: 'GitSCM', branches: [[name: '*/master']], 
        doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], 
        userRemoteConfigs: [[credentialsId: 'jvenkat255', url: 'https://github.com/jvenkat255/gradle-git-sonarqube.git']]])
        }
    }
    
 stage('Sonarqube') {
    environment {
        scannerHome = tool 'SonarQube_Scanner_2.8'
    }
    steps {
        withSonarQubeEnv('sonarqube') {
            //bat "${scannerHome}/bin/sonar-scanner"
            bat "gradlew sonarqube"
        }
        //timeout(time: 1, unit: 'MINUTES') {
          //  waitForQualityGate abortPipeline: true
        //}
	    stage("Quality Gate"){
          timeout(time: 2, unit: 'MINUTES') {
              def qg = waitForQualityGate()
              if (qg.status != 'OK') {
                  error "Pipeline aborted due to quality gate failure: ${qg.status}"
              }
          }
      }
    }
}
             
     
   
   
    
  }
 
   
} 
