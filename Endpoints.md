HTML Views
Home Page:

URL: http://localhost:8080/
Description: Returns the "index" view with the first 20 electric vehicles.
Statistics Page:

URL: http://localhost:8080/statistics
Description: Returns the "statistics" view.
Map Page:

URL: http://localhost:8080/map
Description: Returns the "map" view.
API Endpoints (JSON Responses)
Total Vehicles:

URL: http://localhost:8080/statistics/total
Description: Returns the total number of electric vehicles.
Vehicle Type Distribution:

URL: http://localhost:8080/statistics/type-distribution
URL: http://localhost:8080/api/type-distribution
Description: Returns a map of vehicle types and their counts.
Average Electric Range:

URL: http://localhost:8080/statistics/average-range
Description: Returns the average electric range of all vehicles.
Manufacturer Distribution:

URL: http://localhost:8080/statistics/manufacturer-distribution
Description: Returns a map of manufacturers and their vehicle counts.
Search Vehicles by Make and Model:

URL: http://localhost:8080/search
Description: Returns a list of electric vehicles filtered by make and model (optional parameters).
Parameters: make (optional), model (optional)
Paginated List of Vehicles:

URL: http://localhost:8080/api/vehicles
Description: Returns a paginated list of electric vehicles.
Parameters: page (default 0), size (default 100)
Vehicle Count by County and State:

URL: http://localhost:8080/api/statistics/vehicle-count-by-location
Description: Returns a nested map of vehicle counts grouped by state and county.
Average Range by Make and Model:

URL: http://localhost:8080/api/statistics/average-range-by-make-model
Description: Returns the average electric range for each vehicle make and model.
Registration Trends by Year:

URL: http://localhost:8080/api/statistics/registration-trends
Description: Returns the count of electric vehicle registrations by year.
CAFV Eligibility Summary:

URL: http://localhost:8080/api/statistics/cafv-eligibility-summary
Description: Returns a summary of vehicles based on Clean Alternative Fuel Vehicle (CAFV) eligibility.
Vehicle Distribution Heatmap Data:

URL: http://localhost:8080/api/statistics/vehicle-distribution-heatmap
Description: Returns a list of locations with vehicle counts for heatmap plotting.
Search Vehicles by Year and Make:

URL: http://localhost:8080/api/vehicles/search
Description: Returns a list of electric vehicles filtered by year and make.
Parameters: year (required), make (required)
Manufacturer Statistics:

URL: http://localhost:8080/api/statistics/manufacturer-stats
Description: Returns statistics for each manufacturer, including the number of models and average MSRP.