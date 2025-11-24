Feature: Validating Board APIs


  @CreateBoard @Board
  Scenario Outline: Verify if Board is being successfully created using CreateBoardAPI and retrievable using GetBoardAPI
    Given Create Board Payload with "<name>"
    When user calls "CreateBoardAPI" with "Post" http request
    Then the API call is success with status code 200
    And "name" in response body is "<name>"
    And verify board is created that maps to "<name>" using "GetBoardAPI"

  Examples:
    | name          |
    | RestTestBoard |



  @DeleteBoard @Board
  Scenario: Verify if Board is being successfully deleted using DeleteBoardAPI
    Given a board is created using CreateBoardAPI
    When user calls "DeleteBoardAPI" with "Delete" http request
    Then the API call is success with status code 200
    And verify the board with ID no longer exists via "GetBoardAPI" with expected status 404

#Feature: Validating Place APIs
#
#  @AddPlace @Regression
#  Scenario Outline: Verify if Place is being successfully added using AddPlaceAPI
#    Given Add Place Payload with "<name>" "<language> " "<address>"
#    When user calls "AddPlaceAPI" with "Post" http request
#    Then the API call is success with status code 200
#    And "status" in response body is "OK"
#    And "scope" in response body is "APP"
#    And verify place_id created that maps to "<name>" using "GetPlaceAPI"
#
#  Examples:
#    | name    | language | address            |
#    | AAhouse | English  | World cross center |
#    | BBhouse | Spanish  | Sea cross center   |
#
#
#  @DeletePlace @Regression
#  Scenario: Verify if Delete Place functionality is working
#    Given DeletePlace Payload
#    When user calls "DeletePlaceAPI" with "Post" http request
#    Then the API call is success with status code 200
#    And "status" in response body is "OK"