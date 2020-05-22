Installation
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

1. Download and install JDK 9. (you need an Oracle account to get JDK 9, otherwise it will always redirect you to JDK 10+ )

2. Create an environmental variable in your `.bash_profile` that point to the installed JDK. (ex. export JAVA_HOME=$(/usr/libexec/java_home))

3. Download and install Apache Maven 3.5.2

4. Create an environmental variable in your `.bash_profile` that points to the installed Apache Maven (ex. export PATH=$PATH:/Users/dimitarmavrodiev/Documents/Maven/apache-maven-3.5.2/bin)

5. Download Chrome Driver

6. Create an environmental variable in your `.bash_profile` that points to the downloaded ChromeDriver path (ex. export PATH=$PATH:/Users/dimitarmavrodiev/Documents/Drivers/chrome)

7. Download Gecko Driver

8. Create an environmental variable in your `.bash_profile` that points to the downloaded GeckoDriver path (ex. export PATH=$PATH:/Users/dimitarmavrodiev/Documents/Drivers/firefox)

9. Create an environmental variable in your `.bash_profile` that is called BROWSER and it is used from the framework to check on what browser it should run the tests (ex. export BROWSER=firefox)

10. Create an environmental variable in your `.bash_profile` that is called URL and it is used from the framework to check what is the starting URL for the test set (ex. export URL=enter URL)

11. Create an environmental variable in your `.bash_profile` that is called ELEMENTS_PATH and it is used from the framework to check what file with elements' path to read from (ex. ELEMENTS_PATH=name.properties)




CodeShip
-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

#npm test
#npm run coverage
# Java Config
jdk_switcher home oraclejdk9
jdk_switcher use oraclejdk9
# Run Misha Automated Framework
rm -dfr .git
git config --global user.name "tangocodemobile"
git config --global user.email "tangocodemobile@gmail.com"
git config --global push.default simple
REMOTE_REPOSITORY=${REMOTE_REPOSITORY:?'You need to configure the REMOTE_REPOSITORY environment variable!'}
REMOTE_BRANCH=${REMOTE_BRANCH:?'You need to configure the REMOTE_BRANCH environment variable!'}
git clone ${REMOTE_REPOSITORY}
cd automationui
mvn test


S3
AWS Access Key ID: ****************L5IA
AWS Secret Access Key: ************************************ezy/
Region: us-east-1
Local Path: ./logs/performance/aprimo/
S3 Bucket: plsauto-testing-artifacts
ACL: private
