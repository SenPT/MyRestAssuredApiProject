Feature: Validating Product API

  Scenario: Verify if Product is being successfully added using AddProductAPI
    Given Add Product Payload
    When users call "AddProductAPI" with "Post" http request
    Then the API call got success with status code 200
    And "status" in response body is "OK"