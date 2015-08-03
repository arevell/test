Feature: Execute load test
	
Scenario Outline: Load tests for the following parameters
	Given Number of threads <numberOfThreads>
	Given calls per thread <callsPerThread>
	Given calls period <callsPeriod>
	When I request for the upload tour info load test
	Then Test result is valid
	And Max load test duration <maxLoadTestDuration>
	And Average execution time <averageExecTime>
	

Examples:
        |numberOfThreads |callsPerThread |callsPeriod |maxLoadTestDuration |averageExecTime |
        |1               |1              |60000       |120000              |20000            |
        |2               |1              |60000       |120000              |20000            |
        |5               |1              |60000       |120000              |20000            |
        
        