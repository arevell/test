echo off
cls
echo *****TESTS FOR CCAPI SOAP v1*****
echo.
echo.
D:
cd D:\Program Files (x86)\SmartBear\SoapUI-5.0.0\bin
testrunner C:\Users\Swi3tr\Documents\QA-ccapi-V1-soapui-project.xml -r -a -fC:\Users\Swi3tr\Desktop\Tests\v1\v1_%date% -S & pause
