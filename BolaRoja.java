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
import javafx.util.Duration;
import javafx.scene.Node;
import java.util.TimerTask;
import java.util.Timer;
import java.util.ArrayList;
import javafx.scene.shape.Shape;

public class BolaRoja extends Application { 
    private int velocidadX = 2;
    private int velocidadY = 2;
    private int velocidadxRectangle = 5;
    private static int RADIO = 20;
    private static int DIAMETRO = 40;
    private   Timeline keyframe;   
    private static final int STAR_COUNT = 20000;
    private final Rectangle[] nodes = new Rectangle[STAR_COUNT];
    private final double[] angles = new double[STAR_COUNT];
    private final long[] start = new long[STAR_COUNT];
    private final Random random = new Random();
    private int tiempoEnSegundos;

    public static void main(String args[]) { 
        launch(args) ; 
    } 

    @Override 
    public void start(Stage stage) { 

        Circle circle = new Circle() ;

        Image image = new Image("game-over.png");
        ImageView iv = new ImageView();

        Rectangle rectangle = new Rectangle(150,450,100,15) ; //plataforma

        //plataforma// 
        rectangle.setFill(Color.BLUE) ; 
        rectangle.setArcWidth(10);
        rectangle.setArcHeight(10);

        Group root = new Group(circle , rectangle ,iv) ;
        Scene scene = new Scene(root, 500, 500);
        Random randomNumbers = new Random();

        //creamos la bola
        circle.setRadius(RADIO) ; 
        circle.setCenterX(RADIO +randomNumbers.nextInt(350)) ;
        circle.setCenterY(RADIO +randomNumbers.nextInt(350));

        ArrayList <Rectangle> ladrillo = new ArrayList<Rectangle>();
        Random posicionesLadrillos = new Random();

        //primer ladrillo

        Rectangle ladrillos1 = new Rectangle();

        ladrillos1.setWidth(50);
        ladrillos1.setHeight(10);
        ladrillos1.setTranslateX(posicionesLadrillos.nextInt(500 - 50));
        ladrillos1.setTranslateY(posicionesLadrillos.nextInt(250));
        ladrillos1.setFill(Color.GREEN);
        ladrillos1.setStroke(Color.BLACK);
        ladrillos1.setArcHeight(20);
        ladrillos1.setArcWidth(20);
        ladrillo.add(ladrillos1);
        root.getChildren().add(ladrillos1);

        for(int cont=0;cont<4;cont++){         
            //creamos ladrillos//
            Rectangle ladrillos2 = new Rectangle();
            ladrillos2.setWidth(50);
            ladrillos2.setHeight(10);
            ladrillos2.setTranslateX(posicionesLadrillos.nextInt(500 - 50));
            ladrillos2.setTranslateY(posicionesLadrillos.nextInt(250));
            ladrillos2.setFill(Color.GREEN);
            ladrillos2.setStroke(Color.BLACK);
            ladrillos2.setVisible(false);
            ladrillos2.setArcHeight(20);
            ladrillos2.setArcWidth(20);
            root.getChildren().add(ladrillos2);

            boolean ladrilloCorrecto = true;
            for(cont = 0;cont<ladrillo.size();cont++){  //recorremos arraylist 
                Shape shape = Shape.intersect(ladrillo.get(cont),ladrillos2);
                double ancho = shape.getBoundsInParent().getWidth();
                System.out.println(ancho);

                if(ancho != -1){ //si se solapan no lo pintamos
                    ladrilloCorrecto  = false;
                }
               
            }

            if(ladrilloCorrecto == true ){ //si los ladrillos no se superponen los hacemos visibles y los pintamos.
                ladrillo.add(ladrillos2);
                ladrillos2.setVisible(true);

            }

        }

        Label tiempoPasado = new Label("0");

        root.getChildren().add(tiempoPasado);

        tiempoEnSegundos = 0;

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

                        // Actualizamos la etiqueta del tiempo
                        int minutos = tiempoEnSegundos / 60;
                        int segundos = tiempoEnSegundos % 60;
                        tiempoPasado.setText(minutos + ":" + segundos);

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

        TimerTask tarea = new TimerTask() {
                @Override
                public void run() {
                    tiempoEnSegundos++;
                }                        
            };
        Timer timer = new Timer();
        timer.schedule(tarea, 0, 1000);

    }

}

