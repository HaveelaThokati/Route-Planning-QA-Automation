# Route_Planning_Automation

Selenium + Cucumber (BDD) + TestNG test automation framework.
Author: haveela

## Prerequisites
| Tool | Version | Check |
|------|---------|-------|
| JDK | 11 or higher (17 recommended) | `java -version` |
| Apache Maven | 3.6+ | `mvn -version` |
| Google Chrome | latest (driver auto-managed by WebDriverManager / Selenium Manager) | - |
| Internet | needed on first build (Maven Central) | - |

## Install / build
Windows:   run `setup.bat`
Mac/Linux: `chmod +x setup.sh && ./setup.sh`

Or manually:
    mvn clean install -DskipTests

## Configure
Edit `Resources`
(browser, Username, Password). Test data (emails/passwords) lives in the `.feature` files.
Database settings (optional) are in `Main/utils/DB_Connection.java`.

## Run
    mvn test

Single feature:
    mvn test -Dcucumber.features=src/test/resources/featurefiles/SA_SignIn.feature
Tag filter:
    mvn test -Dcucumber.filter.tags="@yourTag"

## Check for newer dependency versions
    mvn versions:display-dependency-updates
    mvn versions:display-plugin-updates

## Output
Extent / Spark reports and timeline: `test-output/`

## Layout
    pom.xml
    src/main/java/com/routeplanning/automation/driver/   WebDriver setup
    src/main/java/com/routeplanning/automation/page/     Page objects
    src/main/java/com/routeplanning/automation/utils/    Helpers (config, DB, utils)
    src/test/java/stepdefinitions/                       Step definitions
    src/test/java/apphooks/                              Hooks
    src/test/java/testrunner/TestRunner.java             TestNG + Cucumber runner
    src/test/resources/featurefiles/                     .feature files
    src/test/resources/config/                           Config properties

## Starting a new test
1. Add the app URL and login to `Resources`.
2. Write scenarios in `Resources`.
3. Add step definitions in `StepDefinitions` (package `stepdefinitions`).
4. Add page objects in `Main`.
5. Run `mvn test`. (With no feature files the run finishes with 0 tests.)
