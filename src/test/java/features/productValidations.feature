Feature: Validating Product API

  Scenario Outline: Verify if Product is being successfully added using AddProductAPI
    Given Add Product Payload
    When users call "AddProductAPI" with "Post" http request
    Then the API call got success with status code 200
    And verify productId created products to "<title>" using "GetProductAPI"

    Examples:
    |title|
    |Laptop C|

    Scenario: Verify If Delete Place functionality is working
      When users call "DeleteProductAPI" with "Delete" http request
      Then the API call got success with status code 200