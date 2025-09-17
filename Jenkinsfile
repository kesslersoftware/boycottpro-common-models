// Jenkins Pipeline Template for Shared Java Libraries
// This template handles boycottpro-common-models and similar shared libraries

pipeline {
    agent any

    tools {
        maven 'Maven'
        jdk 'JDK-21'
    }

    parameters {
        choice(
            name: 'ENVIRONMENT',
            choices: ['dev', 'qa', 'ps', 'prod'],
            description: 'Target environment for library deployment'
        )
        booleanParam(
            name: 'SKIP_TESTS',
            defaultValue: false,
            description: 'Skip unit tests (emergency builds only)'
        )
    }

    environment {
        LIBRARY_NAME = "${env.JOB_NAME.replace('-pipeline', '')}"
        ENV = "${ENV}"
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

                    // Read version from pom.xml or use default versioning
                    try {
                        env.SEMANTIC_VERSION = sh(
                            script: "mvn help:evaluate -Dexpression=project.version -q -DforceStdout",
                            returnStdout: true
                        ).trim()
                    } catch (Exception e) {
                        env.SEMANTIC_VERSION = "1.0.${BUILD_NUMBER}"
                    }

                    env.BUILD_TIMESTAMP = sh(
                        script: 'date +"%Y%m%d-%H%M%S"',
                        returnStdout: true
                    ).trim()

                    echo "📝 Library version components:"
                    echo "   Semantic version: ${env.SEMANTIC_VERSION}"
                    echo "   Git commit: ${env.GIT_COMMIT_SHORT}"
                    echo "   Build number: ${env.BUILD_NUMBER}"
                    echo "   Timestamp: ${env.BUILD_TIMESTAMP}"
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
                        sh '''
                            export JAVA_HOME="${TOOL_JDK_21}"
                            export PATH="$JAVA_HOME/bin:$PATH"
                            mvn clean test jacoco:report
                        '''
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
                                export JAVA_HOME="${TOOL_JDK_21}"
                                export PATH="$JAVA_HOME/bin:$PATH"
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
                    # Set JAVA_HOME from Jenkins tool
                    export JAVA_HOME="${TOOL_JDK_21}"
                    export PATH="$JAVA_HOME/bin:$PATH"

                    # Build the library JAR
                    mvn clean package -DskipTests

                    # Verify the JAR was created
                    if [ ! -f target/*.jar ]; then
                        echo "ERROR: Library JAR not found!"
                        exit 1
                    fi
                    
                    # Create library package with semantic versioning
                    mkdir -p library-artifacts
                    cp target/*.jar library-artifacts/${LIBRARY_NAME}-${SEMANTIC_VERSION}.jar

                    # Copy source JAR if available
                    if [ -f target/*-sources.jar ]; then
                        cp target/*-sources.jar library-artifacts/${LIBRARY_NAME}-${SEMANTIC_VERSION}-sources.jar
                    fi

                    # Copy javadoc JAR if available
                    if [ -f target/*-javadoc.jar ]; then
                        cp target/*-javadoc.jar library-artifacts/${LIBRARY_NAME}-${SEMANTIC_VERSION}-javadoc.jar
                    fi

                    echo "✅ Library packaged with semantic version: ${SEMANTIC_VERSION}"
                '''

                archiveArtifacts artifacts: 'library-artifacts/*.jar', fingerprint: true
            }
        }

        stage('Install to Local Maven') {
            steps {
                sh '''
                    # Set JAVA_HOME from Jenkins tool
                    export JAVA_HOME="${TOOL_JDK_21}"
                    export PATH="$JAVA_HOME/bin:$PATH"

                    # Install to local Maven repository for other projects to use
                    mvn install -DskipTests

                    echo "✅ Library installed to local Maven repository"
                    echo "Other projects can now use this dependency"
                '''
            }
        }

        stage('Publish to Nexus') {
            steps {
                script {
                    // Create custom Maven settings for Nexus deployment
                    writeFile file: 'custom-settings.xml', text: '''<?xml version="1.0" encoding="UTF-8"?>
<settings>
  <mirrors>
    <mirror>
      <id>nexus-all</id>
      <mirrorOf>*</mirrorOf>
      <url>http://host.docker.internal:8096/repository/maven-public/</url>
    </mirror>
  </mirrors>
  <servers>
    <server>
      <id>nexus-releases</id>
      <username>admin</username>
      <password>admin123</password>
    </server>
    <server>
      <id>nexus-all</id>
      <username>admin</username>
      <password>admin123</password>
    </server>
  </servers>
</settings>'''

                    echo "📦 Publishing library to Nexus with dual versioning strategy..."
                }

                sh '''
                    export JAVA_HOME="${TOOL_JDK_21}"
                    export PATH="$JAVA_HOME/bin:$PATH"

                    # Get the main JAR file
                    MAIN_JAR=$(find target -name "*.jar" -not -name "*sources*" -not -name "*javadoc*" | head -1)

                    if [ -z "$MAIN_JAR" ]; then
                        echo "ERROR: Main JAR file not found"
                        exit 1
                    fi

                    echo "Publishing $MAIN_JAR with semantic version: ${SEMANTIC_VERSION}"

                    # 1. Publish with semantic version (e.g., 1.2.3)
                    mvn deploy:deploy-file \
                        -Dfile="$MAIN_JAR" \
                        -DgroupId=com.boycottpro \
                        -DartifactId=${LIBRARY_NAME} \
                        -Dversion=${SEMANTIC_VERSION} \
                        -Dpackaging=jar \
                        -DrepositoryId=nexus-all \
                        -Durl=http://host.docker.internal:8096/repository/maven-artifacts-${ENV}/ \
                        -s custom-settings.xml

                    echo "✅ Published ${LIBRARY_NAME}:${SEMANTIC_VERSION} to Nexus"

                    # 2. Publish with LATEST alias (overwrite previous LATEST)
                    mvn deploy:deploy-file \
                        -Dfile="$MAIN_JAR" \
                        -DgroupId=com.boycottpro \
                        -DartifactId=${LIBRARY_NAME} \
                        -Dversion=LATEST \
                        -Dpackaging=jar \
                        -DrepositoryId=nexus-all \
                        -Durl=http://host.docker.internal:8096/repository/maven-artifacts-${ENV}/ \
                        -s custom-settings.xml

                    echo "✅ Published ${LIBRARY_NAME}:LATEST alias to Nexus"

                    # Publish sources JAR if available
                    SOURCES_JAR=$(find target -name "*sources*.jar" | head -1)
                    if [ -n "$SOURCES_JAR" ]; then
                        # Semantic version sources
                        mvn deploy:deploy-file \
                            -Dfile="$SOURCES_JAR" \
                            -DgroupId=com.boycottpro \
                            -DartifactId=${LIBRARY_NAME} \
                            -Dversion=${SEMANTIC_VERSION} \
                            -Dpackaging=jar \
                            -Dclassifier=sources \
                            -DrepositoryId=nexus-all \
                            -Durl=http://host.docker.internal:8096/repository/maven-artifacts-${ENV}/ \
                            -s custom-settings.xml

                        # LATEST sources
                        mvn deploy:deploy-file \
                            -Dfile="$SOURCES_JAR" \
                            -DgroupId=com.boycottpro \
                            -DartifactId=${LIBRARY_NAME} \
                            -Dversion=LATEST \
                            -Dpackaging=jar \
                            -Dclassifier=sources \
                            -DrepositoryId=nexus-all \
                            -Durl=http://host.docker.internal:8096/repository/maven-artifacts-${ENV}/ \
                            -s custom-settings.xml

                        echo "✅ Published sources JARs"
                    fi

                    echo "🎯 Dual versioning strategy complete:"
                    echo "   Semantic: ${LIBRARY_NAME}:${SEMANTIC_VERSION}"
                    echo "   Alias: ${LIBRARY_NAME}:LATEST"
                    echo "🔗 View at: http://host.docker.internal:8096/#browse/browse:maven-artifacts-${ENV}"
                '''
            }
        }

        stage('Generate Documentation') {
            steps {
                sh '''
                    # Set JAVA_HOME from Jenkins tool
                    export JAVA_HOME="${TOOL_JDK_21}"
                    export PATH="$JAVA_HOME/bin:$PATH"

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
            echo "📦 JAR: ${LIBRARY_NAME}-${env.SEMANTIC_VERSION}.jar"
            echo "🏠 Installed to local Maven repository"
            echo "🚀 Published to Nexus repository with dual versioning:"
            echo "   Environment: ${ENV}"
            echo "   Semantic: ${LIBRARY_NAME}:${env.SEMANTIC_VERSION}"
            echo "   Alias: ${LIBRARY_NAME}:LATEST"
            echo "🔗 http://host.docker.internal:8096/#browse/browse:maven-artifacts-${ENV}"
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