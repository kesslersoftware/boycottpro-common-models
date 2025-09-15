// Jenkins Pipeline Template for Shared Java Libraries
// This template handles boycottpro-common-models and similar shared libraries

pipeline {
    agent any

    tools {
        maven 'Maven'
        jdk 'JDK-21'
    }

    parameters {
        booleanParam(
            name: 'SKIP_TESTS',
            defaultValue: false,
            description: 'Skip unit tests (emergency builds only)'
        )
        booleanParam(
            name: 'PUBLISH_TO_NEXUS',
            defaultValue: false,
            description: 'Publish to local Nexus repository'
        )
    }

    environment {
        LIBRARY_NAME = "${env.JOB_NAME.replace('-pipeline', '')}"
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
                script {
                    env.GIT_COMMIT_SHORT = sh(
                        script: "git rev-parse --short HEAD",
                        returnStdout: true
                    ).trim()
                }
            }
        }

        stage('Test') {
            when {
                expression { !params.SKIP_TESTS }
            }
            steps {
                script {
                    try {
                        sh 'mvn clean test jacoco:report'
                        echo "✅ Tests and coverage completed successfully"
                    } catch (Exception e) {
                        echo "⚠️ Tests failed but continuing build: ${e.getMessage()}"
                        currentBuild.result = 'UNSTABLE'
                    }
                }
            }
            post {
                always {
                    script {
                        try {
                            junit testResultsPattern: 'target/surefire-reports/*.xml'
                            echo "✅ Test results published successfully"
                        } catch (Exception e) {
                            echo "⚠️ Failed to publish test results: ${e.getMessage()}"
                        }

                        try {
                            publishHTML([
                                allowMissing: true,
                                alwaysLinkToLastBuild: true,
                                keepAll: true,
                                reportDir: 'target/site/jacoco',
                                reportFiles: 'index.html',
                                reportName: 'JaCoCo Coverage Report'
                            ])
                            echo "✅ JaCoCo coverage report published"
                        } catch (Exception e) {
                            echo "⚠️ Failed to publish JaCoCo report: ${e.getMessage()}"
                        }
                    }
                }
            }
        }

        stage('SonarQube Analysis') {
            when {
                expression { !params.SKIP_TESTS }
            }
            steps {
                script {
                    try {
                        withSonarQubeEnv('Local-SonarQube') {
                            sh '''
                                mvn sonar:sonar \
                                    -Dsonar.projectKey=${LIBRARY_NAME} \
                                    -Dsonar.projectName="${LIBRARY_NAME}" \
                                    -Dsonar.projectVersion=${GIT_COMMIT_SHORT}
                            '''
                        }
                        echo "✅ SonarQube analysis completed successfully"
                    } catch (Exception e) {
                        echo "⚠️ SonarQube analysis failed but continuing build: ${e.getMessage()}"
                        currentBuild.result = 'UNSTABLE'
                    }
                }
            }
        }

        stage('Quality Gate (Informational Only)') {
            when {
                expression { !params.SKIP_TESTS }
            }
            steps {
                script {
                    try {
                        timeout(time: 3, unit: 'MINUTES') {
                            def qg = waitForQualityGate()
                            if (qg.status != 'OK') {
                                echo "⚠️ SonarQube Quality Gate failed: ${qg.status}"
                                echo "📊 This is informational only - build will continue"
                                currentBuild.result = 'UNSTABLE'
                            } else {
                                echo "✅ SonarQube Quality Gate passed"
                            }
                        }
                    } catch (Exception e) {
                        echo "⚠️ Quality Gate check failed but continuing: ${e.getMessage()}"
                        currentBuild.result = 'UNSTABLE'
                    }
                }
            }
        }

        stage('Build Library') {
            steps {
                sh '''
                    # Build the library JAR
                    mvn clean package -DskipTests

                    # Verify the JAR was created
                    if [ ! -f target/*.jar ]; then
                        echo "ERROR: Library JAR not found!"
                        exit 1
                    fi

                    # Create library package
                    mkdir -p library-artifacts
                    cp target/*.jar library-artifacts/${LIBRARY_NAME}-${GIT_COMMIT_SHORT}.jar

                    # Copy source JAR if available
                    if [ -f target/*-sources.jar ]; then
                        cp target/*-sources.jar library-artifacts/${LIBRARY_NAME}-${GIT_COMMIT_SHORT}-sources.jar
                    fi

                    # Copy javadoc JAR if available
                    if [ -f target/*-javadoc.jar ]; then
                        cp target/*-javadoc.jar library-artifacts/${LIBRARY_NAME}-${GIT_COMMIT_SHORT}-javadoc.jar
                    fi
                '''

                archiveArtifacts artifacts: 'library-artifacts/*.jar', fingerprint: true
            }
        }

        stage('Install to Local Maven') {
            steps {
                sh '''
                    # Install to local Maven repository for other projects to use
                    mvn install -DskipTests

                    echo "✅ Library installed to local Maven repository"
                    echo "Other projects can now use this dependency"
                '''
            }
        }

        stage('Publish to Nexus') {
            when {
                expression { params.PUBLISH_TO_NEXUS }
            }
            steps {
                sh '''
                    # Deploy to local Nexus repository
                    mvn deploy -DskipTests -DaltDeploymentRepository=nexus::default::http://localhost:8096/repository/maven-releases/

                    echo "✅ Library published to Nexus repository"
                '''
            }
        }

        stage('Generate Documentation') {
            steps {
                sh '''
                    # Generate javadoc
                    mvn javadoc:javadoc

                    # Create documentation package
                    mkdir -p documentation
                    if [ -d target/site/apidocs ]; then
                        cp -r target/site/apidocs documentation/
                        echo "📚 Javadoc generated successfully"
                    fi
                '''

                script {
                    try {
                        publishHTML([
                            allowMissing: true,
                            alwaysLinkToLastBuild: true,
                            keepAll: true,
                            reportDir: 'target/site/apidocs',
                            reportFiles: 'index.html',
                            reportName: 'Javadoc Documentation'
                        ])
                        echo "✅ Javadoc documentation published"
                    } catch (Exception e) {
                        echo "⚠️ Failed to publish Javadoc: ${e.getMessage()}"
                    }
                }
            }
        }
    }

    post {
        always {
            cleanWs()
        }
        success {
            echo "✅ Library build successful!"
            echo "📦 JAR: ${LIBRARY_NAME}-${env.GIT_COMMIT_SHORT}.jar"
            echo "🏠 Installed to local Maven repository"
            script {
                if (params.PUBLISH_TO_NEXUS) {
                    echo "🚀 Published to Nexus repository"
                }
            }
        }
        failure {
            emailext (
                subject: "FAILED: Library Build - ${LIBRARY_NAME}",
                body: "Shared library build failed for ${LIBRARY_NAME}. Check Jenkins for details.",
                to: "dylan@kesslersoftware.com"
            )
        }
    }
}