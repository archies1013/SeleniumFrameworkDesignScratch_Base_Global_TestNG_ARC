
@tag
Feature: Purchase the order from Ecommerce Website
 I want to use this template for my feature file
  
  Background:
  Given I landed on Ecommerce page 
  
  
  @Regression
  Scenario: Positive test of submitting the order
    Given Logged in with username <username> and password <password>
    When I add product <productName> to cart
    And checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on Confirmation page



    Examples: 
      | username  									| password		| productName		|
      | sawantarchana110@gmail.com	| Mullen@123 	| ZARA COAT 3		|						
     
