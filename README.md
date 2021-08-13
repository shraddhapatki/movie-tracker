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

**Phase 4: Task 3**

- I would split up the GUI class into different classes so that each class has one responsibility only, in line with the 
Single Responsibility Principle.
- For the different methods in MovieList that have duplicate code, I would reduce duplication so that it is easier to 
change functionality of those methods together and to fix bugs.
- I would make all my classes more robust by implementing more exceptions.
- I would split up the MovieList class into All, Watched and Unwatched classes. These classes would extend an abstract 
MovieList class. This would ensure that each class has a single responsibility.
