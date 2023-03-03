package goteamgo.AdLibStories;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class JavaFX extends Application {

	StackPane stackPane = new StackPane();
	Scene scene= new Scene(stackPane,550,700);
	Game game = new Game();
	
    @Override
    public void start(Stage primaryStage) throws Exception {
        // TODO Auto-generated method stub

        // Create the "Create a room" button
    	
    	
    	Button createButton = new Button("Create a room");
        createButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                // TODO: Add logic for creating a room
            	Group createRoomGroup = new Group();
                Scene addScene = new Scene(createRoomGroup,550,700);
                
                Button previousButton = new Button("Previous");
                previousButton.setTranslateX(450);
                previousButton.setTranslateY(600);
                
                previousButton.setOnAction(f->primaryStage.setScene(scene));
                
                createRoomGroup.getChildren().addAll(previousButton);
                
                
                primaryStage.setScene(addScene);
                primaryStage.show();
            }
        });

        // Create the "Join a room" button
        Button joinButton = new Button("Join a room");
        joinButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                // TODO: Add logic for joining a room
            	Group joinGroup = new Group();
                Scene addScene = new Scene(joinGroup,550,700);
                
                Button previousButton = new Button("Previous");
                previousButton.setTranslateX(450);
                previousButton.setTranslateY(600);
                
                previousButton.setOnAction(f->primaryStage.setScene(scene));
                
                joinGroup.getChildren().addAll(previousButton);
                
                
                primaryStage.setScene(addScene);
                primaryStage.show();
            }
        });
        
        Button loginButton = new Button("Login");
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                // TODO: Add logic for joining a room
            	Group loginGroup = new Group();
                Scene addScene = new Scene(loginGroup,550,700);
                
                Button previousButton = new Button("Previous");
                previousButton.setTranslateX(450);
                previousButton.setTranslateY(600);
                
                previousButton.setOnAction(f->primaryStage.setScene(scene));
                
                loginGroup.getChildren().addAll(previousButton);
                
                
                primaryStage.setScene(addScene);
                primaryStage.show();
            }
        });
        
        Button createAccountButton = new Button("Create an Account");
        createAccountButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                // TODO: Add logic for joining a room
               Group createAccountGroup = new Group();
               Scene addScene = new Scene(createAccountGroup,550,700);
               
               Button previousButton = new Button("Previous");
               previousButton.setTranslateX(450);
               previousButton.setTranslateY(600);
               
               previousButton.setOnAction(f->primaryStage.setScene(scene));
               
               createAccountGroup.getChildren().addAll(previousButton);
               
               
               primaryStage.setScene(addScene);
               primaryStage.show();
            }
        });
        
        //temporary
        Button playButton = new Button("PLAY");
        playButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
            	Group playGroup = new Group();
                Scene addScene = new Scene(playGroup,550,700);
                
                Button previousButton = new Button("Previous");
                previousButton.setTranslateX(450);
                previousButton.setTranslateY(600);
                
                previousButton.setOnAction(f->primaryStage.setScene(scene));
                
                
                playGroup.getChildren().addAll(previousButton);
                
                
                
                primaryStage.setScene(addScene);
                primaryStage.show();
            	
            }
        });
        
        joinButton.setTranslateY(50);
        //createButton.setTranslateY(50);
        loginButton.setTranslateY(-45);
        createAccountButton.setTranslateY(-95);
        playButton.setTranslateY(100);

        // Create a stack pane to center the buttons in the middle of the screen
        stackPane = new StackPane(createButton, joinButton,loginButton,createAccountButton, playButton);
        stackPane.setAlignment(Pos.CENTER);
        //stackPane.setSpacing(20);

        // Create the scene and set it on the stage
        scene = new Scene(stackPane, 550, 700);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Room GUI");
        primaryStage.show();
    }

    public void playGame() {
    	
    }
    
    @SuppressWarnings("exports")
	/*public Button previousButton(){
    	Button previousButton = new Button("Previous");
        previousButton.setOnAction(f->primaryStage.setScene(scene));
        
    }*/
    
    public static void main(String[] args) {
        launch();
    }

}