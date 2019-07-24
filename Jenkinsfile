pipeline {
	agent { label 'master'}
     options { 
    skipDefaultCheckout()
    disableConcurrentBuilds()
	     timestamps()
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
    
	    stage('Jacoco') {
		    steps {
			    bat "gradlew test jacocoTestReport"
			    
			    step([$class: 'JUnitResultArchiver', testResults: '**/build/test-results/test/*.xml'])
				
			    publishHTML ([
			    allowMissing: true,
			    alwaysLinkToLastBuild: false,
			    includes: '**/*.html',
			    keepAll: true,
			    reportDir: '**/build/reports/tests/test',
			    reportFiles: 'index.html',
			    reportName: 'Junit Report',
			    reportTitles: 'junit'
				])
					step([$class: 'JacocoPublisher', 
        				execPattern: '**/build/jacoco/*.exec',
        				classPattern: '**/build/classes',
      					sourcePattern: '**/src/main/java',
        				sourcePattern: '**/src'
        				])
			}//End post
		  }//End Jacoco
		    
		    
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
