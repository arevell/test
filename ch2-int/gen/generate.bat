rem wsimport -keep -verbose http://localhost:7072/prod-serv-web/api/systemStatus?wsdl
rem wsimport -B-XautoNameResolution -keep -verbose -p com.ttc.prodserv.api.tropics http://londvap1.corp.ttc:7779/tropics/TropicsBuildWS?wsdl
rem wsimport -keep -verbose -autoNameResolution http://londvap1.corp.ttc:7779/tropics/TropicsBuildWS?wsdl

wsimport -B-XautoNameResolution -Xnocompile -keep -verbose -p com.wsout.habs.itropicsbuildws http://lonhabs01.corp.ttc:8883/prod-serv-web-TRUNK-SNAPSHOT/api/TropicsBuildWS?wsdl