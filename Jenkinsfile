stage "DEV-QA"

node {
  git url: 'https://github.com/biswas19/se441-qotd.git'
  //def gradleHome = tool 'Gradle 2.12'
  bat "${gradleHome}\\bin\\gradle.bat assemble uploadArchives"
  
  step([$class: 'ArtifactArchiver', artifacts: '**/*.war',fingerprint: true])
} 
