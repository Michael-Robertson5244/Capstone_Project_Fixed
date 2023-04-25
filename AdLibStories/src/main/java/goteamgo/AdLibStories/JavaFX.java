package goteamgo.AdLibStories;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import java.io.*;

public class JavaFX extends Application {

	StackPane stackPane = new StackPane();
	Scene scene = new Scene(stackPane,900,700);
	boolean loggedIn = false;
	Player playerProfile;

	//SpellChecker spellchecker = profile.spellChecker();

	static Game game = new Game();
	Socket client;
	static Server server;
	static int portNo = 60999;

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub

		// adding logo

		InputStream stream = new FileInputStream("AdLibStories.png");
		Image logo = new Image(stream);

		ImageView imageView = new ImageView();
		imageView.setImage(logo);

		imageView.setFitHeight(400);
		imageView.setFitWidth(400);


		// Create the "Create a room" button

		Button createButton = createButton(primaryStage);
		Button joinButton = joinButton(primaryStage);
		Button loginButton = loginButton(primaryStage);
		Button createAccountButton = createAccountButton(primaryStage);
		Button profileButton = profileButton(primaryStage);


		imageView.setTranslateY(-150);
		joinButton.setTranslateY(140);
		createButton.setTranslateY(90);
		loginButton.setTranslateY(40);
		createAccountButton.setTranslateY(-10);
		profileButton.setTranslateY(190);

		// Create a stack pane to center the buttons in the middle of the screen

		stackPane = new StackPane(createButton, joinButton, loginButton, createAccountButton, profileButton, imageView);
		stackPane.setBackground(new Background(new BackgroundFill(Color.web("#FFFDD0"), CornerRadii.EMPTY, Insets.EMPTY)));

		stackPane.setAlignment(Pos.CENTER);

		scene = new Scene(stackPane, 900, 700);

		primaryStage.setScene(scene);
		scene.setFill(Color.web("#FFFDD0"));
		primaryStage.setTitle("Room GUI");
		primaryStage.show();
	}

	//Need to add a button that lets the user attempt to add to the story what is in the textArea
	public void playGame(Stage primaryStage) {
		Group playGroup = new Group();
		Scene playScene = new Scene(playGroup,900,700);


		InputStream playerIconStream;
		Image playerIcon;
		ImageView thisPlayer;

		try {

			playerIconStream = new FileInputStream("currentUserIcon.png");

			playerIcon = new Image(playerIconStream);
			
			//int numPlayersInGame = Integer.parseInt(numPlayersTextArea.getText());

			//System.out.println(numPlayersInGame);
			
			int iconHeight = 30;
			int margin = 20;
			
			//for(int index = 0; index < numPlayersInGame; index++) {
				
				thisPlayer = new ImageView();
				thisPlayer.setImage(playerIcon);
				
				thisPlayer.setFitHeight(iconHeight);
				thisPlayer.setFitWidth(30);

				//int offsetY = index * (iconHeight + margin);
				
				thisPlayer.setTranslateX(30);
				thisPlayer.setTranslateY(200); // + offsetY);
				
			//}

			Label playerNames = new Label("Player Names");

			playerNames.setTranslateX(55);
			playerNames.setTranslateY(150);

			playerNames.setFont(new Font("Times New Roman", 20));
			playerNames.setStyle("-fx-text-fill: #897361;");

			TextArea storyTextArea = new TextArea("story line goes here");
			storyTextArea.setFont(new Font("Times New Roman", 20));
			storyTextArea.setStyle("-fx-control-inner-background: #EFA565; -fx-background-color: #EFA565; -fx-text-fill: #897361; ");

			storyTextArea.setPrefWidth(450);
			storyTextArea.setPrefHeight(350);

			storyTextArea.setTranslateX(225);
			storyTextArea.setTranslateY(150);

			storyTextArea.setEditable(false);

			TextArea promptText = new TextArea("prompt goes here");
			promptText.setText(game.getPrompt());
			promptText.setWrapText(true);

			promptText.setFont(new Font("Times New Roman", 20));

			promptText.setPrefWidth(450);
			promptText.setPrefHeight(50);

			promptText.setTranslateX(225);
			promptText.setTranslateY(75);

			promptText.setStyle("-fx-control-inner-background: #EFA565; -fx-background-color: #EFA565; -fx-text-fill: #897361; ");

			promptText.setEditable(false);

			TextArea storyEntryTextArea = new TextArea();
			
			storyEntryTextArea.setOnKeyPressed(event ->{
			
				KeyCode keyCode = event.getCode();

				if(keyCode == KeyCode.SPACE || keyCode == KeyCode.ENTER) {
					String currentText = storyEntryTextArea.getText();
					String[] words = currentText.split(" ");
					String lastWord = words[words.length - 1];
					

					System.out.println("Last word: " + lastWord);
					
					String file = "words.txt";
					boolean isMisspelled = false;
			        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			            String line;
			            while ((line = br.readLine()) != null) {
			                if (line.contains(lastWord)) {
			                    System.out.println("Word found");
			                    isMisspelled = false;
			                    break;
			                }else {
			                	isMisspelled = true;
			                }
			     
			            }
			        } catch (IOException e) {
			            e.printStackTrace();
			        }
			        
			        if(isMisspelled == true) {
			        	//System.out.println("Word mispelled: " +lastWord);
			        	// code to change color of word
			        }
					
				}
				
			});

			Button submitButton = new Button("Submit");
			submitButton.setStyle("-fx-background-radius: 20px; -fx-text-fill: #897361; -fx-background-color: #EFA565;");
			submitButton.setFont(new Font("Times New Roman", 20));

			submitButton.setPrefWidth(250);
			submitButton.setPrefHeight(20);

			submitButton.setTranslateX(325);
			submitButton.setTranslateY(600);

			submitButton.setOnAction(new EventHandler<ActionEvent>() {

				public void handle(ActionEvent actionEvent) {

					String textFromArea = storyEntryTextArea.getText();
					System.out.println("Submitted text: " + textFromArea);
					storyTextArea.appendText(textFromArea + "\n");
					storyEntryTextArea.setText("");

				}

			});

			submitButton.setOnMouseEntered(event -> {
				submitButton.setStyle("-fx-background-radius: 20px; -fx-text-fill: #EFA565; -fx-background-color: #897361;");
			});

			submitButton.setOnMouseExited(event -> {
				submitButton.setStyle("-fx-background-radius: 20px; -fx-text-fill: #897361; -fx-background-color: #EFA565;");
			});

			storyEntryTextArea.setPromptText("Write your Story here");
			storyEntryTextArea.setFont(new Font("Times New Roman", 20));
			storyEntryTextArea.setStyle("-fx-control-inner-background: #EFA565; -fx-background-color: #EFA565; -fx-text-fill: #897361; ");
			storyEntryTextArea.setWrapText(true);

			storyEntryTextArea.setPrefWidth(450);
			storyEntryTextArea.setPrefHeight(50);

			storyEntryTextArea.setTranslateX(225);
			storyEntryTextArea.setTranslateY(520);


			Label promptLabel = new Label("Prompt:");
			promptLabel.setFont(new Font("Times New Roman", 20));
			promptLabel.setStyle("-fx-prompt-text-fill: derive(-fx-control-inner-background, -30%);");
			promptLabel.setStyle("-fx-text-fill: #897361;");

			promptLabel.setTranslateX(140);
			promptLabel.setTranslateY(85);

			Button previousButton = previousButton(primaryStage);

			playGroup.getChildren().addAll(previousButton, promptText, promptLabel, storyTextArea, storyEntryTextArea, playerNames, thisPlayer);
			playScene.setFill(Color.web("#FFFDD0"));

		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block

			e1.printStackTrace();

		}

		primaryStage.setScene(playScene);
		primaryStage.show();
	}

	//Possibly do not need this
	public Button playButton(Stage primaryStage, TextArea numPlayers) {
		Button playButton = new Button("PLAY");
		playButton.setStyle("-fx-background-radius: 20px; -fx-text-fill: #897361; -fx-background-color: #EFA565;");
		playButton.setFont(new Font("Times New Roman", 20));

		playButton.setTranslateX(450);
		playButton.setPrefWidth(250);

		playButton.setOnMouseEntered(event -> {
			playButton.setStyle("-fx-background-radius: 20px; -fx-text-fill: #EFA565; -fx-background-color: #897361;");
		});

		playButton.setOnMouseExited(event -> {
			playButton.setStyle("-fx-background-radius: 20px; -fx-text-fill: #897361; -fx-background-color: #EFA565;");
		});

		playButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {	

				int players = Integer.parseInt(numPlayers.getText());

				if(players > 6 || players < 2)
				{
					//popup saying too many players
				}
				else
				{
					//TODO: Send number of players to server
					/*server = new Server(portNo, players);
            		try {
						//server.run();
					} catch (IOException e1) {
						// TODO Error to start server
						e1.printStackTrace();
					}*/

					//Display the game screen
					playGame(primaryStage);
				}

			}

		});
		return playButton;
	}

	public Button createAccountButton(Stage primaryStage) {
		Button createAccountButton = new Button("CREATE AN ACCOUNT");

		createAccountButton.setStyle("-fx-background-radius: 20px; -fx-text-fill: #897361; -fx-background-color: #EFA565;");
		createAccountButton.setFont(new Font("Times New Roman", 20));
		createAccountButton.setPrefWidth(250);

		createAccountButton.setOnMouseEntered(event -> {
			createAccountButton.setStyle("-fx-background-radius: 20px; -fx-text-fill: #EFA565; -fx-background-color: #897361;");
		});

		createAccountButton.setOnMouseExited(event -> {
			createAccountButton.setStyle("-fx-background-radius: 20px; -fx-text-fill: #897361; -fx-background-color: #EFA565;");
		});

		createAccountButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				// TODO: Add logic for joining a room

				Group createAccountGroup = new Group();
				Scene createAccountScene = new Scene(createAccountGroup,900,700);

				InputStream iconStream;
				Image icon;
				ImageView userIcon;

				try {

					iconStream = new FileInputStream("userIcon.png");

					icon = new Image(iconStream);

					userIcon = new ImageView();
					userIcon.setImage(icon);

					userIcon.setFitHeight(150);
					userIcon.setFitWidth(150);

					userIcon.setTranslateX(375);
					userIcon.setTranslateY(95);

					TextArea userName = new TextArea();
					userName.setPromptText("username");
					userName.setStyle("-fx-control-inner-background: #EFA565; -fx-background-color: #EFA565; -fx-text-fill: #897361; ");

					userName.setPrefWidth(250);
					userName.setPrefHeight(50);

					userName.setTranslateX(325);
					userName.setTranslateY(275);

					PasswordField passWord = new PasswordField();
					passWord.setPromptText("password");
					passWord.setStyle("-fx-control-inner-background: #EFA565; -fx-background-color: #EFA565; -fx-text-fill: #897361; ");

					passWord.setPrefWidth(250);
					passWord.setPrefHeight(50);

					passWord.setTranslateX(325);
					passWord.setTranslateY(350);

					TextArea displayName = new TextArea("");
					displayName.setPromptText("display name");
					displayName.setStyle("-fx-control-inner-background: #EFA565; -fx-background-color: #EFA565; -fx-text-fill: #897361; ");

					displayName.setPrefWidth(250);
					displayName.setPrefHeight(50);

					displayName.setTranslateX(325);
					displayName.setTranslateY(425);


					Button create = new Button("CREATE ACCOUNT");
					create.setStyle("-fx-background-radius: 20px; -fx-text-fill: #897361; -fx-background-color: #EFA565;");
					create.setFont(new Font("Times New Roman", 20));
					create.setPrefWidth(250);

					create.setTranslateX(325);
					create.setTranslateY(525);

					create.setOnMouseEntered(event -> {
						create.setStyle("-fx-background-radius: 20px; -fx-text-fill: #EFA565; -fx-background-color: #897361;");
					});

					create.setOnMouseExited(event -> {
						create.setStyle("-fx-background-radius: 20px; -fx-text-fill: #897361; -fx-background-color: #EFA565;");
					});

					create.setOnAction(new EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent event) {

							String username = userName.getText();
							String password = passWord.getText();
							String screenName = displayName.getText();

							if(!DB.validUsername(username))
							{
								Alert alert = new Alert(Alert.AlertType.WARNING);
								alert.setTitle("Error");
								alert.setHeaderText("Account creation failed: Username in use.");
								alert.setContentText("Please use a different username.");
								alert.showAndWait();
							}
							else if(!DB.validScreenName(screenName))
							{
								Alert alert = new Alert(Alert.AlertType.WARNING);
								alert.setTitle("Error");
								alert.setHeaderText("Account creation failed: Screen name in use.");
								alert.setContentText("Please use a different screen name.");
								alert.showAndWait();
							}
							else
							{
								DB.insertUser(username, password, screenName);

								Alert alert = new Alert(Alert.AlertType.WARNING);
								alert.setTitle("Success");
								alert.setHeaderText("Account was created successfully.");
								alert.setContentText("Please return to the menu and login.");
								alert.showAndWait();
							}
						}

					});

					Button previousButton = previousButton(primaryStage);

					createAccountGroup.getChildren().addAll(previousButton, userName, passWord, displayName, userIcon, create);
					createAccountScene.setFill(Color.web("#FFFDD0"));

				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block

					e1.printStackTrace();

				}

				primaryStage.setScene(createAccountScene);
				primaryStage.show();
			}
		});

		return createAccountButton;
	}

	public Button previousButton(Stage primaryStage) {

		Button previousButton = new Button("Previous"); 
		previousButton.setTranslateX(800);
		previousButton.setTranslateY(650);

		previousButton.setOnAction(f->primaryStage.setScene(scene));
		previousButton.setStyle("-fx-background-radius: 20px; -fx-text-fill: #897361; -fx-background-color: #EFA565;");

		previousButton.setOnMouseEntered(event -> {
			previousButton.setStyle("-fx-background-radius: 20px; -fx-text-fill: #EFA565; -fx-background-color: #897361;");
		});

		previousButton.setOnMouseExited(event -> {
			previousButton.setStyle("-fx-background-radius: 20px; -fx-text-fill: #897361; -fx-background-color: #EFA565;");
		});

		return previousButton;
	}

	public Button loginButton(Stage primaryStage) {
		Button loginButton = new Button("LOGIN");

		loginButton.setStyle("-fx-background-radius: 20px; -fx-text-fill: #897361; -fx-background-color: #EFA565;");
		loginButton.setFont(new Font("Times New Roman", 20));
		loginButton.setPrefWidth(250);

		loginButton.setOnMouseEntered(event -> {
			loginButton.setStyle("-fx-background-radius: 20px; -fx-text-fill: #EFA565; -fx-background-color: #897361;");
		});

		loginButton.setOnMouseExited(event -> {
			loginButton.setStyle("-fx-background-radius: 20px; -fx-text-fill: #897361; -fx-background-color: #EFA565;");
		});

		loginButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				// TODO: Add logic for joining a room

				Group loginGroup = new Group();
				Scene loginScene = new Scene(loginGroup,900,700);

				Label loginTitle = new Label("LOGIN");

				loginTitle.setFont(new Font("Times New Roman", 50));
				loginTitle.setStyle("-fx-text-fill: #897361;");

				loginTitle.setTranslateX(370);
				loginTitle.setTranslateY(100);

				Label username = new Label("USERNAME:");

				username.setTranslateX(250);
				username.setTranslateY(265);

				username.setFont(new Font("Times New Roman", 25));
				username.setStyle("-fx-text-fill: #897361;");

				TextArea userText = new TextArea();
				userText.setStyle("-fx-control-inner-background: #EFA565; -fx-background-color: #EFA565; -fx-text-fill: #897361; ");

				userText.setPrefWidth(250);
				userText.setPrefHeight(50);

				userText.setTranslateX(425);
				userText.setTranslateY(250);

				Label password = new Label("PASSWORD:");

				password.setFont(new Font("Times New Roman", 25));
				password.setStyle("-fx-text-fill: #897361;");

				password.setTranslateX(250);
				password.setTranslateY(365);

				PasswordField passText = new PasswordField();
				passText.setStyle("-fx-control-inner-background: #EFA565; -fx-background-color: #EFA565; -fx-text-fill: #897361; ");

				passText.setPrefWidth(250);
				passText.setPrefHeight(50);

				passText.setTranslateX(425);
				passText.setTranslateY(350);

				//Add login button functionality
				Button loginButton = new Button("LOGIN");
				loginButton.setStyle("-fx-background-radius: 25px; -fx-text-fill: #897361; -fx-background-color: #EFA565;");

				loginButton.setTranslateX(370);
				loginButton.setTranslateY(500);

				loginButton.setPrefWidth(200);

				loginButton.setOnMouseEntered(event -> {
					loginButton.setStyle("-fx-background-radius: 20px; -fx-text-fill: #EFA565; -fx-background-color: #897361;");
				});

				loginButton.setOnMouseExited(event -> {
					loginButton.setStyle("-fx-background-radius: 20px; -fx-text-fill: #897361; -fx-background-color: #EFA565;");
				});

				loginButton.setFont(new Font("Times New Roman", 25));

				loginButton.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {

						String userName = userText.getText();
						String passWord = passText.getText();

						System.out.println("Username: " + userName);

						if(loggedIn)
						{
							Alert alert = new Alert(Alert.AlertType.WARNING);
							alert.setTitle("Error");
							alert.setHeaderText("Already logged in.");
							alert.setContentText("Please return to the menu.");
							alert.showAndWait();
						}
						else if(DB.login(userName, passWord))
						{
							playerProfile = new Player(userName, DB.getDisplayName(userName));

							System.out.println("Username: " + playerProfile.getUsername() + ", DisplayName: " + playerProfile.getDisplayName());

							Alert alert = new Alert(Alert.AlertType.WARNING);
							alert.setTitle("Success");
							alert.setHeaderText("Login was successful.");
							alert.setContentText("Please return to the menu.");
							alert.showAndWait();

							setLoggedInTrue();
						}
						else
						{
							Alert alert = new Alert(Alert.AlertType.WARNING);
							alert.setTitle("Error");
							alert.setHeaderText("Incorrect username or password.");
							alert.setContentText("Please try again.");
							alert.showAndWait();
						}
					}

				});

				Button previousButton = previousButton(primaryStage);

				loginGroup.getChildren().addAll(previousButton, loginTitle, username, password, userText, passText, loginButton);

				loginScene.setFill(Color.web("#FFFDD0"));

				primaryStage.setScene(loginScene);
				primaryStage.show();
			}
		});
		return loginButton;

	}

	public Button joinButton(Stage primaryStage) {
		Button joinButton = new Button("JOIN A ROOM");

		joinButton.setStyle("-fx-background-radius: 20px; -fx-text-fill: #897361; -fx-background-color: #EFA565;");
		joinButton.setFont(new Font("Times New Roman", 20));
		joinButton.setPrefWidth(250);

		joinButton.setOnMouseEntered(event -> {
			joinButton.setStyle("-fx-background-radius: 20px; -fx-text-fill: #EFA565; -fx-background-color: #897361;");
		});

		joinButton.setOnMouseExited(event -> {
			joinButton.setStyle("-fx-background-radius: 20px; -fx-text-fill: #897361; -fx-background-color: #EFA565;");
		});

		joinButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				// TODO: Add logic for joining a room
				Group joinGroup = new Group();
				Scene joinScene = new Scene(joinGroup,900,700);

				Label joinLabel = new Label("Enter Room Code:");
				joinLabel.setFont(new Font("Times New Roman", 30));
				joinLabel.setStyle("-fx-text-fill: #897361;");

				joinLabel.setTranslateX(322);
				joinLabel.setTranslateY(250);

				TextArea joinArea = new TextArea();
				joinArea.setPromptText("room code goes here");
				joinArea.setStyle("-fx-text-fill: #897361;");

				joinArea.setPrefWidth(200);
				joinArea.setPrefHeight(50);

				joinArea.setTranslateX(335);
				joinArea.setTranslateY(330);

				//This button will let the user join the server and bring to the lobby screen.
				Button joinRoomButton = new Button("JOIN ROOM");
				joinRoomButton.setStyle("-fx-background-radius: 25px; -fx-text-fill: #897361; -fx-background-color: #EFA565;");
				joinRoomButton.setFont(new Font("Times New Roman", 20));

				joinRoomButton.setPrefWidth(200);

				joinRoomButton.setTranslateX(335);
				joinRoomButton.setTranslateY(430);

				joinRoomButton.setOnMouseEntered(event -> {
					joinRoomButton.setStyle("-fx-background-radius: 20px; -fx-text-fill: #EFA565; -fx-background-color: #897361;");
				});

				joinRoomButton.setOnMouseExited(event -> {
					joinRoomButton.setStyle("-fx-background-radius: 20px; -fx-text-fill: #897361; -fx-background-color: #EFA565;");
				});

				joinRoomButton.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {

						String host = joinArea.getText();
						//Need to check if the connection to the server is good
						try {
							client = new Socket(host, portNo);

							//TODO: Send the player object to the server so it has it

							//TODO: Receive list of players and player number so that turns can be tracked

							//Goes to the game screen
							playGame(primaryStage);
						} 
						catch (UnknownHostException e) {
							// TODO Auto-generated catch block
							// Have a pop up that the IP is wrong
							e.printStackTrace();
						} 
						catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}

				});

				Button previousButton = previousButton(primaryStage);

				joinGroup.getChildren().addAll(previousButton, joinRoomButton, joinLabel, joinArea);
				joinScene.setFill(Color.web("#FFFDD0"));

				primaryStage.setScene(joinScene);
				primaryStage.show();
			}
		});

		return joinButton;

	}

	public Button createButton(Stage primaryStage) {

		Label createRoomLabel = new Label("Enter the Number of Players (6 at most):");

		createRoomLabel.setFont(new Font("Times New Roman", 30));
		createRoomLabel.setStyle("-fx-text-fill: #897361;");

		createRoomLabel.setTranslateX(200);
		createRoomLabel.setTranslateY(250);

		//Will create the server.
		Button createButton = new Button("CREATE A ROOM");

		createButton.setStyle("-fx-background-radius: 20px; -fx-text-fill: #897361; -fx-background-color: #EFA565;");
		createButton.setFont(new Font("Times New Roman", 20));
		createButton.setPrefWidth(250);

		createButton.setOnMouseEntered(event -> {
			createButton.setStyle("-fx-background-radius: 20px; -fx-text-fill: #EFA565; -fx-background-color: #897361;");
		});

		createButton.setOnMouseExited(event -> {
			createButton.setStyle("-fx-background-radius: 20px; -fx-text-fill: #897361; -fx-background-color: #EFA565;");
		});

		createButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				// TODO: Create a server with the IP of the host and a defined portNo
				// 		 After server is started display the play screen where the IP and port are shown
				//		 Ask for max number of players

				Group createRoomGroup = new Group();
				Scene createRoomScene = new Scene(createRoomGroup,900,700);

				Button previousButton = previousButton(primaryStage);

				TextArea numPlayers = new TextArea("");
				numPlayers.setFont(new Font("Times New Roman", 20));

				numPlayers.setPrefWidth(250);
				numPlayers.setPrefHeight(30);

				numPlayers.setTranslateX(320);
				numPlayers.setTranslateY(350);

				numPlayers.setStyle("-fx-control-inner-background: #EFA565; -fx-background-color: #EFA565; -fx-text-fill: #897361; ");
				numPlayers.setEditable(true);

				//Need to change it so that it will handle more than one person and keep track of turns.
				Button playButton = playButton(primaryStage, numPlayers);
				playButton.setTranslateY(550);
				playButton.setTranslateX(320);

				createRoomGroup.getChildren().addAll(createRoomLabel, numPlayers, previousButton, playButton);
				createRoomScene.setFill(Color.web("#FFFDD0"));

				primaryStage.setScene(createRoomScene);
				primaryStage.show();
			}
		});
		return createButton;
	}

	public Button profileButton(Stage primaryStage) {
		Button profileButton = new Button("PROFILE");
		profileButton.setStyle("-fx-background-radius: 20px; -fx-text-fill: #897361; -fx-background-color: #EFA565;");
		profileButton.setFont(new Font("Times New Roman", 20));
		profileButton.setPrefWidth(250);

		profileButton.setOnMouseEntered(event -> {
			profileButton.setStyle("-fx-background-radius: 20px; -fx-text-fill: #EFA565; -fx-background-color: #897361;");
		});

		profileButton.setOnMouseExited(event -> {
			profileButton.setStyle("-fx-background-radius: 20px; -fx-text-fill: #897361; -fx-background-color: #EFA565;");
		});

		profileButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				// TODO: Add logic for creating a room

				String playerUser = playerProfile.getUsername();
				//playerProfile.getDisplayName();

				Label playerUsername = new Label("Username:");

				playerUsername.setFont(new Font("Times New Roman", 34));
				playerUsername.setStyle("-fx-text-fill: #897361;");

				playerUsername.setPrefWidth(250);
				playerUsername.setPrefHeight(50);

				playerUsername.setTranslateX(250);
				playerUsername.setTranslateY(200);

				Label playerDisplay = new Label("Display Name:");

				playerDisplay.setFont(new Font("Times New Roman", 34));
				playerDisplay.setStyle("-fx-text-fill: #897361;");

				playerDisplay.setPrefWidth(250);
				playerDisplay.setPrefHeight(50);

				playerDisplay.setTranslateX(200);
				playerDisplay.setTranslateY(300);

				TextArea playerUsernameText = new TextArea();

				playerUsernameText.setFont(new Font("Times New Roman", 20));
				playerUsernameText.setStyle("-fx-text-fill: #897361;");

				playerUsernameText.setPrefWidth(150);
				playerUsernameText.setPrefHeight(playerUsernameText.getFont().getSize() * 2);

				playerUsernameText.setTranslateX(450);
				playerUsernameText.setTranslateY(200);

				playerUsernameText.setEditable(false);

				playerUsernameText.setStyle("-fx-control-inner-background: #EFA565; -fx-background-color: #EFA565; -fx-text-fill: #897361; ");

				TextArea playerDisplayText = new TextArea();

				playerDisplayText.setFont(new Font("Times New Roman", 20));
				playerDisplayText.setStyle("-fx-text-fill: #897361;");

				playerDisplayText.setPrefWidth(150);
				playerDisplayText.setPrefHeight(playerDisplayText.getFont().getSize() * 2);

				playerDisplayText.setTranslateX(450);
				playerDisplayText.setTranslateY(300);

				playerDisplayText.setEditable(false);

				playerDisplayText.setStyle("-fx-control-inner-background: #EFA565; -fx-background-color: #EFA565; -fx-text-fill: #897361; ");

				playerUsernameText.setText(playerProfile.getUsername());
				playerDisplayText.setText(playerProfile.getDisplayName());

				Group profileGroup = new Group();
				Scene profileScene = new Scene(profileGroup,900,700);

				Button previousButton = previousButton(primaryStage);

				profileGroup.getChildren().addAll(previousButton, playerUsername, playerDisplay, playerUsernameText, playerDisplayText);
				profileScene.setFill(Color.web("#FFFDD0"));

				primaryStage.setScene(profileScene);
				primaryStage.show();
			}
		});

		return profileButton;
	}

	public Button sendButton(Stage primaryStage) {

		Button send = new Button("Send");
		send.setTranslateX(400);
		send.setTranslateY(650);

		return send;
	}

	public void setLoggedInTrue() {
		this.loggedIn = true;
	}


	public void joinServer(int IP) {

	}

	public static void main(String[] args) {
		game.setPrompt();
		launch();
	}

}