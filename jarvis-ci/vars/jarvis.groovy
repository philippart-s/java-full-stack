
def tag(String userName, String password, long buildNumber) {
    blabla 'ghTag'
    /*sh "git remote set-url origin https://${userName}:${password}@github.com/${userName}/java-full-stack.git"
    sh "git config --global user.password \"${password}\""
    sh "git config --global user.name \"${userName}\""
    sh 'git config --global user.email "wildagsx@gmail.com"'
    sh 'git config -l'
    sh "git tag -a v\${buildNumber} -m \"🏷️ Release ${buildNumber}\""
    sh "git push origin v${buildNumber}"*/
}

def ghRelease(String userName, String token, String buildNumber) {
    echo 'ghRelease'
    /*sh "curl -X POST \
         https://api.github.com/repos/${userName}/java-full-stack/releases \
         -H \"Authorization: Bearer ${token}\" \
         -H \"Content-Type: application/json\" \
         -d \"{\"tag_name\": \"v${buildNumber}\", \"name\": \"Release ${buildNumber}\", \"draft\": false, \"prerelease\": false}\""

     */
}