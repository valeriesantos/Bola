import javafx.application.Application; 
import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.stage.Stage; 
import javafx.scene.shape.Circle; 
import javafx.scene.paint.Color; 
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.lang.Object;
import javafx.util.Duration;
import javafx.scene.transform.Translate;
import javafx.animation.AnimationTimer;
import javafx.scene.control.Button;

public class BolaRoja extends Application { 
    public static void main(String args[]) { 
        launch(args) ; 
    } 

    @Override 
    public void start(Stage stage) { 

        Circle circle = new Circle() ; 


        Group root = new Group(circle) ;

        circle.setRadius(50.0f) ; 
        circle.setCenterX(250.0f);
        circle.setCenterY(250.0f);
        Button button = new Button("Pulsar para parar");
        root.getChildren().add(button);

        Timeline keyframe = new Timeline(new KeyFrame(Duration.millis(100), 
                    new EventHandler<ActionEvent>() {
                        @Override public void handle(ActionEvent t) {

                            circle.setTranslateX(circle.getTranslateX() + 20 );
                            circle.setTranslateY(circle.getTranslateY() + 20);
                        }
                    }));

        button.setOnAction(event -> {
                keyframe.stop();

            });

        keyframe.setCycleCount(Timeline.INDEFINITE);

        button.setLayoutX(0);
        button.setLayoutY(50);

        Scene scene = new Scene(root, 500, 500) ;  
        stage.setTitle("Circle") ; 
        circle.setFill(Color.RED) ; 

        stage.setScene(scene); 
        stage.show();
        keyframe.play();
    }
} 

