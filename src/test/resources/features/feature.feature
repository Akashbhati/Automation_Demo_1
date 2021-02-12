Feature: RideCell Demo

  Background: To Launch the browser
    Given Launch the browser "chrome"

  Scenario: Verify repositories in UI with API
    When I navigate to github django page
    Then I fetch all repositories in UI
    And I fetch all "/django/repos" repositories using API
    And I verify "name" of repositories in UI and API