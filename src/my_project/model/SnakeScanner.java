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
                    Token tempT = new Token(input.substring(i,i+5),"RICHTUNG");
                    if(tempT.equals("links")){
                        this.tokenList.append(tempT);
                    }
                }
            }
            if(input.charAt(i) == 'r'){
                if(i<input.length()-5){
                    Token tmpT = new Token(input.substring(i,i+6),"RICHTUNG");
                    if(tmpT.equals("rechts")){
                        this.tokenList.append(tmpT);
                    }
                }
            }
            if(input.charAt(i) == 'v'){
                if(i<input.length()-2){
                    Token temp = new Token(input.substring(i,i+4),"POSITION");
                    if(temp.equals("vor")){
                        this.tokenList.append(temp);
                    }
                }
            }
            if(input.charAt(i) == 'z'){
                if(i<input.length()-6){
                    Token temp = new Token(input.substring(i,i+7),"POSITION");
                    if(temp.equals("zurueck")){
                        this.tokenList.append(temp);
                    }
                }
            }
            if(input.charAt(i) == 'f'){
                if(i<input.length()-6){
                    Token temp = new Token(input.substring(i,i+7),"FRESSEN");
                    if(temp.equals("fressen")){
                        this.tokenList.append(temp);
                    }
                }
            }

        }
        return false;
    }
}
