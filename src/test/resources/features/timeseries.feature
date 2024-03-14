@timeseries
Feature: Timeseries endpoint lets you query the API for daily historical rates between two dates of your choice, with a maximum time frame of 365 days

  @happy-paths
  Scenario Outline: As an authenticated user I can do the /timeseries request successfully
    Given the user creates a "TIMESERIES" request
    And a "valid_token" is added as a apikey to the request
    And the query "start_date" with value "<start_date>" is added to the request
    And the query "end_date" with value "<end_date>" is added to the request
    And the query "base" with value "<base>" is added to the request
    And the query "symbols" with value "<symbols>" is added to the request
    When the user executes "GET" on the request
    Then then request will have 200 status code

    Examples:
      | start_date | end_date   | base | symbols |
      | 2020-01-20 | 2020-01-23 | EUR  |         |
      | 2020-01-20 | 2020-01-23 |      | GBP,JPY |
      | 2020-01-20 | 2020-01-23 | EUR  | GBP,JPY |
      #Converting USD to USD works apparently even though the rate is always 1. Can be a defect
      | 2020-01-20 | 2020-01-23 | USD  | USD     |


  @unhappy-paths @401
  Scenario Outline: As an unauthenticated user I receive 401 when doing the /timeseries request
    Given the user creates a "TIMESERIES" request
    And a "<apikey>" is added as a apikey to the request
    And the query "start_date" with value "2021-01-20" is added to the request
    And the query "end_date" with value "2021-01-23" is added to the request
    When the user executes "GET" on the request
    Then then request will have 401 status code

    Examples:
      | apikey        |
      |               |
      | invalid_token |

  @unhappy-paths @404
  Scenario: As an unauthenticated user I receive 401 when doing the /timeseries request
    Given the user creates a "TIMESERIES" request
    And a "valid_token" is added as a apikey to the request
    And the query "start_date" with value "2020-01-20" is added to the request
    And the query "end_date" with value "2020-01-23" is added to the request
    And the query "base" with value "ZYX" is added to the request
    When the user executes "GET" on the request
    Then then request will have 404 status code

  @unhappy-paths @429
  Scenario: As an unauthenticated user I receive 401 when doing the /timeseries request
    Given the user creates a "TIMESERIES" request
    And a "limit_reached_token" is added as a apikey to the request
    And the query "start_date" with value "2021-01-20" is added to the request
    And the query "end_date" with value "2021-01-23" is added to the request
    When the user executes "GET" on the request
    Then then request will have 429 status code
