@Label
Feature: Validating Label APIs


  @CreateLabel @E2E
  Scenario: Verify if Label is being successfully created using CreateLabelAPI and retrievable using GetLabelAPI
    Given create label payload with name "Test_Label" and color "purple"
    When user calls "CreateLabelAPI" with "Post" http request
    Then the API call is success with status code 200
    And "name" in response body is "Test_Label"
    And verify "label" exists that maps to "Test_Label" using "GetLabelAPI"


  @UpdateLabel @E2E
  Scenario: Verify if Label is being successfully updated using UpdateLabelAPI
    Given a label is created using CreateLabelAPI and new label name "UpdatedLabel"
    When user calls "UpdateLabelAPI" with "Put" http request
    Then the API call is success with status code 200
    And "name" in response body is "UpdatedLabel"
    And verify "label" exists that maps to "UpdatedLabel" using "GetLabelAPI"

  @DeleteLabel
  Scenario: Verify if Label is being successfully deleted using DeleteLabelAPI
    Given a label is created using CreateLabelAPI
    When user calls "DeleteLabelAPI" with "Delete" http request
    Then the API call is success with status code 200
    And verify the "label" with ID no longer exists via "GetLabelAPI" with expected status 404

