Feature: Trendyol Case

  Scenario: Scenario1: Select Browser
    When I have opened "https://www.trendyol.com/" with "chrome" browser


  Scenario: Scenario2: Login
    When sign in as "trendyol@test199978.com" and "dsdsdsds"
    But sign up with "trendyol@test199978.com" and "dsdsdsds" if I'm not registered


  Scenario: Scenario3: Check Boutique Images
    When check boutique images on KADIN tab
    And check boutique images on ERKEK tab
    And check boutique images on COCUK tab
    And check boutique images on AYAKKABI/CANTA tab
    And check boutique images on SAAT/AKSESUAR tab
    And check boutique images on KOZMETIK tab
    And check boutique images on EV/YASAM tab
    And check boutique images on ELEKTRONIK tab
    And check boutique images on SUPERMARKET tab


  Scenario: Scenario4: Check Product Images
    When check product image on random boutique

  Scenario: Scenario5: Go to Detail of a Random Product and Add to Basket
    When go to detail of a random product
    And add to basket