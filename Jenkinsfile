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
	      checkout([$class: 'GitSCM', branches: [[name: '*/cobertura']], 
        doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], 
        userRemoteConfigs: [[credentialsId: 'jvenkat255', url: 'https://github.com/jvenkat255/gradle-git-sonarqube.git']]])
        }
    }
    
	    stage('Cobertura') {
		    steps {
			    bat "gradlew test cobertura"
			    
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
			    
			//publishCoverage adapters: [coberturaAdapter(path: '**/build/reports/cobertura/coverage.xml', thresholds: [[thresholdTarget: 'Class', unstableThreshold: 80.0], [thresholdTarget: 'Method', unstableThreshold: 80.0], [thresholdTarget: 'Line', unstableThreshold: 80.0]])], failNoReports: true, failUnhealthy: true, sourceFileResolver: sourceFiles('NEVER_STORE')
			
			cobertura autoUpdateHealth: false, autoUpdateStability: false, coberturaReportFile: '**/build/reports/cobertura/coverage.xml', conditionalCoverageTargets: '70, 0, 0', failUnhealthy: false, failUnstable: false, lineCoverageTargets: '80, 0, 0', maxNumberOfBuilds: 0, methodCoverageTargets: '80, 0, 0', onlyStable: false, sourceEncoding: 'ASCII', zoomCoverageChart: false
			    
		// post {
        	//	always {
           	//		 junit '**/nosetests.xml'
            	//		 step([$class: 'CoberturaPublisher', autoUpdateHealth: false, autoUpdateStability: false, coberturaReportFile: '**/coverage.xml', failUnhealthy: false, failUnstable: false, maxNumberOfBuilds: 0, onlyStable: false, sourceEncoding: 'ASCII', zoomCoverageChart: false])
        	//		}
    		//}//End post
			    
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
