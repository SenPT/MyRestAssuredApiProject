Feature: Validating Product API
@AddProduct
  Scenario Outline: Verify if Product is being successfully added using AddProductAPI
    Given Add Product Payload with "<title>" and <price> and "<description>"
    When users call "AddProductAPI" with "Post" http request
    Then the API call got success with status code 201
    And verify productId created products to "<title>" using "GetProductAPI"

    Examples:
      | title    | price | description |
      | Laptop C | 14    | Laptop 2022 |

@DeleteProduct
    Scenario: Verify If Delete Place functionality is working
      When users call "DeleteProductAPI" with "Delete" http request
      Then the API call got success with status code 200