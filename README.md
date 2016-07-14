# Module Capstone - National Park Reservations

 Congratulations! You did such a great job on your previous site we want you back! 
 We are tasking you to build a web application that our National Park Service can use to book campsite reservations.
 
## Requirements

1. The site that you develop will need to have a consistent look and feel across all pages.
2. The home page should display a list of the available national parks in the reservation system.
    1. Each national park listed should include a picture, the name of the park, its location, the number of visitors, and a description.
    2. When a user proceeds to begin making a reservation for a national park, they click on the name of the park or its image and are taken to a page displaying the park’s campgrounds.
3. Every national park in the system contains one or more campgrounds. The campground page displays a list of campgrounds for the selected national park.
    1. Include the name of each campground, which months it is open (01 - January through 12 - December), a daily visitor fee in USD, and the number of campsites the campground contains.
    2. For each campground listing provide a FIND AVAILABILITY button that takes the user to a campsite search page where they begin looking for a site to reserve.
4. When a visitor begins the reservation process they must enter their criteria into the system. The campsite search page lets the user enter their requirements before searching for availability.
    1. Every reservation requires a start date and end date.
    2. Once the required fields are entered, provide a SUBMIT button that takes the user to the campsite search results page.
    3. *NICE TO HAVE* Not all sites are created equal. Some sites offer handicap accessible facilities, utility hookups, maximum occupancy, and max RV size. Provide this as additional search criteria. 
5. After the user completes the campsite search form, a results page provides a listing with the top 10 campsites available meeting their criteria.   
    1. Each available campsite should list its site number, maximum occupancy, handicap accessible, utility hookup option, and max RV length (if applicable).
    2. For any available campsite, the user should be able to click a RESERVE  button. This action will take the user to the site reservation form.
    3. A campsite is unavailable if any part of their preferred date range overlaps with an existing reservation.
    4. If no campsites are available, indicate to the user that they no sites are available and redirect them to the search page to enter an alternate date range.
    5. *NICE TO HAVE* A campsite is also unavailable if it does not meet any of their additional search criteria (accessible, utility, RV, maximum occupancy).
    6. *NICE TO HAVE* No campsite reservations are available if the user enters a reservation month when the park is not open.
6. The site reservation form displays after an available site is selected for a reservation. The user is asked to enter their name but should not be allowed to alter their reservation window.
    1. The page must require that a name is submitted for the reservation.
    2. The page should check that the campsite has not already been booked for that reservation (it is possible that two users both tried to book it). It also limits the likelihood that someone can tamper with our page data (i.e. pick a random site number, change the date, etc.) after we’ve found an available campsite for them. 
    3. Once the reservation has been submitted, the reservation is made and the user is sent to a confirmation page.
7. The confirmation page provides the user with the details about the reservation. It includes the name on the reservation, the site number, whether the site is handicap accessible, supports utility hookups or RV, and the maximum occupancy.
8. *NICE TO HAVE* The national park service would like an administrating option to see all current and upcoming reservations for each campground.*

Any items marked **_NICE TO HAVE_** should be completed after the core functionality of the project has been completed.

## Data Sources

### Park Table
A parks table is provided to the system that provides the data for each of the supported national parks. The data columns are as follows:

| | Field | Description|
|-|-------|------------|
|PK| park_id | A surrogate key for the park. |
| | name | Name of the park |
| | location | Location of the park |
| | establish_date | Date the park was established |
| | area | Size of the park in square km |
| | visitors | Annual number of visitors to the park |
| | description | Short description about the park |

### Campground Table
A campground table is provided to the system that provides a list of the one or many campgrounds located inside of a national park. The data columns are as follows:

| | Field | Description|
|-|-------|------------|
|PK| campground_id | A surrogate key for the campground |
|FK| park_id | Park that the campground is associated with |
| | name | Name of the campground |
| | open_from_mm | The numerical month the campground is open for reservation. ( 01 - January, 02 - February, …) |
| | open_to_mm | The numerical month the campground is closed for reservation. ( 01 - January, 02 - February, …) |
| | daily_fee | The daily fee for booking a campsite at this campground |

### Site Table
A site table lists all of the available campsites available for reservation in a campground.The data columns are as follows:

| | Field | Description|
|-|-------|------------|
|PK| site_id | A surrogate key for the site |
|FK| campground_id | Campground that the site belongs to |
| | site_number | The campsite number |
| | max_occupancy | Maximum occupancy at the campsite |
| | accessible| Indicates whether or not the site is handicap accessible |
| | max_rv_length | The maximum rv length that the campsite can fit. 0 indicates that no RV will fit at this campsite. |
| | utilities | Indicates whether or not the campsite provides access to utility hookup. |


### Reservation Table
The reservation table lists all of the past, current, and future reservations for a campsite in the national park system. The data columns are as follows:

| | Field | Description|
|-|-------|------------|
|PK| reservation_id | A surrogate key for the reservation |
|FK| site_id | Campsite that the reservation is for |
| | name | The name for the reservation |
| | from_date | The start date of the reservation |
| | to_date | The end date of the reservation |
| | create_date | The date that the reservation was booked |