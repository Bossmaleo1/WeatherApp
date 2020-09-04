This is a weather application, this application is designed according to the main clean architecture & SOLID, the goal was to set up the most scalable architecture possible and also to highlight all the most popular principles of the jetpack with the implementation of an MVVM architecture.

This application is structured according to an external module called core which contains our data logic and our structuring of our cache implementation that we have implemented through the RoomData library.
Our application module by account is separated into two main parts, the framework part which calls on all the business applications of our project, in particular the dependency injection with dagger to bring more readability to our code, the implementation our management of database with roomData to manage our cache, the implementation of our viewModels to materialize the MVVM dimension of our architecture, the implementation of our http requests with Retrofit, the implementation of the glide library to display our icons weather.

at the level of our presentation package we have implemented our UIs in particular based on the Navigation library which is one of the new features of jetpack which is a library which brings a lot of facilitation in terms of navigation at the application level.

At the UI level, the application takes into account the swiperefreshlayout to refresh the UI, uses the shimmer library as a progress bar and integrates a google maps display to display the cities