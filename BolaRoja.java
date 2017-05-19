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
import javafx.animation.Animation;
import java.util.Random;


public class BolaRoja extends Application { 
    static int dx = 1;
    static int dy = 1;
    
    public static void main(String args[]) { 
        launch(args) ; 
    } 

    @Override 
    public void start(Stage stage) { 

        Circle circle = new Circle() ;
        Group root = new Group(circle) ;
        Scene scene = new Scene(root, 500, 500) ;  
        Random randomNumbers = new Random();
        
        circle.setRadius(25.0f) ; 
        circle.setCenterX(25+randomNumbers.nextInt(350)) ;
        circle.setCenterY(25 +randomNumbers.nextInt(350));
        
        Timeline keyframe = new Timeline(new KeyFrame(Duration.millis(1), 

        
                    new EventHandler<ActionEvent>() {
                        @Override public void handle(ActionEvent t) {

                            double xMin = circle.getBoundsInParent().getMinX();
                            double yMin = circle.getBoundsInParent().getMinY();
                            double xMax = circle.getBoundsInParent().getMaxX();
                            double yMax = circle.getBoundsInParent().getMaxY();

                            if (xMin < 0 || xMax > scene.getWidth()) {
                                dx = dx * -1;
                            }
                            if (yMin < 0 || yMax > scene.getHeight()) {
                                dy = dy * -1;
                            }

                            circle.setTranslateX(circle.getTranslateX() + dx);
                            circle.setTranslateY(circle.getTranslateY() + dy);

                        }
                    }));

        keyframe.setCycleCount(Timeline.INDEFINITE);


        stage.setTitle("Circle") ; 
        circle.setFill(Color.RED) ; 

        stage.setScene(scene); 
        stage.show();
        keyframe.play();
    }
} 

