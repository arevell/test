Feature: Execute load test

  @Fetching_data_from_database_before_execution_of_test
  Scenario Outline: Load tests for the following parameters
    Given Number of threads <numberOfThreads>
    Given calls per thread <callsPerThread>
    Given calls period <callsPeriod>
    When I request for the get brochure load test
    Then Test result is valid
    And Max load test duration <maxLoadTestDuration>
    And Average execution time <averageExecTime>

    Examples: 
      | numberOfThreads | callsPerThread | callsPeriod | maxLoadTestDuration | averageExecTime |
      | 3               | 10             | 360000      | 400000              | 5000            |
      | 4               | 10             | 360000      | 400000              | 5000            |
      | 3               | 10             | 360000      | 400000              | 5000            |
      | 4               | 10             | 360000      | 400000              | 5000            |
      | 3               | 10             | 360000      | 400000              | 5000            |
