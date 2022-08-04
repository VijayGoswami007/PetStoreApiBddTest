Feature: An example

  @PetStore
  Scenario: Add a new pet to the store
    Given We have pet data as below
    |categoryId| CategoryName| petName| photoUrl |
    |1         | domestic    | dog    | http://www.example.com   |
    And we have following tags as below
    |id|name|
    |1 |puppy|
    When the pet is submitted to pet API with status 'available'
    Then the pet should be saved


  @PetStore
  Scenario: Retrieve pet from store
    Given We have pet id saved
    When the pet is requested from pet API
    Then the pet should be returned