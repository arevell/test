echo off
D:
cd D:\Program Files (x86)\SmartBear\SoapUI-5.0.0\bin
cls    
echo. ***** TESTS FOR CCAPI SOAP v3 *****
echo.
echo.      
testrunner C:\Users\Swi3tr\Documents\QA---ccapi-V3-soapui-project.xml -r -a -fC:\Users\Swi3tr\Desktop\Tests\v3\v3_%date% -S & pause
