import javafx.application.Application; 
import javafx.scene.Group; 
import javafx.scene.Scene; 
import javafx.stage.Stage; 
import javafx.scene.shape.Circle; 
import javafx.scene.paint.Color; 

public class BolaRoja extends Application { 

    public static void main(String args[]) { 
        launch(args) ; 
    } 

    @Override 
    public void start(Stage stage) { 

        Circle circle = new Circle() ; 


      
        circle.setRadius(50.0f) ; 
        circle.setCenterX(250.0f);
        circle.setCenterY(250.0f);


        Group root = new Group(circle) ; 


        Scene scene = new Scene(root, 500, 500) ;  

        stage.setTitle("Circle") ; 
        circle.setFill(Color.RED) ;  



        stage.setScene(scene) ; 


        stage.show();
    }
} 

