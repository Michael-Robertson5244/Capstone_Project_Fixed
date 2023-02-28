package AdLibStories.AdLibStories;

import javafx.application.Application;

/*import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
/*public class App extends Application {

    @Override
    public void start(Stage stage) {
        var javaVersion = SystemInfo.javaVersion();
        var javafxVersion = SystemInfo.javafxVersion();

        var label = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        var scene = new Scene(new StackPane(label), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}*/

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class JavaFX extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // TODO Auto-generated method stub

        // Create the "Create a room" button
        Button createButton = new Button("Create a room");
        createButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                // TODO: Add logic for creating a room
                System.out.println("Create a room button clicked");
            }
        });

        // Create the "Join a room" button
        Button joinButton = new Button("Join a room");
        joinButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                // TODO: Add logic for joining a room
                System.out.println("Join a room button clicked");
            }
        });

        // Create a stack pane to center the buttons in the middle of the screen
        StackPane stackPane = new StackPane(createButton, joinButton);
        stackPane.setAlignment(Pos.CENTER);
        //stackPane.setSpacing(20);

        // Create the scene and set it on the stage
        Scene scene = new Scene(stackPane, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Room GUI");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}