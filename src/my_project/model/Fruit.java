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
        setNewImage("assets/Erbeere.png");
        strawberry = getMyImage();
        setNewImage("assets/Banane.png");
        bannana = getMyImage();


    }

    @Override
    public void draw(DrawTool drawTool) {
        super.draw(drawTool);
        if(fruitType.equals("Apple")){
            drawTool.setCurrentColor(255,0,0,255);
            drawTool.drawCircle(x,y,20);
        }
        if(fruitType.equals("Bannana")){
            drawTool.drawImage(bannana,x,y);
        }
        if(fruitType.equals("Strawberry")){
            drawTool.drawImage(strawberry,x,y);
        }
    }
}
