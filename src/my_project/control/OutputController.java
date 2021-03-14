package my_project.control;

import KAGO_framework.control.ViewController;
import KAGO_framework.model.abitur.datenstrukturen.List;
import my_project.model.Blok;

import java.awt.image.BufferedImage;

public class OutputController {

    private ViewController viewController;
    private List<Blok> caterpillar;

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
            grow();
            turnLeft();
        }else{
            turnRigth();
            move();
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
