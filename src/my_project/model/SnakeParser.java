package my_project.model;

public class SnakeParser implements Parser{

    private SnakeScanner snakeScanner;
    private String debbugOutput;

    public SnakeParser(){
        snakeScanner = new SnakeScanner();
        debbugOutput="";
    }
    @Override
    public boolean parse(String input) {
        boolean b;
        debbugOutput="";
        if(snakeScanner.scan(input)){
            if(snakeScanner.getType().equals("START")){
                debbugOutput+="Start --> ";
                snakeScanner.nextToken();
                b = verzweigung(snakeScanner);
                b = befehl(snakeScanner);
                if(b){
                    if(snakeScanner.getType().equals("ENDE")){
                        snakeScanner.nextToken();
                        debbugOutput+="Ende --> ";
                        if(snakeScanner.getType().equals("NODATA")){
                            System.out.println(debbugOutput);
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

    public boolean befehl(SnakeScanner sC) {
        while(snakeScanner.getType().equals("BEFEHL")) {
            snakeScanner.nextToken();
            debbugOutput += "Befehl --> ";
            if (snakeScanner.getType().equals("KLAMMERAUF")) {
                snakeScanner.nextToken();
                debbugOutput += "Klammer auf --> ";
                if (snakeScanner.getType().equals("KLAMMERZU")) {
                    snakeScanner.nextToken();
                    debbugOutput += "Klammer zu --> ";
                } else return false;
            } else return false;
        }
        return true;
    }
    public boolean verzweigung(SnakeScanner sC) {
        if(snakeScanner.getType().equals("VERZWEIGUNG")) {
            snakeScanner.nextToken();
            debbugOutput += "Verzweigung --> ";
            if (snakeScanner.getType().equals("ABFRAGE")) {
                snakeScanner.nextToken();
                debbugOutput += "Abfrage --> ";
                if (snakeScanner.getType().equals("KLAMMERAUF")) {
                    snakeScanner.nextToken();
                    debbugOutput += "Klammer auf --> ";
                    if (snakeScanner.getType().equals("KLAMMERZU")) {
                        snakeScanner.nextToken();
                        debbugOutput += "Klammer zu --> ";
                        if (snakeScanner.getType().equals("START")) {
                            snakeScanner.nextToken();
                            debbugOutput += "Start --> ";
                            verzweigung(snakeScanner);
                            befehl(snakeScanner);
                            if (snakeScanner.getType().equals("ENDE")) {
                                snakeScanner.nextToken();
                                debbugOutput += "Ende --> ";
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
}
