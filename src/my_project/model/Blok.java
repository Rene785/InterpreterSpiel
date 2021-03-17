package my_project.model;

import KAGO_framework.model.GraphicalObject;
import KAGO_framework.view.DrawTool;

public class Blok extends GraphicalObject {

    private boolean head;
    private int rotationAngel;

    public Blok(boolean head,double x,double y){
        this.head =head;
        width=25;
        this.x=x;
        this.y=y;
        rotationAngel =0;
    }

    @Override
    public void draw(DrawTool drawTool) {
        super.draw(drawTool);
        if(this.head){
            drawTool.setCurrentColor(118,238,198,255);
            drawTool.drawFilledArc(x,y,width, rotationAngel,60);
        }else{
            drawTool.setCurrentColor(118,238,198,255);
            drawTool.drawFilledCircle(x,y,width);
        }
    }

    public void turn(String direction) {
        if(direction.equals("rigth")){
            rotationAngel+=270;
        }
        if(direction.equals("left")){
            rotationAngel+=90;
        }
    }

    public int getRotationAngel() {
        return rotationAngel;
    }

    public String getDirection(){
        switch (rotationAngel % 360){
            case 0:
                return "rechts";
            case 180:
                return "links";
            case 270:
                return "unten";
            case 90:
                return "oben";
        }
        return null;
    }

}
