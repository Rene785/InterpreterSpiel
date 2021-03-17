package my_project.model;

import my_project.control.OutputController;

public class Interpreter {

    private String status;
    private OutputController outputController;
    private String output;

    public Interpreter(OutputController outputController){
        this.outputController=outputController;
        status="do"; // do/check/doCond/jumpCond/readParam
        output="";
    }

    /**
     * Wird vom Parser nach jedem einzelnen Token aufgerufen.
     * @param type
     * @param value
     */
    public void interpret(String type,String value){
        if(status.equals("do")){
            if(type.equals("BEFEHL")){
                if(value.equals("vor")){
                    outputController.move();
                    output="Vorne - ";
                    if(outputController.crashed()){
                        status="verloren";
                        output="Kollision - ";
                    }
                }else if(value.equals("rechts")){
                    outputController.turnRigth();
                    output="Rechts - ";
                }else if(value.equals("links")){
                    outputController.turnLeft();
                    output="Links - ";
                }else if(value.equals("zurück")){
                    outputController.back();
                    output="Zurück - ";
                    if(outputController.crashed()){
                        status="verloren";
                        output="Kollision - ";
                    }
                }else if(value.equals("fressen")){
                    outputController.eat();
                }
            }
        }
    }
    //TODO "Verloren" weiter ausbauen. Der Parser soll in diesem Fall seine Handlung abbrechen.


}
