package my_project.model;

import KAGO_framework.model.abitur.datenstrukturen.List;

public class SnakeScanner extends Scanner<String,String>{
    @Override
    public boolean scan(String input) {
        if(input == null || input.length() == 0){
            return false;
        }
        this.tokenList = new List<>();
        for(int i = 0; i<input.length();i++){
            if(input.charAt(i) == 'l'){
                if(i<input.length()-4){
                    Token tempT = new Token(input.substring(i,i+5),"BEFEHL");
                    if(tempT.equals("links")){
                        this.tokenList.append(tempT);
                    }else return false;
                }else return false;
            }else if(input.charAt(i) == 'r'){
                if(i<input.length()-5){
                    Token tmpT = new Token(input.substring(i,i+6),"BEFEHL");
                    if(tmpT.equals("rechts")){
                        this.tokenList.append(tmpT);
                    }else return false;
                }else return false;
            }else if(input.charAt(i) == 'v'){
                if(i<input.length()-2){
                    Token temp = new Token(input.substring(i,i+4),"BEFEHL");
                    if(temp.equals("vor")){
                        this.tokenList.append(temp);
                    }else return false;
                }else return false;
            }else if(input.charAt(i) == 'z'){
                if(i<input.length()-6){
                    Token temp = new Token(input.substring(i,i+7),"BEFEHL");
                    if(temp.equals("zurueck")){
                        this.tokenList.append(temp);
                    }else return false;
                }else return false;
            }else if(input.charAt(i) == 'f'){
                if(i<input.length()-6){
                    Token temp = new Token(input.substring(i,i+7),"BEFEHL");
                    if(temp.equals("fressen")){
                        this.tokenList.append(temp);
                    }else return false;
                }else return false;
            }else if(input.charAt(i) == '('){
                this.tokenList.append(new Token(input.charAt(i),"KLAMMERAUF"));
            }else if(input.charAt(i) == ')'){
                this.tokenList.append(new Token(input.charAt(i),"KLAMMERZU"));
            }else if(input.charAt(i) == '{'){
                this.tokenList.append(new Token(input.charAt(i),"START"));
            }else if(input.charAt(i) == '}'){
                this.tokenList.append(new Token(input.charAt(i),"ENDE"));
            }else return false;
        }
        this.tokenList.append(new Token("#","NODATA"));
        tokenList.toFirst(); // WICHTIG!
        return true;
    }
}
