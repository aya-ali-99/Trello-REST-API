Feature: Validating List APIs


  @CreateList @E2E @List
  Scenario: Verify if List is being successfully created using CreateListAPI and retrievable using GetListAPI
    Given create list payload with name "Test_List"
    When user calls "CreateListAPI" with "Post" http request
    Then the API call is success with status code 200
    And "name" in response body is "Test_List"
    And verify "list" exists that maps to "Test_List" using "GetListAPI"

  @UpdateList @E2E @List
  Scenario: Verify if List is being successfully updated using UpdateListAPI
    Given a list is created using CreateListAPI and new list name "UpdatedList"
    When user calls "UpdateListAPI" with "Put" http request
    Then the API call is success with status code 200
    And "name" in response body is "UpdatedList"
    And verify "list" exists that maps to "UpdatedList" using "GetListAPI"

  @ArchiveList @List
  Scenario: Verify if List is being successfully archived using ArchiveListAPI
    Given prepare archive request for list
    When user calls "ArchiveListAPI" with "Put" http request
    Then the API call is success with status code 200
    And verify the "list" is archived via "GetListAPI"

  @UnarchiveList @List
  Scenario: Verify if List is being successfully unarchived using UnarchiveListAPI
    Given a list is archived using ArchiveListAPI
    And prepare unarchive request for list
    When user calls "UnarchiveListAPI" with "Put" http request
    Then the API call is success with status code 200
    And verify the "list" is unarchived via "GetListAPI"