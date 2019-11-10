---------------------------
## Trending Movies
----------------------------

A Master/Detail type project that displays a list of trending movies.  Tapping on an item displays more detailed information


Things that could be done:
- Trending call options:
  - Could give the user the ability to choose the parameters that are sent

Tasks:
X Get data from server
  - there's pages of data - how many pages to get?
X Parse data from server
X Create UI of main screen to be a grid of items (recyclerView)
X Get data retrieved from web call from repository -> viewModel -> UI
X display the list of movies (titles)
X replace the linearLayout of each row item with a constraintLayout
X Update the icon of the app
X Load the data automatically when the app starts
X Configuration
  X Make "Configuration" fetched using retrofit
  X save the configuration to the viewModel and expose it to the activity via LiveData

- When should the configuration be fetched?


- replace "Richard-debug" with something else
- for each item, go fetch the image
  - to start, do this when the item is displayed?
  - when should this actually take place?  Should the "Movie" object contain the actual image?
- turn the rows into a grid
- swipe to refresh
- display the image in the recyclerView
  - Compose code to get the URL for the movie (using configuration)
  - How do we know what size to get?  Maybe something here to do in the future?
- If the images pop back in in a strange order - how can we make sure they come back in correct order?
- Make items tappable
- When tapping an item go to detail view?
  - Or do they want an overlay specifically?
  - use the "backdrop" image?  Rather than "poster" image??
- What to show in detail view?
- Add search bar at the top?
  - Use search button?
  - Search as soon as the user types stuff?
  - Delay (debounce?) before searching after keystrokes
  - What would I be searching / filtering on exactly?  Or is it calling the search API?
  - how to cancel a search?
- UX transitions?
- Corner cases?
  - No internet?
  - Rotate screen?
  - Language?
- Tests
- Caching images?
  - saving images?
- Caching results?
  - DB?
- remove twopane stuff?














----------------------------
## Things I implemented
----------------------------

- Created a new project with a "Master/Detail" template
- Fetch the contents of the URL
  - Created an AsyncTask called fetchurltask to get the json payload
    - make sure to keep json out of fetchurltask to keep it format independant
  - created movie class and movielocationprovider
  - add a callback method so the result of FetchURLTask can call back to the provider when it has completed
- Parse the JSON.
  - I'm simply reverse engineering what I see in the JSON right now.
  - I should be using: "meta: view: columns" to figure out how to parse the movie data properly.
  - I assume the column stucture could potentially change, and it would be important to use the
    column definitions in order to parse the movies properly
  - For the moment I'm going to just extract the items assuming the current columns structure
  - It appears that the data can be obtained using an android-sdk.  This could allow doing queries
    for data and less parsing of JSON data
  - Once the JSON is parsed, use a callback from the provider to give the Movie<List> to the recyclerView
- Modify the recyclerView to show Movie objects
- I realized that the JSON list has an entry for a movie for each location.  I decided the movie list
  should only show each movie once, and each movie can have a list of locations
- Modify Movie objects to contain a list of locations, add "getYearString"
- Added a menu to the action bar and a sorting class to allow for basic sorting
- Sort the data when it is loaded
- Update the listview to show the title, year and # of locations
- Add the rest of the properties for a movie to the Movie object and parse them from the JSON
- create method to append actors names for display in details
- move formatting strings to resources
- add textviews in details to display other movie properties and popuplate them
- don't crash if no internet connection
- Add app icon to actionbar and image of SF to collapsable toolbar on details
- loop through the properties for the detail page and hide or populate them
- Add a list of the locations to the detail view
- Add the abililty to sort by "number of locations"
- Add maps to the detail view
- Add code that attempts to get lat-longs for all the locations for a movie and add the
  associated markers to the map
- Add a quick "welcome" message to the main screen.
- Put the creation of the map markers onto a background thread.  Be sure to cancel the background
  thread if the user moves away from the detail view.  Once the results are completed, make sure
  the UI is still available
- Implement a bottom sheet containing 3 sortby options (title, year, # locations)
- The "sortby" menu icon shows the bottomsheet, and when a selection is made a callback is made to the mainActivity which resorts the movie list
- Add sorting in asc/desc
- Tapping the currently selected item in the bottom sheet reverses the sorting
- The menu icon displays how the list is sorted

----------------------------
## Known issues
----------------------------

- Tapping on a movie that has many locations can be slow to transition to the detailView.  This is because it takes
  a while for it to get the lat-longs for all the locations, and I ran out of time to put that on a background thread.
- If there is an error parsing the JSON file, the screen remains blank.  I would ideally show a message to the user
- The same thing happens if the file cannot be retrieved from the server (eg. no internet connection).
  - Could also check if wifi is on, etc and inform them they need an internet connection
- Scrolling in the detail view is a bit wonky because of the recyclerview list of locations
- The detail view doesn't load with the view scrolled to the top for some reason.
  - This seemed to be a result of adding the recyclerview of locations
- The detail view doesn't appear to scroll properly on a tablet

----------------------------
## Features I would do if I had more time
----------------------------

- Having the mapView on the detail view is a little awkward.  I would want to improve this.  Perhaps
  showing a non-interactive thumbnail, and tapping on it would take the user to a full screen map
- The map dimensions are hard-coded, I would want to make them sized nicely
- Display an image or text in the empty view when the app loads
  - I did this pretty quickly, and didn't get to test it as much as I would like
- Save the sort preference
- It would be cool if you could use the users current location to try and find movies that were
  filmed nearby!
- Make sure other languages don't have issues (length of languages like russian or dutch?)
  - And check that Right to left is handled properly
- I would've liked to add "pull to refresh"
- I would've liked to add a "search" or "filter" option
- I hard coded the column mapping for parsing the JSON.  It appears that the column structure is
  described though, and it would be better to use that to determine how to properly parse the data
- Save the JSON to a file, or DB so it can be loaded immediate
  - This of course brings up the question about when and how to update the local version
  - Is there a "last modified" property in the JSON file to check if the cached one is out of date?

