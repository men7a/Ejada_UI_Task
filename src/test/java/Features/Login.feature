Feature: test the Login
  Scenario Outline: invalid Login
    Given open the website and go to login page
    When enter "<username>" and "<password>"
    Then a message displayed
  Examples:
    |    username   |   password    |
    | standard_user |   password    |
    |   username    |  secret_sauce |
    |   username    |    password   |

  Scenario: Valid Login
    Given open the website and go to login page
    When enter "standard_user" and "secret_sauce"
    Then go to home page
