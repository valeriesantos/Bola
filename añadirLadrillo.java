
        ArrayList <Rectangle> ladrillo = new ArrayList<Rectangle>();
        Random posicionesLadrillos = new Random();

        for(int cont=0;cont<4;cont++){
            int randomX=posicionesLadrillos.nextInt(400);
            int posX=(randomX+70);
            int randomY=posicionesLadrillos.nextInt(150);
            int posY=(randomY+50);

            ladrillos.setX(posX);
            ladrillos.setY(posY);
            ladrillos.setWidth(50);
            ladrillos.setHeight(10);
            ladrillos.setArcHeight(20);
            ladrillos.setArcWidth(20);

            ladrillos.setFill(Color.GREEN);
            ladrillo.add(ladrillos);


            ladrillos.setTranslateX(ladrillos.getTranslateX());
              double pos = ladrillos.getBoundsInParent().getMaxX();
                        if(circle.getBoundsInParent().intersects(ladrillos.getBoundsInParent())){

                            ladrillo.remove(pos);
                            velocidadY = velocidadY * -1;

                        } 
        }
            root.getChildren().add(ladrillos);