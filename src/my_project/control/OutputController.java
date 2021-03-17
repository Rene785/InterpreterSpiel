package my_project.control;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.abitur.datenstrukturen.List;
import my_project.model.Blok;
import my_project.model.Fruit;

import java.awt.*;

import static my_project.Config.WINDOW_HEIGHT;
import static my_project.Config.WINDOW_WIDTH;

public class OutputController {

    private ViewController viewController;
    private List<Blok> caterpillar;
    private Fruit[] fruits;

    public OutputController(ViewController viewController){
        this.viewController=viewController;
        caterpillar=new List<>();
        caterpillar.append(new Blok(true,225,225));
        caterpillar.toFirst();
        while(caterpillar.hasAccess()){
            viewController.draw(caterpillar.getContent());
            caterpillar.next();
        }

    }

    public void test(boolean b){
        if(b){
            back();
            spreadFruits(5);
        }else{
            grow();
            move();
            turnRigth();
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
                fruits[i]=new Fruit(25+(int)(Math.random()*(WINDOW_WIDTH/50))*50,25+(int)(Math.random()*(WINDOW_HEIGHT/50))*50,"Apple");
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
            }
            grow();
        }
    }

    public void move() {
        caterpillar.toFirst();
        double pX=caterpillar.getContent().getX();
        double pY=caterpillar.getContent().getY();
        switch (caterpillar.getContent().getRotationAngel() % 360){
            case 0:
                caterpillar.getContent().setX(caterpillar.getContent().getX() + 50);//rechts
                break;
            case 180:
                caterpillar.getContent().setX(caterpillar.getContent().getX() - 50);//links
                break;
            case 270:
                caterpillar.getContent().setY(caterpillar.getContent().getY() + 50);//unten
                break;
            case 90:
                caterpillar.getContent().setY(caterpillar.getContent().getY() - 50);//oben
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
        Blok tmpBlok=caterpillar.getContent();
        caterpillar.next();
        while(caterpillar.hasAccess()){
            tmpBlok.setX(caterpillar.getContent().getX());
            tmpBlok.setY(caterpillar.getContent().getY());
            tmpBlok=caterpillar.getContent();
            caterpillar.next();
        }
        caterpillar.toFirst();
        switch (caterpillar.getContent().getRotationAngel() % 360){
            case 0:
                tmpBlok.setX(tmpBlok.getX() - 50);//rechts
                break;
            case 180:
                tmpBlok.setX(tmpBlok.getX() + 50);//links
                break;
            case 270:
                tmpBlok.setY(tmpBlok.getY() - 50);//unten
                break;
            case 90:
                tmpBlok.setY(tmpBlok.getY() + 50);//oben
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
        Blok blok = new Blok(false,caterpillar.getContent().getX()-50,caterpillar.getContent().getY());
        caterpillar.append(blok);
        viewController.draw(blok);
        /*caterpillar.toLast();
        System.out.println(caterpillar.getContent().getX()+" "+caterpillar.getContent().getY());*/
    }

    public void turnRigth(){
        caterpillar.toFirst();
        caterpillar.getContent().turn("rigth");
    }

    public void turnLeft(){
        caterpillar.toFirst();
        caterpillar.getContent().turn("left");
    }



}
