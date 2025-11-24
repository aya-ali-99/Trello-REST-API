Feature: Validating Board APIs


  @CreateBoard @Board
  Scenario Outline: Verify if Board is being successfully created using CreateBoardAPI and retrievable using GetBoardAPI
    Given Create Board Payload with "<name>"
    When user calls "CreateBoardAPI" with "Post" http request
    Then the API call is success with status code 200
    And "name" in response body is "<name>"
    And verify board exists that maps to "<name>" using "GetBoardAPI"

  Examples:
    | name          |
    | RestTestBoard |

  @UpdateBoard @Board
  Scenario: Verify if Board is being successfully updated using UpdateBoardAPI
    Given a board is created using CreateBoardAPI and new board name "UpdatedBoard"
    When user calls "UpdateBoardAPI" with "Put" http request
    Then the API call is success with status code 200
    And "name" in response body is "UpdatedBoard"
    And verify board exists that maps to "UpdatedBoard" using "GetBoardAPI"

  @DeleteBoard @Board
  Scenario: Verify if Board is being successfully deleted using DeleteBoardAPI
    Given a board is created using CreateBoardAPI
    When user calls "DeleteBoardAPI" with "Delete" http request
    Then the API call is success with status code 200
    And verify the board with ID no longer exists via "GetBoardAPI" with expected status 404

