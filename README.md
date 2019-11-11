Trending Movies
============================

A Master/Detail type project that displays a list of trending movies.  Tapping on an item displays more detailed information


## Things I implemented
- Get data from server
- Parse data from server
- Create UI of main screen to be a grid of items (recyclerView)
- Get data retrieved from web call from repository -> viewModel -> UI
- display the list of movies (titles)
- replace the linearLayout of each row item with a constraintLayout
- Update the icon of the app
- Load the data automatically when the app starts
- Configuration
  - Make "Configuration" fetched using retrofit
  - save the configuration to the viewModel and expose it to the activity via LiveData
- Load the poster image using picasso
- Change recyclerView to be a gridlayout
- Update the app icon, FAB icon and colors
- Added "fetchConfigurationAndMovies" to handle chaining the calls to make sure the configuration
  exists so when we go through the list of movies we can set the poster URL
- Each Movie doesn't need the full poster URL since they all share a common base URL
  - Save the common base URL to the Movie object and then create the posterURL when it's needed
- Add a picasso "Placeholder" for the image when it's loading
- Show a "no network" screen if there's no network connection

## Things to do next
- handle if "fetchConfiguration" comes back with an error? (airplane mode will do this rightnow)
- replace "Richard-debug" with something else
- there's pages of data - how many pages to get?
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


## Known issues
- I have the app icon showing in the title bar and the spacing is strange.  Would be nice to fix that
- In fact, it would be better to replace the icon with a "hamburger" navigation icon if there were a navigation drawer
- I didn't handle any tablet layouts at all, only phone (single pane vs. twoPane)


## Features I would do if I had more time
- Trending call options:
  - Could give the user the ability to choose the parameters that are sent
- Use Rx "zip" function that waits for both configuration and movie to be fetched instead of "fetchConfigurationAndMovies()"
- Download the images before the "onBindViewHolder" method so they might already be there when needed
- Don't hard code the number of columns - use the dimensions of images and dimensions of screen
- The `Movie`object could cache the poster image once it's loaded
  - Would be nice to pre-load images before they are needed
- Poster size is hard coded.  Would be better to figure out the best size to use
- Would be cool if there's no network, to detect if a connection becomes available to automatically
  go and fetch the items



