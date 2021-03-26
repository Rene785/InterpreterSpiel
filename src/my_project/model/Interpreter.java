package my_project.model;

import KAGO_framework.model.abitur.datenstrukturen.Queue;
import my_project.control.OutputController;


public class Interpreter {

    private OutputController outputController;
    private String output;
    private Queue<String[]> streamQueue;
    private int ebene,notwEbene;
    private boolean startLoop,stop,slowMode,pause;

    public Interpreter(OutputController outputController){
        this.outputController=outputController;
        output="Interpret -->";
        ebene=0;
        notwEbene= Integer.MAX_VALUE;
        startLoop=false;
        streamQueue=new Queue<>();
        stop=false;
        slowMode=false;
        pause=false;

    }

    public void addOrder(String value,String type){
        String[] s=new String[]{value,type};
        streamQueue.enqueue(s);

    }

    public void run(){
        run(streamQueue);
        System.out.println(output);
    }

    private void run(Queue<String[]> orderQueue) {
        boolean firstLoopRun = false;
        Queue<String[]> tmp = new Queue<>();
        while (!orderQueue.isEmpty() && !stop) {// Solange die Schlange Eingaben enthält
            if (ebene <= notwEbene) {  //Falls man die Eingabe nciht überspringen soll
                notwEbene = Integer.MAX_VALUE;
                switch (orderQueue.front()[1]) {//Selektiere Eingabentyp
                    case "START":
                        ebene++;
                        break;
                    case "ENDE":
                        ebene--;
                        if (firstLoopRun) {//Falls eine Schleife das erste mal durchgegenagen wird:
                            // Die gespeicherten Befehle werden in einem Selbsaufruf der Methode
                            // noch einmal durchgegnagen.
                            firstLoopRun = false;
                            tmp.enqueue(new String[]{orderQueue.front()[0], orderQueue.front()[1]});
                            run(tmp);
                            tmp = new Queue<>();
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
                           //Falls eine Abfrage in einer Verzweigung true zurück gibt,
                            // werden die restlichen befehle norml durchlaufen
                        } else {
                            firstLoopRun=false;
                            tmp=new Queue<>();
                            notwEbene = ebene;
                            orderQueue.dequeue();
                            orderQueue.dequeue();
                            ebene++;
                            startLoop = false;
                        }

                        break;
                    case "SCHLEIFE":
                        firstLoopRun = true;
                        break;

                }
            } else {
                if (orderQueue.front()[1].equals("ENDE")) {
                    ebene--;
                    if (firstLoopRun) {//Falls eine Schleife das erste mal durchgegenagen wird:
                        // Die gespeicherten Befehle werden in einem Selbsaufruf der Methode
                        // noch einmal durchgegnagen.
                        firstLoopRun = false;
                        tmp.enqueue(new String[]{orderQueue.front()[0], orderQueue.front()[1]});
                        run(tmp);
                        tmp = new Queue<>();
                    }
                }
            }
            if (firstLoopRun) {
                tmp.enqueue(new String[]{orderQueue.front()[0], orderQueue.front()[1]});
            }
            orderQueue.dequeue();
        }

        if (stop) {
            stop = false;
            outputController.neuStart();
            streamQueue = new Queue<>();
        }
    }
    private void execute(String value) {
        if (value.equals("vor")) {
            outputController.move();
            output += "Vorne - ";
            if (outputController.crashed()) {
                output += "Kollision - ";
                stop=true;
            }
        } else if (value.equals("rechts")) {
            outputController.turnRight();
            output += "Rechts - ";
        } else if (value.equals("links")) {
            outputController.turnLeft();
            output += "Links - ";
        } else if (value.equals("zurueck")) {
            outputController.back();
            output += "Zurück - ";
            if (outputController.crashed()) {
                output += "Kollision - ";
                stop=true;
            }
        } else if (value.equals("fressen")) {
            outputController.eat();
        }
    }

}