Feature: Validating Checklist APIs


  @CreateChecklist @E2E @Checklist
  Scenario: Verify if Checklist is being successfully created using CreateChecklistAPI and retrievable using GetChecklistAPI
    Given create checklist payload with name "Test_Checklist"
    When user calls "CreateChecklistAPI" with "Post" http request
    Then the API call is success with status code 200
    And "name" in response body is "Test_Checklist"
    And verify "checklist" exists that maps to "Test_Checklist" using "GetChecklistAPI"


  @UpdateChecklist @E2E @Checklist
  Scenario: Verify if Checklist is being successfully updated using UpdateChecklistAPI
    Given a checklist is created using CreateChecklistAPI and new checklist name "UpdatedChecklist"
    When user calls "UpdateChecklistAPI" with "Put" http request
    Then the API call is success with status code 200
    And "name" in response body is "UpdatedChecklist"
    And verify "checklist" exists that maps to "UpdatedChecklist" using "GetChecklistAPI"

  @DeleteChecklist @Checklist
  Scenario: Verify if Checklist is being successfully deleted using DeleteChecklistAPI
    Given a checklist is created using CreateChecklistAPI
    When user calls "DeleteChecklistAPI" with "Delete" http request
    Then the API call is success with status code 200
    And verify the "checklist" with ID no longer exists via "GetChecklistAPI" with expected status 404