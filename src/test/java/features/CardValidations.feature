Feature: Validating Card APIs


  @CreateCard @E2E @Card
  Scenario: Verify if Card is being successfully created using CreateCardAPI and retrievable using GetCardAPI
    Given create card payload with name "Test_Card"
    When user calls "CreateCardAPI" with "Post" http request
    Then the API call is success with status code 200
    And "name" in response body is "Test_Card"
    And verify "card" exists that maps to "Test_Card" using "GetCardAPI"


  @UpdateCard @E2E @Card
  Scenario: Verify if Card is being successfully updated using UpdateCardAPI
    Given a card is created using CreateCardAPI and new card name "UpdatedCard"
    When user calls "UpdateCardAPI" with "Put" http request
    Then the API call is success with status code 200
    And "name" in response body is "UpdatedCard"
    And verify "card" exists that maps to "UpdatedCard" using "GetCardAPI"

  @DeleteCard @Card
  Scenario: Verify if Card is being successfully deleted using DeleteCardAPI
    Given a card is created using CreateCardAPI
    When user calls "DeleteCardAPI" with "Delete" http request
    Then the API call is success with status code 200
    And verify the "card" with ID no longer exists via "GetCardAPI" with expected status 404