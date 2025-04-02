def ghTag(String userName, String password, long buildNumber) {
    sh "git remote set-url origin https://${userName}:${password}@github.com/${userName}/java-full-stack.git"
    sh "git config --global user.password \"${password}\""
    sh "git config --global user.name \"${userName}\""
    sh 'git config --global user.email "wildagsx@gmail.com"'
    sh 'git config -l'
    sh "git tag -a v\${buildNumber} -m \"🏷️ Release ${buildNumber}\""
    sh "git push origin v${buildNumber}"
}

