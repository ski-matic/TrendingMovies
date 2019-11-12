# Trending Movies

A project that displays a list of trending movies and allows the user to search for movies.  
Tapping on an item displays more detailed information in a popup window.

## Things I implemented
### Trending movies list 
- Created a basic app structure with `MovieListActivity`, a viewModel named `MovieListViewModel`, a repository named `MovieListRepository` and an API class named `MovieDatabaseAPI`
- Retrieved "Trending movie" list data from the Movie DB server using the `retrofit` library for the request
- Created a recyclerView and recyclerViewAdapter in the main Activity screen as a grid of items using `GridLayoutManager`
- Bubbled up the `List<Movie>` from the repository to the UI as liveData ( `LiveData<List<Movie>>` ) 
- Showed the movie titles in the recyclerView items (later to be replaced with images)
- Updated the recyclerView item layout (use `ConstraintLayout` rather than `LinearLayout` and added appropriate fields)
- Updated the icon and the colours of the app
- Observed the `LiveData` of movies when the main activity is created, which will cause the movie list to load and display when the app launches
### Poster images
- Retrieved the `Configuration` from the server using `retrofit`, saved it in the viewModel and exposed it as LiveData to the UI
- Added `fetchConfigurationAndMovies` which ensures a configuration has been fetched before the movie list is fetched (this could be better - see below)
- Set the base URL for posters and backdrop images in the `Movie` object when a `Configuration` is obtained
- Used `Picasso` to load the poster image when the Movie object is bound to RecyclerView
- Added a Picasso "placeholder" image for when the poster image is loading
### Search
- Added a search icon and `SearchView` to the actionbar, and added handlers for the assocated actions
- Added a `retrofit` call to search the API and added classes for the `SearchResults`
- Displayed the results of a search query by setting the LiveData Movie List to the results, and minimize the keyboard
- Set the list to "Trending" items when a search is "closed"
- Moved to the top of the Movie list when switching between "Trending" and "Search"
  - It might be nicer to do a fade out/fade in or something to avoid the flash of white
### Details PopupWindow
- Added a `PopupWindow` that is displayed when tapping on a movie and pass in the `Movie` to be displayed
- Filled in the contents of the `PopupWindow` with the movie details
- Loaded the backdrop image using `Picasso` with a placeholder image
- Dimmed the background behind the `PopupWindow` when the details overlay is displayed
- Added a fun animation to make the `PopupWindow` "slide in"
### Handle no network
- Showed a "no network" screen if there's no network connection

## Known issues
- I have the app icon showing in the title bar and the spacing is strange.  Would be nice to fix that
  - In fact, it would be better to replace the icon with a "hamburger" navigation icon if there were a navigation drawer
- I didn't handle any tablet layouts at all, only phone (single pane vs. twoPane)
- Sanitizing / URL encoding the query string when doing a search
- Handle the case where a movie doesn't have a poster image or backdrop image
- A bit of a flash when going between "Search" and "Trending".  Would be nice to have a smoother transition
- When the details overlay is being displayed, on an older version of Android it appears that tapping outside the overlay won't dismiss it
  - The back button should at least close it
- If the screen is rotated into landscape, the detail overlay doesn't usually fit on the screen.
  - It should be scrollable
- If a detail overlay is shown and the screen is rotated the overlay disappears  
- If a search is executed, and then the screen is rotated, the search is cleared
- After a search, sometimes when dismissing a detail popup, the keyboard will pop back up
  - I may have fixed this, but didn't test thoroughly

## Features I would do if I had more time
- Trending call options:
  - Could give the user the ability to choose the parameters that are sent
- Use Rx "zip" function that waits for both configuration and movie to be fetched instead of "fetchConfigurationAndMovies()"
  - https://stackoverflow.com/questions/36474120/how-to-make-multiple-request-and-wait-until-data-is-come-from-all-the-requests-i
- Download the images before the "onBindViewHolder" method so they might already be there when needed
- Don't hard code the number of columns - use the dimensions of images and dimensions of screen
- The `Movie` object could cache the poster image once it's loaded
  - Would be nice to pre-load images before they are needed
- Poster size is hard coded.  Would be better to figure out the best size to use
- Would be cool if there's no network, to detect if a connection becomes available to automatically
  go and fetch the items
- Unit tests
- The poster images should be cached in some way rather than always get fetched
- Add recent query suggestions
- Consider the idea of searching each time the user modifies the search string
- Might be nicer to do a fade out/fade in or something when transitioning between "Trending" and "Search"
- Results could be saved to a DB so that the app is semi-functional offline
- I noticed towards the end that I could fetch "page 2" of the data by adding a query parameter
  - I would load more results as the user continues to scroll


