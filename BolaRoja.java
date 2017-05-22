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
import javafx.scene.shape.Rectangle; 
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;

public class BolaRoja extends Application { 
    private int velocidadX = 10;
    private int velocidadY = 20;
    private int velocidadxRectangle = 1;

    public static void main(String args[]) { 
        launch(args) ; 
    } 

    @Override 
    public void start(Stage stage) { 

        Circle circle = new Circle() ;
        Rectangle rectangle = new Rectangle(150,450,150,30) ;
                rectangle.setFill(Color.BLUE) ; 

        Group root = new Group(circle , rectangle) ;
        Scene scene = new Scene(root, 500, 500) ;  
        Random randomNumbers = new Random();

        circle.setRadius(25.0f) ; 
        circle.setCenterX(25+randomNumbers.nextInt(350)) ;
        circle.setCenterY(25+randomNumbers.nextInt(350));

        Timeline keyframe = new Timeline(new KeyFrame(Duration.millis(10), 

                    new EventHandler<ActionEvent>() {
                        @Override public void handle(ActionEvent t) {

                            double xMin = circle.getBoundsInParent().getMinX();
                            double yMin = circle.getBoundsInParent().getMinY();
                            double xMax = circle.getBoundsInParent().getMaxX();
                            double yMax = circle.getBoundsInParent().getMaxY();

                            if (xMin < 0 || xMax > scene.getWidth()) {
                                velocidadX = velocidadX * -1;
                            }
                            if (yMin < 0 || yMax > scene.getHeight()) {
                                velocidadY = velocidadY * -1;
                            }

                            circle.setTranslateX(circle.getTranslateX() + velocidadX);
                            circle.setTranslateY(circle.getTranslateY() + velocidadY);
                            rectangle.setTranslateX(rectangle.getTranslateX() +velocidadxRectangle);
                        }
                    }));

        keyframe.setCycleCount(Timeline.INDEFINITE);
        stage.setTitle("Circle") ; 
        circle.setFill(Color.RED) ; 


        scene.setOnKeyPressed(event -> {             
                    if(event.getCode() == KeyCode.RIGHT ){
                        velocidadxRectangle =  1;
                    }
                    
                    else if(event.getCode() == KeyCode.LEFT){
                        velocidadxRectangle =  -1; //Cambiamos direccion del rectangulo
                    }
                  
                    rectangle.setTranslateX(rectangle.getTranslateX() + velocidadxRectangle);
                    event.consume();
                });
           

        stage.setScene(scene); 
        stage.show();
        keyframe.play();
        
    }
} 

