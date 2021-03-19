package my_project.model;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;
import com.sun.prism.Image;

import java.awt.image.BufferedImage;

public class Fruit extends GraphicalObject {

    private String fruitType;
    private BufferedImage bannana;
    private BufferedImage strawberry;

    public Fruit(double x,double y,String type){
        this.x=x;
        this.y=y;
        fruitType=type;
        bannana=createImage("assets/Banane.png");
        strawberry=createImage("assets/Erbeere.png");

    }

    @Override
    public void draw(DrawTool drawTool) {
        super.draw(drawTool);
        if(fruitType.equals("Apple")){
            drawTool.setCurrentColor(255,0,0,255);
            drawTool.drawFilledCircle(x,y,20);
            drawTool.setCurrentColor(139,90,43,255);
            drawTool.drawFilledArc(x,y-20,7,0,210,false);
        }
        if(fruitType.equals("Bannana")){
            drawTool.drawImage(bannana,x-25,y);
        }
        if(fruitType.equals("Strawberry")){
            drawTool.drawImage(strawberry,x-25,y-25);
        }
    }
}
