  Feature: Openweather API Test
  
  @NegativeTest @Regression @Post
    Scenario Outline: POST without API Key
    Given The Open weather station url is running
    When Register a weather station without an API key, body includs "<external_id>","<name>","<latitude>","<longitude>","<altitude>"
		 Examples: 
     |external_id | name											 | latitude | longitude | altitude|
     |DEMO_TEST001| Team Demo Test Station 001 |   33.33  | -122.43   |   222   |
     
  @Positive @Regression @Post
    Scenario Outline: POST with API Key then check DB using GET  	
  	# Post new stations
    When Register weather station with "<external_id>","<name>","<latitude>","<longitude>","<altitude>"

		# Get new station
		When Query the new created station and response body contain  "<external_id>","<name>","<latitude>","<longitude>","<altitude>"
    
    Examples: 
     |external_id | name											 | latitude | longitude | altitude|
     |DEMO_TEST001| Team Demo Test Station 001 |   33.33  | -122.43   |   222   |
     |DEMO_TEST002| Team Demo Test Station 002 |   44.44  | -122.44   |   111   |
     
 		