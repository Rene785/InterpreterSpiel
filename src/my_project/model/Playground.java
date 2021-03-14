package my_project.model;

import KAGO_framework.model.InteractiveGraphicalObject;
import KAGO_framework.view.DrawTool;

import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static my_project.Config.WINDOW_HEIGHT;
import static my_project.Config.WINDOW_WIDTH;

public class Playground extends InteractiveGraphicalObject {

    public Playground(){
        setNewImage("assets/gras.jpg");
    }

    @Override
    public void draw(DrawTool drawTool) {
        super.draw(drawTool);
        for(int i=0;i<WINDOW_WIDTH/50;i++){
            for(int j=0;j<WINDOW_HEIGHT/50;j++){
                drawTool.drawImage(getMyImage(),i*50,j*50);
                drawTool.drawRectangle(i*50,j*50,50,50);
            }
        }
        /*for(int i=0;i<WINDOW_WIDTH/50;i++){
            drawTool.drawLine(i,0,i,WINDOW_HEIGHT);
        }
        for(int i=0;i<WINDOW_HEIGHT/50;i++){
            drawTool.drawLine(0,i,WINDOW_WIDTH,i);
        }*/
    }

    @Override
    public void keyPressed(int key) {

    }

    @Override
    public void keyReleased(int key) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }
}
