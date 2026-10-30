@echo off
echo Checking Java...
java -version || (echo Java JDK 11+ is required & exit /b 1)
echo Checking Maven...
call mvn -version || (echo Apache Maven is required & exit /b 1)
call mvn clean install -DskipTests
echo.
echo Done. Run tests with: mvn test
pause
