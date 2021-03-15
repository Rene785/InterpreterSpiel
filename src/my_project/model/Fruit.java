package my_project.model;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;

public class Fruit extends GraphicalObject {

    private String fruitType;

    public Fruit(double x,double y,String type){
        this.x=x;
        this.y=y;
        fruitType=type;
    }

    @Override
    public void draw(DrawTool drawTool) {
        super.draw(drawTool);
        if(fruitType.equals("Apple")){
            drawTool.setCurrentColor(255,0,0,255);
            drawTool.drawCircle(x,y,20);
        }
    }
}
