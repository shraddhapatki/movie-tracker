# Movie Watchlist

**What will my application do?**

Through my project, I want to create a movie watchlist that allows users to record information about films they
have watched or would like to watch, the movie's genre, and its ratings. 

**Who will use it?**

This would a useful application for film enthusiasts who are interested in keeping track of the movies they want to 
watch and record the ones they have watched. As someone who enjoys watching films, I think this would be an 
appropriate application to organise this sort of information. A user could use such an application for when they are out
of ideas on what movie to watch with their loved ones. Or, they could use such an application to keep track of their 
favourite films.

**Why is this project of interest to me?**

While brainstorming about project ideas, I realised that I have a list of movies on my Notes app that I either want to
watch or have already watched and loved. However, this way of tracking was extremely messy. So, I thought that having a
way to organise this kind of data would be useful.

**User Stories**

- As a user, I want to be able to add a movie to the movie list.
- As a user, I want to be able to delete a movie.
- As a user, I want to be able to view all movies.
- As a user, I want to be able to view my watched and unwatched movies.
- As a user, I want to be able to mark a movie as watched or unwatched.
- As a user, I want to be able to save my movie list to file.
- As a user, I want to be able to load my movie list to file.

**Phase 4: Task 2**

I have chosen to test and design a class in my model package to make it robust. I have made the MovieList class robust 
by throwing a ExistingMovieException in the addAll, addWatched and addUnwatched methods. If a movie has the same name as
an existing movie in the respective lists, it throws the exception.
