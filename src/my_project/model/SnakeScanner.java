package my_project.model;

import KAGO_framework.model.abitur.datenstrukturen.List;

public class SnakeScanner extends Scanner<String,String>{

    private String debbugOutput;

    @Override
    public boolean scan(String input) {
        System.out.println(input);
        debbugOutput="Scan --> ";
        if(input == null || input.length() == 0){
            return false;
        }
        this.tokenList = new List<>();
        for(int i = 0; i<input.length();i++){
            if(input.charAt(i) == 'l'){
                if(i<input.length()-4){
                    Token tempT = new Token(input.substring(i,i+5),"BEFEHL");
                    if(tempT.getValue().equals("links")){
                        debbugOutput+=tempT.getValue()+" --> ";
                        this.tokenList.append(tempT);
                        i+=4;
                    }else return false;
                }else return false;
            }else if(input.charAt(i) == 'r'){
                if(i<input.length()-5){
                    Token tmpT = new Token(input.substring(i,i+6),"BEFEHL");
                    if(tmpT.getValue().equals("rechts")){
                        debbugOutput+=tmpT.getValue()+" --> ";
                        this.tokenList.append(tmpT);
                        i+=5;
                    }else return false;
                }else return false;
            }else if(input.charAt(i) == 'v'){
                if(i<input.length()-2){
                    Token temp = new Token(input.substring(i,i+3),"BEFEHL");
                    if(temp.getValue().equals("vor")){
                        debbugOutput+=temp.getValue()+" --> ";
                        this.tokenList.append(temp);
                        i+=2;
                    }else return false;
                }else return false;
            }else if(input.charAt(i) == 'z'){
                if(i<input.length()-6){
                    Token temp = new Token(input.substring(i,i+7),"BEFEHL");
                    if(temp.getValue().equals("zurueck")){
                        debbugOutput+=temp.getValue()+" --> ";
                        this.tokenList.append(temp);
                        i+=6;
                    }else return false;
                }else return false;
            }else if(input.charAt(i) == 'f'){
                if(i<input.length()-6){
                    Token temp = new Token(input.substring(i,i+7),"BEFEHL");
                    if(temp.getValue().equals("fressen")){
                        debbugOutput+=temp.getValue()+" --> ";
                        this.tokenList.append(temp);
                        i+=6;
                    }else return false;
                }else return false;
            }else if(input.charAt(i) == '('){
                debbugOutput+=" ( --> ";
                this.tokenList.append(new Token(input.charAt(i),"KLAMMERAUF"));
            }else if(input.charAt(i) == ')'){
                debbugOutput+=" ) --> ";
                this.tokenList.append(new Token(input.charAt(i),"KLAMMERZU"));
            }else if(input.charAt(i) == '{'){
                debbugOutput+=" { --> ";
                this.tokenList.append(new Token(input.charAt(i),"START"));
            }else if(input.charAt(i) == '}'){
                debbugOutput+=" } --> ";
                this.tokenList.append(new Token(input.charAt(i),"ENDE"));
            }else if(input.charAt(i) == 'i'){
                if(i<input.length()-1){
                    if(input.charAt(i) == 'f'){
                        this.tokenList.append(new Token(input.substring(i,i+2),"VERZWEIGUNG"));
                    }
                }
            }
        }
        this.tokenList.append(new Token("#","NODATA"));
        tokenList.toFirst(); // WICHTIG!
        System.out.println(debbugOutput);
        return true;
    }

}
