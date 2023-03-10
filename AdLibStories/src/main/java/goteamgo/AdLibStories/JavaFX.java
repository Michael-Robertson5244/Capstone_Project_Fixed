package goteamgo.AdLibStories;

import java.awt.ScrollPane;
import java.awt.TextField;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.swing.JScrollPane;

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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class JavaFX extends Application {

	StackPane stackPane = new StackPane();
	Scene scene = new Scene(stackPane,900,700);
	
	Game game = new Game();
	
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
    	
    	Button createButton = new Button("CREATE A ROOM");
       
    	createButton.setStyle("-fx-background-radius: 20px; -fx-text-fill: #897361; -fx-background-color: #EFA565;");
    	createButton.setFont(new Font("Times New Roman", 20));
    	createButton.setPrefWidth(250);
    	
    	createButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                // TODO: Add logic for creating a room
            	Group createRoomGroup = new Group();
                Scene createRoomScene = new Scene(createRoomGroup,900,700);
                
                Button previousButton = new Button("Previous");
                previousButton.setTranslateX(800);
                previousButton.setTranslateY(650);
                
                previousButton.setOnAction(f->primaryStage.setScene(scene));
                previousButton.setStyle("-fx-background-radius: 20px; -fx-text-fill: #897361; -fx-background-color: #EFA565;");
                
                createRoomGroup.getChildren().addAll(previousButton);
                createRoomScene.setFill(Color.web("#FFFDD0"));
                
                primaryStage.setScene(createRoomScene);
                primaryStage.show();
            }
        });

        // Create the "Join a room" button
    	
        Button joinButton = new Button("JOIN A ROOM");
        
        joinButton.setStyle("-fx-background-radius: 20px; -fx-text-fill: #897361; -fx-background-color: #EFA565;");
		joinButton.setFont(new Font("Times New Roman", 20));
		joinButton.setPrefWidth(250);
		
        joinButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                // TODO: Add logic for joining a room
            	Group joinGroup = new Group();
                Scene joinScene = new Scene(joinGroup,900,700);
                
                Label joinLabel = new Label("Enter Room Code:");
                joinLabel.setFont(new Font("Times New Roman", 30));
                
                joinLabel.setTranslateX(322);
                joinLabel.setTranslateY(250);
                
                TextArea joinArea = new TextArea("room code goes here");
                
                joinArea.setPrefWidth(200);
                joinArea.setPrefHeight(50);
                
                joinArea.setTranslateX(335);
                joinArea.setTranslateY(330);
                
                Button joinRoomButton = new Button("JOIN ROOM");
                joinRoomButton.setStyle("-fx-background-radius: 25px; -fx-text-fill: #897361; -fx-background-color: #EFA565;");
                joinRoomButton.setFont(new Font("Times New Roman", 20));
                
                joinRoomButton.setPrefWidth(200);
                
                joinRoomButton.setTranslateX(335);
                joinRoomButton.setTranslateY(430);
            
                Button previousButton = new Button("Previous");
                previousButton.setTranslateX(800);
                previousButton.setTranslateY(650);
                
                previousButton.setOnAction(f->primaryStage.setScene(scene));
                previousButton.setStyle("-fx-background-radius: 20px; -fx-text-fill: #897361; -fx-background-color: #EFA565;");
                
                joinGroup.getChildren().addAll(previousButton, joinRoomButton, joinLabel, joinArea);
                joinScene.setFill(Color.web("#FFFDD0"));
                
                primaryStage.setScene(joinScene);
                primaryStage.show();
            }
        });
        
        Button loginButton = new Button("LOGIN");
       
        loginButton.setStyle("-fx-background-radius: 20px; -fx-text-fill: #897361; -fx-background-color: #EFA565;");
        loginButton.setFont(new Font("Times New Roman", 20));
        loginButton.setPrefWidth(250);
        
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                // TODO: Add logic for joining a room
            	
            	Group loginGroup = new Group();
                Scene loginScene = new Scene(loginGroup,900,700);
                
                Button loginButton = new Button("LOGIN");
                loginButton.setStyle("-fx-background-radius: 25px; -fx-text-fill: #897361; -fx-background-color: #EFA565;");
                
                loginButton.setTranslateX(370);
                loginButton.setTranslateY(500);
                
                loginButton.setPrefWidth(200);
                
                loginButton.setFont(new Font("Times New Roman", 25));
                
                Label loginTitle = new Label("LOGIN");
                
                loginTitle.setFont(new Font("Times New Roman", 50));
                
                loginTitle.setTranslateX(370);
                loginTitle.setTranslateY(100);
                
                Label username = new Label("USERNAME:");
                
                username.setTranslateX(250);
                username.setTranslateY(265);
                
                username.setFont(new Font("Times New Roman", 25));
                
                TextArea userText = new TextArea();
                
                userText.setPrefWidth(250);
            	userText.setPrefHeight(50);
            	
            	userText.setTranslateX(425);
            	userText.setTranslateY(250);
                
                Label password = new Label("PASSWORD:");
                
                password.setFont(new Font("Times New Roman", 25));
                
                password.setTranslateX(250);
                password.setTranslateY(365);
                
                TextArea passText = new TextArea();
                
                passText.setPrefWidth(250);
            	passText.setPrefHeight(50);
            	
            	passText.setTranslateX(425);
            	passText.setTranslateY(350);
                
                Button previousButton = new Button("Previous");
                previousButton.setTranslateX(800);
                previousButton.setTranslateY(650);
                
                previousButton.setOnAction(f->primaryStage.setScene(scene));
                previousButton.setStyle("-fx-background-radius: 20px; -fx-text-fill: #897361; -fx-background-color: #EFA565;");
                
                loginGroup.getChildren().addAll(previousButton, loginTitle, username, password, userText, passText, loginButton);
               
                loginScene.setFill(Color.web("#FFFDD0"));
                
                primaryStage.setScene(loginScene);
                primaryStage.show();
            }
        });
        
        Button createAccountButton = new Button("CREATE AN ACCOUNT");
        
        createAccountButton.setStyle("-fx-background-radius: 20px; -fx-text-fill: #897361; -fx-background-color: #EFA565;");
        createAccountButton.setFont(new Font("Times New Roman", 20));
        createAccountButton.setPrefWidth(250);
        
        createAccountButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                // TODO: Add logic for joining a room
               Group createAccountGroup = new Group();
               Scene createAccountScene = new Scene(createAccountGroup,900,700);
               
               Button previousButton = new Button("Previous");
               previousButton.setTranslateX(800);
               previousButton.setTranslateY(650);
               
               previousButton.setOnAction(f->primaryStage.setScene(scene));
               previousButton.setStyle("-fx-background-radius: 20px; -fx-text-fill: #897361; -fx-background-color: #EFA565;");
               
               createAccountGroup.getChildren().addAll(previousButton);
               createAccountScene.setFill(Color.web("#FFFDD0"));
               
               primaryStage.setScene(createAccountScene);
               primaryStage.show();
            }
        });
        
        Button playButton = new Button("PLAY");
        
        playButton.setStyle("-fx-background-radius: 20px; -fx-text-fill: #897361; -fx-background-color: #EFA565;");
		playButton.setFont(new Font("Times New Roman", 20));
		playButton.setPrefWidth(250);
		
        playButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {	
            	Group playGroup = new Group();
                Scene playScene = new Scene(playGroup,900,700);
            	
            	//game.setPrompt();
            	
            	Label playerNames = new Label("Player Names");
            	
            	playerNames.setTranslateX(55);
            	playerNames.setTranslateY(150);
            	
            	playerNames.setFont(new Font("Times New Roman", 20));
            	
            	TextArea storyTextArea = new TextArea("story line goes here");
            	storyTextArea.setFont(new Font("Times New Roman", 20));
            	storyTextArea.setStyle("-fx-control-inner-background: #EFA565; -fx-background-color: #EFA565; -fx-text-fill: #897361; ");
            	
            	storyTextArea.setPrefWidth(450);
            	storyTextArea.setPrefHeight(350);
            	
            	storyTextArea.setTranslateX(225);
            	storyTextArea.setTranslateY(150);
            	
            	storyTextArea.setEditable(false);
            	
            	
            	TextArea promptText = new TextArea("prompt goes here");
            	//promptText.setText(game.getPrompt());
            	
            	promptText.setFont(new Font("Times New Roman", 20));
            	
            	promptText.setPrefWidth(450);
            	promptText.setPrefHeight(50);
            	
            	promptText.setTranslateX(225);
            	promptText.setTranslateY(75);
            	
            	promptText.setStyle("-fx-control-inner-background: #EFA565; -fx-background-color: #EFA565; -fx-text-fill: #897361; ");
            	
            	promptText.setEditable(false);
            	
            	TextArea storyEntryTextArea = new TextArea("write story here");
            	storyEntryTextArea.setFont(new Font("Times New Roman", 20));
            	storyEntryTextArea.setStyle("-fx-control-inner-background: #EFA565; -fx-background-color: #EFA565; -fx-text-fill: #897361; ");
            	
            	storyEntryTextArea.setPrefWidth(450);
            	storyEntryTextArea.setPrefHeight(50);
            	
            	storyEntryTextArea.setTranslateX(225);
            	storyEntryTextArea.setTranslateY(520);
            	
            	Label promptLabel = new Label("Prompt:");
            	promptLabel.setFont(new Font("Times New Roman", 20));
            	
            	promptLabel.setTranslateX(140);
            	promptLabel.setTranslateY(85);
            	
                Button previousButton = new Button("Previous");
                previousButton.setTranslateX(800);
                previousButton.setTranslateY(650);
                
                previousButton.setOnAction(f->primaryStage.setScene(scene));
                previousButton.setStyle("-fx-background-radius: 20px; -fx-text-fill: #897361; -fx-background-color: #EFA565;");
                
                playGroup.getChildren().addAll(previousButton, promptText, promptLabel, storyTextArea, storyEntryTextArea, playerNames);
                playScene.setFill(Color.web("#FFFDD0"));
                
                primaryStage.setScene(playScene);
                primaryStage.show();
            	
            }
            
        });
        
        imageView.setTranslateY(-150);
        joinButton.setTranslateY(140);
        createButton.setTranslateY(90);
        loginButton.setTranslateY(40);
        createAccountButton.setTranslateY(-10);
        playButton.setTranslateY(190);

        // Create a stack pane to center the buttons in the middle of the screen
       
        stackPane = new StackPane(createButton, joinButton, loginButton, createAccountButton, playButton, imageView);
        stackPane.setBackground(new Background(new BackgroundFill(Color.web("#FFFDD0"), CornerRadii.EMPTY, Insets.EMPTY)));

        stackPane.setAlignment(Pos.CENTER);
        
        scene = new Scene(stackPane, 900, 700);
       
        primaryStage.setScene(scene);
        //scene.setFill(Color.web("#FFFDD0"));
        primaryStage.setTitle("Room GUI");
        primaryStage.show();
    }

    public void playGame() {
    	
    }
    
    public static void main(String[] args) {
        launch();
    }

}