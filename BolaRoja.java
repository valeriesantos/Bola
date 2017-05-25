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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;;
import javafx.scene.control.Label;
public class BolaRoja extends Application { 
    private int velocidadX = 2;
    private int velocidadY = 2;
    private int velocidadxRectangle = 5;
    private static int RADIO = 20;
     private static int DIAMETRO = 40;
    private   Timeline keyframe;

    public static void main(String args[]) { 
        launch(args) ; 
    } 

    @Override 
    public void start(Stage stage) { 

        Circle circle = new Circle() ;
        Rectangle rectangle = new Rectangle(150,450,100,15) ;
        rectangle.setFill(Color.BLUE) ; 
        rectangle.setArcWidth(10);
        rectangle.setArcHeight(10);

        Image image = new Image("game-over.png");
        ImageView iv = new ImageView();

        Group root = new Group(circle , rectangle ,iv ) ;
        Scene scene = new Scene(root, 500, 500) ;  
        Random randomNumbers = new Random();

        circle.setRadius(RADIO) ; 
        circle.setCenterX(RADIO +randomNumbers.nextInt(350)) ;
        circle.setCenterY(RADIO +randomNumbers.nextInt(350));

        keyframe = new Timeline(new KeyFrame(Duration.millis(10), 
                new EventHandler<ActionEvent>() {
                    @Override public void handle(ActionEvent t) {

                        double xMin = circle.getBoundsInParent().getMinX();
                        double yMin = circle.getBoundsInParent().getMinY();
                        double xMax = circle.getBoundsInParent().getMaxX();
                        double yMax = circle.getBoundsInParent().getMaxY();
                        rectangle.setTranslateX(rectangle.getTranslateX() +velocidadxRectangle);

                        if (xMin < 0 || xMax > scene.getWidth()) {
                            velocidadX = velocidadX * -1;
                        }

                        if (yMin < 0) {
                            velocidadY = velocidadY * -1;
                        }

                        if(circle.getBoundsInParent().intersects(rectangle.getBoundsInParent())){
                            velocidadY = velocidadY * -1;
                        }

                        if( yMax > scene.getWidth() + DIAMETRO){
                            iv.setX(200);
                            iv.setY(200);
                            iv.setFitHeight(100);
                            iv.setFitWidth(100);
                            iv.setImage(image);  
                            keyframe.stop();

                        }
                        double xMinR = rectangle.getBoundsInParent().getMinX();
                        double xMaxR = rectangle.getBoundsInParent().getMaxX();

                        if(xMinR <= 0 || xMaxR  >= scene.getWidth()){
                            velocidadxRectangle = 0;                                

                        }
                        circle.setTranslateX(circle.getTranslateX() + velocidadX);
                        circle.setTranslateY(circle.getTranslateY() + velocidadY);
                    }
                }));

        keyframe.setCycleCount(Timeline.INDEFINITE);
        stage.setTitle("Circle") ; 
        circle.setFill(Color.RED) ; 

        scene.setOnKeyPressed(event -> {             
                if(event.getCode() == KeyCode.RIGHT )
                {

                    if(rectangle.getBoundsInParent().getMaxX()  <500  ){
                        velocidadxRectangle = 3;
                    }

                }

                else if(event.getCode() == KeyCode.LEFT)
                {

                    if(rectangle.getBoundsInParent().getMinX()  > 0){
                        velocidadxRectangle = -3;
                    }

                }
                rectangle.setTranslateX(rectangle.getTranslateX() + velocidadxRectangle);
                event.consume();
            });

        stage.setScene(scene); 
        stage.show();
        keyframe.play();

    }
} 

