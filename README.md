# Overview of the submission structure

//Have the submission structure for the final submission here
Final Submission: Contents(Project Report.pdf, Source Code.pdf, User Guide for Capstone Project.pdf)
  Source Code.pdf: includes Github link and MongoDB link to our database

# AdLibStories JavaFX Application

This JavaFX application is designed to let users play a game where they can create a story by taking turns in writing. The application has two main scenes, the login and the gameplay scene. 

## Login Scene

When the application first launches, it displays a login scene. The scene contains several buttons:

* **Create a room button**: Allows users to create a new game room.
* **Join a room button**: Allows users to join an existing game room.
* **Login button**: Allows users to login to an existing account.
* **Create account button**: Allows users to create a new account.
* **Profile button**: Allows users to view their profile.

The login scene also displays a logo image at the center of the screen.

## Gameplay Scene

Once a user has logged in and joined a room, they will be directed to the gameplay scene. The gameplay scene consists of the following components:

* **Story text area**: Displays the current story text.
* **Prompt text area**: Displays the prompt for the next player to write.
* **Player list**: Displays the list of players in the game.
* **Game options label**: Displays the label for game options.

The gameplay scene also displays the room code at the bottom of the screen.

When it is a player's turn to write, they can input their text in the prompt text area and then submit it using a button that has not been implemented in this code. 

The scene also displays the current player's icon and a rectangle box at the right side of the screen. 

## Running the Application

To run the application, run the `JavaFX` class. The main method of the class calls the `start` method, which initializes the login scene. 

Please note that this code has not been implemented with several functionality, including the button that lets the user attempt to add to the story what is in the textArea.
