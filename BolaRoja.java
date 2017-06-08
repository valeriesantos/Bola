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
    private static final  int ANCHO_LADRILLO = 50;
    private static final int ALTO_LADRILLO = 20;
    private int ladrillosEliminados;
    private static final int ANCHO_ESCENA =500;
    private static final int LARGO_ESCENA =500;

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

        //añadir label con el numero de ladrillos rotos
        Label ladrillosRotos = new Label();
        ladrillosRotos.setTranslateX(250);
        //añadir label al final el juego
        Label winner = new Label();
        winner.setTranslateX(200); 
        winner.setTranslateY(250);

        //añadir elementos al grupo
        Group root = new Group(circle , rectangle ,iv, ladrillosRotos, winner) ;
        Scene scene = new Scene(root, 500, 500); // definimos escena

        //creamos la bola
        circle.setRadius(RADIO) ; 
        Random randomNumbers = new Random();
        circle.setCenterX(RADIO +randomNumbers.nextInt(350)) ;
        circle.setCenterY(RADIO +randomNumbers.nextInt(250));


        //primer ladrillo
        
        //primero creamos arraylist
        ArrayList <Rectangle> ladrillo = new ArrayList<Rectangle>();
        Random posicionesLadrillos = new Random();
        Rectangle ladrillos1 = new Rectangle();
       
        //definimos el ladrillo
        ladrillos1.setWidth(50);
        ladrillos1.setHeight(20);
        ladrillos1.setTranslateX(posicionesLadrillos.nextInt(500 - 50));
        ladrillos1.setTranslateY(posicionesLadrillos.nextInt(250));
        ladrillos1.setFill(Color.GREEN);
        ladrillos1.setStroke(Color.BLACK);
        ladrillos1.setArcHeight(20);
        ladrillos1.setArcWidth(20);
        ladrillo.add(ladrillos1);
        root.getChildren().add(ladrillos1);//añadimos al grupo el ladrillo

        for(int cont=0;cont<4;cont++){         
            //creamos ladrillo//
            Rectangle ladrillos2 = new Rectangle();
            ladrillos2.setWidth(ANCHO_LADRILLO);
            ladrillos2.setHeight(ALTO_LADRILLO);
            ladrillos2.setTranslateX(posicionesLadrillos.nextInt(ANCHO_ESCENA - ANCHO_LADRILLO));
            ladrillos2.setTranslateY(20 + posicionesLadrillos.nextInt(LARGO_ESCENA/2 - 100));
            ladrillos2.setFill(Color.GREEN);
            ladrillos2.setStroke(Color.BLACK);
            ladrillos2.setVisible(false);// de momento invisible 
            ladrillos2.setArcHeight(20);
            ladrillos2.setArcWidth(20);
            root.getChildren().add(ladrillos2);

            boolean ladrilloCorrecto = true;
            for(cont = 0;cont<ladrillo.size();cont++){  //recorremos arraylist hasta encontrar ladrillo valido
                Shape shape = Shape.intersect(ladrillo.get(cont),ladrillos2);
                double ancho = shape.getBoundsInParent().getWidth();

                if(ancho != -1){ //si se solapan no lo pintamos
                    ladrilloCorrecto  = false;
                }

            }

            if(ladrilloCorrecto){ //si los ladrillos no se superponen los hacemos visibles y los pintamos.
                ladrillo.add(ladrillos2);//añadimos al arraylist
                ladrillos2.setVisible(true);//y hacemos visible
            }

        }

        tiempoEnSegundos = 0; //inicializar el tiempo
        
        Label tiempoPasado = new Label("0");
        root.getChildren().add(tiempoPasado);

        

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


                        for(int contador = 0;contador<ladrillo.size();contador++){

                            if(circle.getBoundsInParent().intersects(ladrillo.get(contador).getBoundsInParent())){
                                velocidadY = velocidadY * -1;

                                ladrillo.remove(contador).setVisible(false);
                                ladrillosEliminados++;
                                                        ladrillosRotos.setText("Ladrillos eliminados : " +  ladrillosEliminados );
                            } 

                            if(ladrillo.size() == 0 ){
                                winner.setText("YOU WIN!!");
                                keyframe.stop();
                            }

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
                if(event.getCode() == KeyCode.RIGHT )//accion al pulsar tecla derecha
                {

                    if(rectangle.getBoundsInParent().getMaxX()  <500  ){
                        velocidadxRectangle = 3;
                    }

                }

                else if(event.getCode() == KeyCode.LEFT)//accion al pulsar tecla izq
                {

                    if(rectangle.getBoundsInParent().getMinX()  > 0){
                        velocidadxRectangle = -3;
                    }

                }
                rectangle.setTranslateX(rectangle.getTranslateX() + velocidadxRectangle);
                event.consume();
            });

        

        TimerTask tarea = new TimerTask() {
                @Override
                public void run() {
                    tiempoEnSegundos++;
                }                        
            };
            
        Timer timer = new Timer();
        timer.schedule(tarea, 0, 1000);
        stage.setScene(scene); 
        stage.show();
        keyframe.play();

    }

}

