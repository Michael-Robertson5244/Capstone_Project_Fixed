package goteamgo.AdLibStories;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class JavaFX extends Application {

	StackPane stackPane = new StackPane();
	Scene scene = new Scene(stackPane,550,700);
	Game game = new Game();
	
    @Override
    public void start(Stage primaryStage) throws Exception {
        // TODO Auto-generated method stub

    	
        
    	// Create the "Create a room" button
    	
    	Button createButton = new Button("Create a room");
        createButton.setStyle("-fx-background-radius: 20px; -fx-text-fill: #2b5780; -fx-background-color: #efebe2;");
		
    	createButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                // TODO: Add logic for creating a room
            	Group createRoomGroup = new Group();
                Scene createRoomScene = new Scene(createRoomGroup,550,700);
                
                Button previousButton = new Button("Previous");
                previousButton.setTranslateX(450);
                previousButton.setTranslateY(600);
                
                previousButton.setOnAction(f->primaryStage.setScene(scene));
                previousButton.setStyle("-fx-background-radius: 20px; -fx-text-fill: #2b5780; -fx-background-color: #efebe2;");
                
                createRoomGroup.getChildren().addAll(previousButton);
                createRoomScene.setFill(new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE, new Stop(0, Color.web("#FFFDD0")), new Stop(1, Color.web("#F00000"))));
                
                primaryStage.setScene(createRoomScene);
                primaryStage.show();
            }
        });

        // Create the "Join a room" button
    	
        Button joinButton = new Button("Join a room");
        joinButton.setStyle("-fx-background-radius: 20px; -fx-text-fill: #2b5780; -fx-background-color: #efebe2;");
		
        joinButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                // TODO: Add logic for joining a room
            	Group joinGroup = new Group();
                Scene joinScene = new Scene(joinGroup,550,700);
                
                Button previousButton = new Button("Previous");
                previousButton.setTranslateX(450);
                previousButton.setTranslateY(600);
                
                previousButton.setOnAction(f->primaryStage.setScene(scene));
                previousButton.setStyle("-fx-background-radius: 20px; -fx-text-fill: #2b5780; -fx-background-color: #efebe2;");
                
                joinGroup.getChildren().addAll(previousButton);
                joinScene.setFill(new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE, new Stop(0, Color.web("#FFFDD0")), new Stop(1, Color.web("#F00000"))));
                
                primaryStage.setScene(joinScene);
                primaryStage.show();
            }
        });
        
        Button loginButton = new Button("Login");
        loginButton.setStyle("-fx-background-radius: 20px; -fx-text-fill: #2b5780; -fx-background-color: #efebe2;");
        
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                // TODO: Add logic for joining a room
            	Group loginGroup = new Group();
                Scene loginScene = new Scene(loginGroup,550,700);
                
                Button previousButton = new Button("Previous");
                previousButton.setTranslateX(450);
                previousButton.setTranslateY(600);
                
                previousButton.setOnAction(f->primaryStage.setScene(scene));
                previousButton.setStyle("-fx-background-radius: 20px; -fx-text-fill: #2b5780; -fx-background-color: #efebe2;");
                
                loginGroup.getChildren().addAll(previousButton);
                loginScene.setFill(new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE, new Stop(0, Color.web("#FFFDD0")), new Stop(1, Color.web("#F00000"))));
                
                primaryStage.setScene(loginScene);
                primaryStage.show();
            }
        });
        
        Button createAccountButton = new Button("Create an Account");
        createAccountButton.setStyle("-fx-background-radius: 20px; -fx-text-fill: #2b5780; -fx-background-color: #efebe2;");
        
        createAccountButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                // TODO: Add logic for joining a room
               Group createAccountGroup = new Group();
               Scene createAccountScene = new Scene(createAccountGroup,550,700);
               
               Button previousButton = new Button("Previous");
               previousButton.setTranslateX(450);
               previousButton.setTranslateY(600);
               
               previousButton.setOnAction(f->primaryStage.setScene(scene));
               previousButton.setStyle("-fx-background-radius: 20px; -fx-text-fill: #2b5780; -fx-background-color: #efebe2;");
               
               createAccountGroup.getChildren().addAll(previousButton);
               createAccountScene.setFill(new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE, new Stop(0, Color.web("#FFFDD0")), new Stop(1, Color.web("#F00000"))));
               
               primaryStage.setScene(createAccountScene);
               primaryStage.show();
            }
        });
        
        //temporary
        Button playButton = new Button("PLAY");
        playButton.setStyle("-fx-background-radius: 20px; -fx-text-fill: #2b5780; -fx-background-color: #efebe2;");
		
        playButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
            	Group playGroup = new Group();
                Scene playScene = new Scene(playGroup,550,700);
                
                Button previousButton = new Button("Previous");
                previousButton.setTranslateX(450);
                previousButton.setTranslateY(600);
                
                previousButton.setOnAction(f->primaryStage.setScene(scene));
                previousButton.setStyle("-fx-background-radius: 20px; -fx-text-fill: #2b5780; -fx-background-color: #efebe2;");
                
                playGroup.getChildren().addAll(previousButton);
                playScene.setFill(new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE, new Stop(0, Color.web("#FFFDD0")), new Stop(1, Color.web("#F00000"))));
                
                primaryStage.setScene(playScene);
                primaryStage.show();
            	
            }
        });
        
        joinButton.setTranslateY(50);
        createButton.setTranslateY(5);
        loginButton.setTranslateY(-45);
        createAccountButton.setTranslateY(-95);
        playButton.setTranslateY(100);

        // Create a stack pane to center the buttons in the middle of the screen
        stackPane = new StackPane(createButton, joinButton, loginButton, createAccountButton, playButton);
        stackPane.setAlignment(Pos.CENTER);

        // Create the scene and set it on the stage
        scene.setFill(new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE, new Stop(0, Color.web("#2b5780")), new Stop(1, Color.web("#54945b"))));
        scene = new Scene(stackPane, 550, 700);
        //scene.setFill(new LinearGradient(0, 0, 1, 1, true, CycleMethod.NO_CYCLE, new Stop(0, Color.web("#FFFDD0")), new Stop(1, Color.web("#F00000"))));
        primaryStage.setScene(scene);
        primaryStage.setTitle("Room GUI");
        primaryStage.show();
    }

    public void playGame() {
    	
    }
    
    public static void main(String[] args) {
        launch();
    }

}