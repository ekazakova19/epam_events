pipeline{
    agent any
    parameters{
        choice(name: 'RunTestStrategy', choices: ['local', 'remote'], description: 'If you choose local test will be run using local webdrivers, for remote - tests will be run on remote webdriver using selinoid')
        choice(name: 'Browser', choices: ['chrome', 'firefox'], description: 'Browser for test run')
    }

    tools {
        maven 'maven'
        jdk 'jdk'
    }
    stages {
        stage('Checkout'){
            steps{
                checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[credentialsId: 'git', url: 'https://github.com/ekazakova19/epam_events.git']]])
            }
        }
        stage('Prepare test environment'){
            when{
                expression { params.RunTestStrategy =='remote' }
            }
            steps{
                echo 'REMOTE RUN SELENOID'
                sh label: '', script: 'chmod +x cm'
                sh label: '', script: '''./RunSelenoid.sh'''
            }
        }
        stage('Run test'){
            steps{
                sh "mvn test -DrunTestStrategy=${params.RunTestStrategy} -Dbrowser=${params.Browser}"
            }
        }
    }
    post('Send email notification') {
        always {
            emailext body: '''${SCRIPT, template="groovy-text1.template"} TEST RESULTS
                                    total  - ${TEST_COUNTS, var="total"}
                                    failed - ${TEST_COUNTS, var="fail"}
                                    passed - ${TEST_COUNTS, var="pass"}
                                    skipped - ${TEST_COUNTS, var="skip"}''',
                    subject:"[JENKINS] "+"${currentBuild.currentResult}"+" $JOB_NAME #$BUILD_NUMBER",
                    to: 'al.kazakova93@gmail.com'
            allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
        }
    }
}
