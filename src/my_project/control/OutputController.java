package my_project.control;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.abitur.datenstrukturen.List;
import my_project.model.Block;
import my_project.model.Fruit;

import static my_project.Config.WINDOW_HEIGHT;
import static my_project.Config.WINDOW_WIDTH;

public class OutputController {

    private ViewController viewController;
    private List<Block> caterpillar;
    private Fruit[] fruits;
    private String richtung;

    public OutputController(ViewController viewController){
        this.viewController=viewController;
        caterpillar=new List<>();
        caterpillar.append(new Block(true,225,225));
        caterpillar.toFirst();
        while(caterpillar.hasAccess()){
            viewController.draw(caterpillar.getContent());
            caterpillar.next();
        }
        richtung = "rechts";
        spreadFruits(5);

    }

    public void test(boolean b){
        if(b){
            back();
            if(inSicht(3)) {
                spreadFruits(5);
            }
        }else{
            grow();
            move();
            turnRight();
            if(crashed()){
                //System.out.println("Bum bum");
            }
        }
    }

    public void spreadFruits(int amount){
        if(amount>-1) {
            if(fruits!=null && fruits.length>0){
                for(int i=0;i<fruits.length;i++){
                    viewController.removeDrawable(fruits[i]);
                }
            }
            fruits = new Fruit[amount];
            for(int i=0;i<amount;i++){
                String fruitType="Apple";
                double w=Math.random();
                if(w>=0.67){
                    fruitType="Bannana";
                }else if(w>=0.34){
                    fruitType="Strawberry";
                }
                System.out.println(w+" "+fruitType);
                fruits[i]=new Fruit(25+(int)(Math.random()*(WINDOW_WIDTH/50))*50,25+(int)(Math.random()*(WINDOW_HEIGHT/50))*50,fruitType);
                viewController.draw(fruits[i]);
            }
        }
    }


    /**
     * Falls der Kopf bzw. das erste Objekt vonder Liste caterpillar die selbe X/Y Koordinaten
     * wie ein Objekt aus dem Array fruits, dann wird das Objekt der KLasse frutis gelöscht,
     * nicht mehr gezeichnet und die Raupe wäxhst. grow() wird aufgerufen
     */
    public void eat(){
        caterpillar.toFirst();
        for (int i = 0; i<= fruits.length-1; i++){
            if(fruits[i].collidesWith(caterpillar.getContent())){
                viewController.removeDrawable(fruits[i]);
                grow();
            }
        }
    }

    public void move() {
        caterpillar.toFirst();
        double pX=caterpillar.getContent().getX();
        double pY=caterpillar.getContent().getY();
        switch (caterpillar.getContent().getRotationAngel() % 360){
            case 0:
                caterpillar.getContent().setX(caterpillar.getContent().getX() + 50);//rechts
                richtung = "rechts";
                break;
            case 180:
                caterpillar.getContent().setX(caterpillar.getContent().getX() - 50);//links
                richtung = "links";
                break;
            case 270:
                caterpillar.getContent().setY(caterpillar.getContent().getY() + 50);//unten
                richtung = "unten";
                break;
            case 90:
                caterpillar.getContent().setY(caterpillar.getContent().getY() - 50);//oben
                richtung = "oben";
                break;
        }
        if(caterpillar.getContent().getX()>WINDOW_WIDTH){
            caterpillar.getContent().setX(25);
        }
        if(caterpillar.getContent().getX()<0){
            caterpillar.getContent().setX(WINDOW_WIDTH-25);
        }
        if(caterpillar.getContent().getY()>WINDOW_HEIGHT){
            caterpillar.getContent().setY(25);
        }
        if(caterpillar.getContent().getY()<0){
            caterpillar.getContent().setY(WINDOW_HEIGHT-25);
        }
        caterpillar.next();
        while(caterpillar.hasAccess()){
            double tX=caterpillar.getContent().getX();
            double tY=caterpillar.getContent().getY();
            caterpillar.getContent().setX(pX);
            caterpillar.getContent().setY(pY);
            pX=tX;
            pY=tY;
            caterpillar.next();
        }
    }

    public void back(){
        caterpillar.toFirst();
        Block tmpBlock =caterpillar.getContent();
        caterpillar.next();
        while(caterpillar.hasAccess()){
            tmpBlock.setX(caterpillar.getContent().getX());
            tmpBlock.setY(caterpillar.getContent().getY());
            tmpBlock =caterpillar.getContent();
            caterpillar.next();
        }
        caterpillar.toFirst();
        switch (caterpillar.getContent().getDirection()){
            case "rechts":
                tmpBlock.setX(tmpBlock.getX() - 50);//rechts
                break;
            case "links":
                tmpBlock.setX(tmpBlock.getX() + 50);//links
                break;
            case "unten":
                tmpBlock.setY(tmpBlock.getY() - 50);//unten
                break;
            case "oben":
                tmpBlock.setY(tmpBlock.getY() + 50);//oben
                break;
        }
    }

    public boolean crashed(){
        boolean answer=false;
        caterpillar.toFirst();
        double actX=caterpillar.getContent().getX();
        double actY=caterpillar.getContent().getY();
        caterpillar.next();
        while(caterpillar.hasAccess() && !answer){
            if(caterpillar.getContent().getX()==actX && caterpillar.getContent().getY()==actY){
                answer=true;
            }
            caterpillar.next();
        }
        return answer;
    }
    /**
     * Fügt einen neuen Block zur Raupenliste hinzu.
     */
    public void grow(){
        caterpillar.toLast();
        Block block = new Block(false,caterpillar.getContent().getX()-50,caterpillar.getContent().getY());
        caterpillar.append(block);
        viewController.draw(block);
        /*caterpillar.toLast();
        System.out.println(caterpillar.getContent().getX()+" "+caterpillar.getContent().getY());*/
    }

    public void turnRight(){
        caterpillar.toFirst();
        caterpillar.getContent().turn("rigth");
    }

    public void turnLeft(){
        caterpillar.toFirst();
        caterpillar.getContent().turn("left");
    }

    public boolean inSicht(int sichtweite) {
        if(sichtweite<=0){
            return false;
        }
        caterpillar.toFirst();
        String richtung=caterpillar.getContent().getDirection();
        for (int i = 0; i < fruits.length; i++) {
            double n = 0;
            if (richtung.equals("rechts")) {
                for (int j = 1; j <= sichtweite; j++) {
                    if (caterpillar.getContent().getX() +j*50 == fruits[i].getX() && caterpillar.getContent().getY()==fruits[i].getY()) {
                        return true;
                    }
                }
            }
            if (richtung.equals("links")) {
                for (int j = 1; j <= sichtweite; j++) {
                    if (caterpillar.getContent().getX() - j*50 == fruits[i].getX() && caterpillar.getContent().getY()==fruits[i].getY()) {
                        return true;
                    }
                }
            }
            if (richtung.equals("oben")) {

                for (int j = 1; j <= sichtweite; j++) {
                    if (caterpillar.getContent().getX()==fruits[i].getX() && caterpillar.getContent().getY() - 50*j == fruits[i].getY()){
                        return true;
                    }
                }
            }
            if (richtung.equals("unten")) {
                for (int j = 1; j <= sichtweite; j++) {
                    if (caterpillar.getContent().getX()==fruits[i].getX() && caterpillar.getContent().getY() + j*50 == fruits[i].getY()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void neuStart(){
        for (int i = 0; i< fruits.length-1; i++){
            viewController.removeDrawable(fruits[i]);
        }
        fruits = new Fruit[5];
        caterpillar.toFirst();
        Block tmp = caterpillar.getContent();
        while(caterpillar.hasAccess()){
            viewController.removeDrawable(caterpillar.getContent());
            caterpillar.remove();
        }
        caterpillar.insert(tmp);
        caterpillar.toFirst();
        caterpillar.getContent().setX(225);
        caterpillar.getContent().setY(225);
        viewController.draw(caterpillar.getContent());
    }


}





