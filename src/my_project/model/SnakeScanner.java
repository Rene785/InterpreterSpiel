package my_project.model;

import KAGO_framework.model.abitur.datenstrukturen.List;

public class SnakeScanner extends Scanner<String,String>{

    private String debugOutput;

    @Override
    public boolean scan(String input) {
        System.out.println(input);
        debugOutput ="Scan --> ";
        if(input == null || input.length() == 0){
            return false;
        }
        this.tokenList = new List<>();
        for(int i = 0; i<input.length();i++) {
            if (input.charAt(i) == 'l') {
                if (i < input.length() - 4) {
                    Token tempT = new Token(input.substring(i, i + 5), "BEFEHL");
                    if (tempT.getValue().equals("links")) {
                        debugOutput += tempT.getValue() + " --> ";
                        this.tokenList.append(tempT);
                        i += 4;
                    } else return false;
                } else return false;
            } else if (input.charAt(i) == 'r') {
                if (i < input.length() - 5) {
                    Token tmpT = new Token(input.substring(i, i + 6), "BEFEHL");
                    if (tmpT.getValue().equals("rechts")) {
                        debugOutput += tmpT.getValue() + " --> ";
                        this.tokenList.append(tmpT);
                        i += 5;
                    } else return false;
                } else return false;
            } else if (input.charAt(i) == 'v') {
                if (i < input.length() - 2) {
                    Token temp = new Token(input.substring(i, i + 3), "BEFEHL");
                    if (temp.getValue().equals("vor")) {
                        debugOutput += temp.getValue() + " --> ";
                        this.tokenList.append(temp);
                        i += 2;
                    } else return false;
                } else return false;
            } else if (input.charAt(i) == 'z') {
                if (i < input.length() - 6) {
                    Token temp = new Token(input.substring(i, i + 7), "BEFEHL");
                    if (temp.getValue().equals("zurueck")) {
                        debugOutput += temp.getValue() + " --> ";
                        this.tokenList.append(temp);
                        i += 6;
                    } else return false;
                } else return false;
            } else if (input.charAt(i) == 'f') {
                if (i < input.length() - 6) {
                    Token temp = new Token(input.substring(i, i + 7), "BEFEHL");
                    if (temp.getValue().equals("fressen")) {
                        debugOutput += temp.getValue() + " --> ";
                        this.tokenList.append(temp);
                        i += 6;
                    } else return false;
                } else return false;
            } else if (input.charAt(i) == '(') {
                debugOutput += " ( --> ";
                this.tokenList.append(new Token(input.charAt(i), "KLAMMERAUF"));
            } else if (input.charAt(i) == ')') {
                debugOutput += " ) --> ";
                this.tokenList.append(new Token(input.charAt(i), "KLAMMERZU"));
            } else if (input.charAt(i) == '{') {
                debugOutput += " { --> ";
                this.tokenList.append(new Token(input.charAt(i), "START"));
            } else if (input.charAt(i) == '}') {
                debugOutput += " } --> ";
                this.tokenList.append(new Token(input.charAt(i), "ENDE"));
            } else if (input.charAt(i) == 'w') {
                if (i < input.length() - 3) {
                    Token tmp = new Token(input.substring(i, i + 4), "VERZWEIGUNG");
                    if (tmp.getValue().equals("wenn")) {
                        debugOutput += tmp.getValue() + " --> ";
                        this.tokenList.append(tmp);
                        i += 3;
                    } else return false;
                } else return false;
            }else if(input.charAt(i) == 'i') {
                if (i < input.length() - 6) {
                    Token tmp = new Token(input.substring(i, i + 7), "ABFRAGE");
                    if (tmp.getValue().equals("inSicht")) {
                        debugOutput += tmp.getValue() + " --> ";
                        this.tokenList.append(tmp);
                        i += 6;
                    } else return false;
                } else return false;
            }else if(input.charAt(i) == 's'){
                if(i<input.length()-6){
                    Token tmp = new Token(input.substring(i,i+7),"SCHLEIFE");
                    if(tmp.getValue().equals("solange")){
                        debugOutput += tmp.getValue() + " --> ";
                        this.tokenList.append(tmp);
                        i+=6;
                    }
                }
            }else if(Character.isDigit(input.charAt(i))){
                debugOutput +=input.charAt(i)+ "  --> ";
                this.tokenList.append(new Token(input.charAt(i), "ZAHL"));
            }
        }
        System.out.println(debugOutput);
        this.tokenList.append(new Token("#","NODATA"));
        tokenList.toFirst(); // WICHTIG!
        return true;
    }

}
