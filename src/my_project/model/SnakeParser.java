package my_project.model;

public class SnakeParser implements Parser{

    private SnakeScanner snakeScanner;

    public SnakeParser(){ snakeScanner = new SnakeScanner();}
    @Override
    public boolean parse(String input) {
        if(snakeScanner.scan(input)){
            if(snakeScanner.getType().equals("START")){
                while(snakeScanner.getType().equals("BEFEHL")){
                    snakeScanner.nextToken();
                    if(snakeScanner.getType().equals("KLAMMERAUF")){
                        snakeScanner.nextToken();
                        if(snakeScanner.getType().equals("KLAMMERZU")){
                            snakeScanner.nextToken();
                        }else return false;
                    }else return false;
                }
                if(snakeScanner.getType().equals("ENDE")){
                    snakeScanner.nextToken();
                    if(snakeScanner.getType().equals("NODATA")){
                        return true;
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

}
