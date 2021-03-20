package my_project.model;

import KAGO_framework.model.abitur.datenstrukturen.Queue;
import KAGO_framework.model.abitur.datenstrukturen.Stack;
import my_project.control.OutputController;

public class Interpreter {

    private String status;
    private boolean warteAufParameter;
    private OutputController outputController;
    private String output;
    private Stack<Queue<String[]>> streamStack;
    private Stack<Integer> ebenenStack;
    private Queue<String[]> streamQueue;
    private int ebene,notwEbene,loopCount;
    private boolean startLoop,needParam;

    public Interpreter(OutputController outputController){
        this.outputController=outputController;
        status="do"; // do/check/jumpCond/doLoop
        output="";
        streamStack =new Stack<>();
        streamStack.push(new Queue<>());
        ebene=0;
        notwEbene= Integer.MAX_VALUE;
        startLoop=false;
        loopCount=0;
        ebenenStack=new Stack<>();
        streamQueue=new Queue<>();

    }

    public void addOrder(String value,String type){
        String[] s=new String[]{value,type};
        streamQueue.enqueue(s);

    }

    public void run(){
        run(streamQueue);
    }

    private void run(Queue<String[]> orderQueue){
        boolean firstLoopRun=false;
        Queue<String[]> tmp=new Queue<>();
        while(!orderQueue.isEmpty()){// Solange die Schlange Eingaben enthält
            if(ebene<=notwEbene) {  //Falls man die Eingabe nciht überspringen soll
                notwEbene=Integer.MAX_VALUE;
                switch (orderQueue.front()[1]) {//Selektiere Eingabentyp
                    case "START":
                        System.out.println("1111");
                        ebene++;
                        break;
                    case "ENDE":
                        System.out.println("2222");
                        ebene--;
                        if(firstLoopRun){//Falls eine Schleife das erste mal durchgegenagen wird:
                            // Die gespeicherten Befehle werden in einem Selbsaufruf der Methode
                            // noch einmal durchgegnagen.
                            firstLoopRun=false;
                            ebenenStack.pop();
                            tmp.enqueue(new String[]{orderQueue.front()[0],orderQueue.front()[1]});
                            run(tmp);
                            tmp=new Queue<>();
                        }
                        break;
                    case "BEFEHL":
                        execute(orderQueue.front()[0]);
                        break;
                    case "VERZWEIGUNG":
                        break;
                    case "ABFRAGE":
                        break;
                    case "ZAHL":
                        System.out.println(outputController.inSicht(Integer.valueOf(orderQueue.front()[0])));
                            if (outputController.inSicht(Integer.valueOf(orderQueue.front()[0]))) {
                                if (startLoop) {
                                    startLoop=false;
                                    loopCount++;
                                    firstLoopRun=true;
                                    ebenenStack.push(ebene);
                                }//Falls eine Abfrage in einer Verzweigung true zurück gibt,
                                // werden die restlichen befehle norml durchlaufen
                            } else {
                                notwEbene = ebene;
                                orderQueue.dequeue();
                                orderQueue.dequeue();
                                ebene++;
                                startLoop=false;
                            }

                        break;
                    case "SCHLEIFE":
                        needParam=true;
                        startLoop=true;
                        break;

                }
            }else{
                if(orderQueue.front()[1].equals("ENDE")){
                    System.out.println("2222");
                    ebene--;
                    if(firstLoopRun){//Falls eine Schleife das erste mal durchgegenagen wird:
                        // Die gespeicherten Befehle werden in einem Selbsaufruf der Methode
                        // noch einmal durchgegnagen.
                        firstLoopRun=false;
                        ebenenStack.pop();
                        tmp.enqueue(new String[]{orderQueue.front()[0],orderQueue.front()[1]});
                        run(tmp);
                        tmp=new Queue<>();
                    }
                }
            }
            if(firstLoopRun){
                tmp.enqueue(new String[]{orderQueue.front()[0],orderQueue.front()[1]});
            }
            orderQueue.dequeue();
        }
    }

    private void execute(String value) {
        if (value.equals("vor")) {
            outputController.move();
            output = "Vorne - ";
            if (outputController.crashed()) {
                status = "verloren";
                output = "Kollision - ";
            }
        } else if (value.equals("rechts")) {
            outputController.turnRigth();
            output = "Rechts - ";
        } else if (value.equals("links")) {
            outputController.turnLeft();
            output = "Links - ";
        } else if (value.equals("zurueck")) {
            outputController.back();
            output = "Zurück - ";
            if (outputController.crashed()) {
                status = "verloren";
                output = "Kollision - ";
            }
        } else if (value.equals("fressen")) {
            outputController.eat();
        }

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
            }else if(type.equals("VERZWEIGUNG")){
                status="check";
            }

        }else if(status.equals("check")){
            if(type.equals("ABFRAGE")){
                if(value.equals("inSicht")){
                    warteAufParameter=true;
                }
            }else if(type.equals("PARAMETER") && warteAufParameter){ // wenn der Parser auf Syntax prüft, ist Paramteter notwendig ?
                warteAufParameter=false;
                if(outputController.inSicht(Integer.valueOf(value))){
                    status="do";
                }else{
                    status="jumpCond";
                }
            }
        }else if(status.equals("jumpCond")){
            if(type.equals("KLAMMERZU")){
                status="do";
            }
        }
    }
    //TODO "Verloren" weiter ausbauen. Der Parser soll in diesem Fall seine Handlung abbrechen.


}
