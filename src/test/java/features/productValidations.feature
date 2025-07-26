Feature: Validating Product API
@AddProduct
  Scenario Outline: Verify if Product is being successfully added using AddProductAPI
    Given Add Product Payload with "<title>" and <price> and "<description>" and "<category>" and <stock>
    When users call "AddProductAPI" with "Post" http request
    Then the API call got success with status code 201
    And verify productId created products to "title" with value "<title>" using "GetProductAPI"

    Examples:
      | title    | price | description | category   | stock|
      | Laptop C | 14.5  | Laptop 2022 | Technology | 5    |

  @UpdateProduct
  Scenario Outline: Verify if Product is being update successfully
    Given Update Product Payload with fields and values:
      | fields   | values   |
      | <field1> | <value1> |
      | <field2> | <value2> |
      | <field3> | <value3> |


    When users call "PatchProductAPI" with "Patch" http request
    Then the API call got success with status code 200
    And verify productId created products to "<field1>" with value "<value1>" using "GetProductAPI"

    Examples:
      | field1 | value1    | field2 | value2 | field3 | value3 |
      | title  | laptopABC | price  | 15.5   | desc   | test   |


  @DeleteProduct
    Scenario: Verify If Delete Place functionality is working
      When users call "DeleteProductAPI" with "Delete" http request
      Then the API call got success with status code 200