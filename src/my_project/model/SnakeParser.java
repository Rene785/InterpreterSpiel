package my_project.model;

public class SnakeParser implements Parser{

    private SnakeScanner snakeScanner;
    public SnakeParser(){ snakeScanner = new SnakeScanner();}
    @Override
    public boolean parse(String input) {
        if(snakeScanner.scan(input)){
            if(snakeScanner.getType().equals("START")){
                if(snakeScanner.getType().equals("BEFEHL")){
                    snakeScanner.nextToken();
                    if(snakeScanner.getType().equals("KLAMMERAUF")){
                        snakeScanner.nextToken();
                    }
                }
            }

        }
        return false;
    }

    @Override
    public boolean getScannerResult(String input) {
        return false;
    }

}
