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

public class BolaRoja extends Application { 
    public static void main(String args[]) { 
        launch(args) ; 
    } 

    @Override 
    public void start(Stage stage) { 

        Circle circle = new Circle() ; 
        final Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);

        circle.setRadius(50.0f) ; 
        circle.setCenterX(250.0f);
        circle.setCenterY(250.0f);

        final KeyFrame kreyframe = new KeyFrame(Duration.millis(20), 
                new EventHandler<ActionEvent>() {
                    @Override public void handle(ActionEvent t) {
                        circle.setCenterX(circle.getTranslateX() + circle.getCenterX());
                        circle.setCenterY(circle.getTranslateY() + circle.getCenterY());
                        circle.setTranslateX(5);
                        

                    }
                });

        timeline.getKeyFrames().add(kreyframe);
        Group root = new Group(circle) ; 

        Scene scene = new Scene(root, 500, 500) ;  

        stage.setTitle("Circle") ; 
        circle.setFill(Color.RED) ; 

        stage.setScene(scene); 
        stage.show();
        timeline.play();
    }
} 

