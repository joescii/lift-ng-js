@echo off

set JAVA_HOME=%JAVA7_HOME%

if "%1" == "" (goto USAGE) else goto PUBLISH

:USAGE
echo USAGE: publish.bat ^<ng-version^>
exit /b 1

:PUBLISH
echo %SBT_HOME%\bin\sbt "set ngVersion := \"%1\"" "< publish.txt"
exit /b 0
