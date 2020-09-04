# General presentation of the project

This is a weather application, this application is designed according to the main clean & SOLID architecture, the goal was to set up the most scalable architecture possible and also to highlight all the most popular principles of the jetpack with the implementation of an MVVM under architecture.

This application is structured according to an external module called core which contains our data logic and our structuring of our cache implementation which we implemented through the RoomData library.
Our application module by account is separated into two main parts, the framework part which calls on all the business applications of our project, in particular the injection of dependencies with dagger to bring more readability to our code, the implementation of our database management with roomData to manage our cache, the implementation of our viewModels to materialize the MVVM dimension of our architecture, the implementation of our http requests with Retrofit, the implementation of the glide library to display our weather icons.

at the level of our presentation package we have implemented our UIs in particular based on the navigation library which is one of the new features of jetpack which is a library which brings a lot of facilitation in terms of navigation at the application level.

At the user interface level, the application takes into account the swiperefreshlayout to update the user interface, uses the shimmer library as a progress bar and integrates a google maps display to display the cities
