package my_project.model;

public class SnakeParser implements Parser{

    private SnakeScanner snakeScanner;
    private String debbugOutput;
    private Interpreter interpreter;

    public SnakeParser(Interpreter interpreter){
        snakeScanner = new SnakeScanner();
        debbugOutput="";
        this.interpreter=interpreter;
    }
    @Override
    public boolean parse(String input) {
        boolean b;
        debbugOutput="Parsing --> ";
        if(snakeScanner.scan(input)){
            if(snakeScanner.getType().equals("START")){
                debbugOutput+="Start --> ";
                interpreter.addOrder(String.valueOf(snakeScanner.getValue()),String.valueOf(snakeScanner.getType()));
                snakeScanner.nextToken();
                b = schleife();
                b = verzweigung();
                b = befehl();
                if(b){
                    if(snakeScanner.getType().equals("ENDE")){
                        interpreter.addOrder(String.valueOf(snakeScanner.getValue()),String.valueOf(snakeScanner.getType()));
                        snakeScanner.nextToken();
                        debbugOutput+="Ende --> ";
                        if(snakeScanner.getType().equals("NODATA")){
                            System.out.println(debbugOutput);
                            interpreter.run();
                            return true;
                        }
                    }
                }

            }
        }
        return false;
    }

    @Override
    public boolean getScannerResult(String input) {
        return snakeScanner.scan(input);
    }

    public boolean befehl() {
        while(snakeScanner.getType().equals("BEFEHL")) {
            interpreter.addOrder(String.valueOf(snakeScanner.getValue()),String.valueOf(snakeScanner.getType()));
            snakeScanner.nextToken();
            debbugOutput += "Befehl --> ";
            if (snakeScanner.getType().equals("KLAMMERAUF")) {
                interpreter.addOrder(String.valueOf(snakeScanner.getValue()),String.valueOf(snakeScanner.getType()));
                snakeScanner.nextToken();
                debbugOutput += "Klammer auf --> ";
                if (snakeScanner.getType().equals("KLAMMERZU")) {
                    interpreter.addOrder(String.valueOf(snakeScanner.getValue()),String.valueOf(snakeScanner.getType()));
                    snakeScanner.nextToken();
                    debbugOutput += "Klammer zu --> ";
                } else return false;
            } else return false;
        }
        return true;
    }
    public boolean verzweigung() {
        if(snakeScanner.getType().equals("VERZWEIGUNG")) {
            interpreter.addOrder(String.valueOf(snakeScanner.getValue()),String.valueOf(snakeScanner.getType()));
            snakeScanner.nextToken();
            debbugOutput += "Verzweigung --> ";
            if (snakeScanner.getType().equals("ABFRAGE")) {
                interpreter.addOrder(String.valueOf(snakeScanner.getValue()),String.valueOf(snakeScanner.getType()));
                snakeScanner.nextToken();
                debbugOutput += "Abfrage --> ";
                if (snakeScanner.getType().equals("KLAMMERAUF")) {
                    interpreter.addOrder(String.valueOf(snakeScanner.getValue()),String.valueOf(snakeScanner.getType()));
                    snakeScanner.nextToken();
                    debbugOutput += "Klammer auf --> ";
                    if (snakeScanner.getType().equals("ZAHL")) {
                        interpreter.addOrder(String.valueOf(snakeScanner.getValue()),String.valueOf(snakeScanner.getType()));
                        snakeScanner.nextToken();
                        debbugOutput += "Zahl --> ";
                        if (snakeScanner.getType().equals("KLAMMERZU")) {
                            interpreter.addOrder(String.valueOf(snakeScanner.getValue()),String.valueOf(snakeScanner.getType()));
                            snakeScanner.nextToken();
                            debbugOutput += "Klammer zu --> ";
                            if (snakeScanner.getType().equals("START")) {
                                interpreter.addOrder(String.valueOf(snakeScanner.getValue()),String.valueOf(snakeScanner.getType()));
                                snakeScanner.nextToken();
                                debbugOutput += "Start --> ";
                                boolean b;
                                b = schleife();
                                b = verzweigung();
                                b = befehl();
                                if (b) {
                                    if (snakeScanner.getType().equals("ENDE")) {
                                        interpreter.addOrder(String.valueOf(snakeScanner.getValue()),String.valueOf(snakeScanner.getType()));
                                        snakeScanner.nextToken();
                                        debbugOutput += "Ende --> ";
                                    } else return false;
                                } else return false;
                            } else return false;
                        } else return false;
                    } else return false;
                } else return false;
            }else return false;
        }
        return true;
    }

    public boolean schleife(){
        if(snakeScanner.getType().equals("SCHLEIFE")){
            interpreter.addOrder(String.valueOf(snakeScanner.getValue()),String.valueOf(snakeScanner.getType()));
            snakeScanner.nextToken();
            debbugOutput += "Schleife --> ";
            if(snakeScanner.getType().equals("BEFEHL")|| snakeScanner.getType().equals("ABFRAGE")){
                interpreter.addOrder(String.valueOf(snakeScanner.getValue()),String.valueOf(snakeScanner.getType()));
                snakeScanner.nextToken();
                debbugOutput += "Abfrage --> ";
                if(snakeScanner.getType().equals("KLAMMERAUF")){
                    interpreter.addOrder(String.valueOf(snakeScanner.getValue()),String.valueOf(snakeScanner.getType()));
                    snakeScanner.nextToken();
                    debbugOutput += "Klammer auf --> ";
                    if(snakeScanner.getType().equals("ZAHL")) {
                        interpreter.addOrder(String.valueOf(snakeScanner.getValue()),String.valueOf(snakeScanner.getType()));
                        snakeScanner.nextToken();
                        debbugOutput += "Zahl --> ";
                        if (snakeScanner.getType().equals("KLAMMERZU")) {
                            interpreter.addOrder(String.valueOf(snakeScanner.getValue()),String.valueOf(snakeScanner.getType()));
                            snakeScanner.nextToken();
                            if (snakeScanner.getType().equals("START")) {
                                interpreter.addOrder(String.valueOf(snakeScanner.getValue()),String.valueOf(snakeScanner.getType()));
                                debbugOutput += "Start --> ";
                                snakeScanner.nextToken();
                                boolean b;
                                b = schleife();
                                b = verzweigung();
                                b = befehl();
                                if (b) {
                                    if (snakeScanner.getType().equals("ENDE")) {
                                        interpreter.addOrder(String.valueOf(snakeScanner.getValue()),String.valueOf(snakeScanner.getType()));
                                        snakeScanner.nextToken();
                                        debbugOutput += "Ende --> ";
                                    } else return false;
                                } else return false;
                            } else return false;
                        } else return false;
                    }else return false;
                }else return false;
            }else return false;
        }
        return true;
    }

    public String getDebbugOutput() {
        return debbugOutput;
    }
}
