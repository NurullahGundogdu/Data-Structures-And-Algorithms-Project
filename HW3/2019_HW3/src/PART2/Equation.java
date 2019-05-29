package PART2;

import PART1.STACK;

public class Equation {



    public STACK<String> equation=new STACK<>();
    public String [] paramete = new String[1];
    public Double [] valu = new  Double[1];

    /**
     *calculate equation value
     */
    public String calculate(){

        equation.reverse();

        String x,y,temp;
        STACK<String> result=new STACK<>();

        while (!equation.empty()){

            temp=equation.pop();

            if(!temp.equals("+") && !temp.equals("-") && !temp.equals("*") && !temp.equals("/")){
                result.push(temp);
            }else{

                y=result.pop();
                x=result.pop();

                if(temp.equals("+")) {
                    result.push(Double.toString(addition(x,y)));
                }else if(temp.equals("-")){
                    result.push(Double.toString(subtraction(x,y)));
                }else if(temp.equals("/")){
                    result.push(Double.toString(divide(x,y)));
                }else if(temp.equals("*")){
                    result.push(Double.toString(multiplication(x,y)));

                }
            }
        }

        return result.pop();
    }

    /**
     *
     * @param x
     * @param y
     * @return double value
     * take two string number and add up
     */
    private double addition(String x, String y){

        double num=control(x);
        double num2=control(y);


        return num+num2;
    }

    /**
     *
     * @param x
     * @param y
     * @return return double value
     * take two string number and substract
     */
    private double subtraction(String x, String y){
        double num=control(x);
        double num2=control(y);


        return num-num2;
    }

    /**
     *
     * @param x
     * @param y
     * @return return double value
     * take two string number and divide
     */
    private double divide(String x, String y){

        double num=control(x);
        double num2=control(y);

        return num/num2;
    }

    /**
     *
     * @param x
     * @param y
     * @return return double value
     * take two string number and multiply
     */
    private double multiplication(String x, String y){

        double num=control(x);
        double num2=control(y);


        return num*num2;
    }

    /**
     *
     * @param temp
     * @return double value of string
     * take string and check is a double value or a name
     */
    private double control(String temp){

        double num=0;

        if(Character.isDigit(temp.charAt(0)) || temp.charAt(0) == '-'){
            num=Double.parseDouble(temp);
        }else if(temp.contains("abs")){
            num=abs(temp);
        }else if(temp.contains("sin")){
            num=sinus(temp);
        }else if(temp.contains("cos")){
            num=cosinus(temp);
        }else{

            for (int i=0; i<valu.length; i++){
                if (paramete[i].contains(temp)){
                    num=valu[i];
                    break;
                }
            }
        }

        return num;
    }

    /**
     *
     * @param temp
     * @return sinus value
     * take string and convert to double and calculate sinus value of string
     */
    private double sinus(String temp){

        double num=0,num2=0,result;
        int i =0;

        String []a=temp.split(" ");
        if(a.length==3){
            num=control(a[1]);
        } else if(a.length==5) {
            if (a[1].contains("abs") || a[1].contains("cos") || a[1].contains("sin")){
                   String tm=a[1]+" "+a[2]+" "+a[3];
                   num=control(tm);
            }else
                num=control(a[1])+control(a[3]);

        }else if(a.length>5){

            i=2;
            if(a[1].contains("abs") || a[1].contains("cos") || a[1].contains("sin")){
                String string=a[1];
                while (!a[i].equals(")")){
                    string +=" "+a[i];
                    i++;
                }
                string+=" "+a[i];
                num2=control(string);
            }else{
                num2=control(a[1]);
                i=-1;
            }
            if(i==-1){
                String string=a[3];
                for(int j=4; j<a.length-1; j++)
                    string+=" "+a[j];
                num=control(string);

            }else{
                i++;
                if(!a[i].equals(")")) {
                    i++;
                    num = control(a[i]);
                }
            }

        }
        if(a.length<=5)
            result =num;
        else{
            if(i==-1)
                result=helperFunction(num2,num,a[2]);
            else {
                if (a[i].equals(")"))
                    result = num2;
                else
                    result = helperFunction(num2, num, a[i - 1]);
            }
                
        }



        result = result / 180 * 3.14;


        double term = 1.0;
        double sum  = 0.0;

        for (i = 1; term != 0.0; i++) {
            term *= (result / i);
            if (i % 4 == 1) sum += term;
            if (i % 4 == 3) sum -= term;
        }

        return sum;
    }

    /**
     *
     * @param a
     * @param b
     * @param c
     * @return opertor value
     */
    private double helperFunction(double a,double b,String c){
        
        double result=0;
        
        if(c.equals("+"))
            result=addition(Double.toString(a),Double.toString(b));
        else if(c.equals("-"))
            result=subtraction(Double.toString(a),Double.toString(b));

        else if(c.equals("*"))
            result=multiplication(Double.toString(a),Double.toString(b));

        else if(c.equals("/"))
            result=divide(Double.toString(a),Double.toString(b));
        
        return result;
    }

    /**
     *
     * @param temp
     * @return cosinus value
     * take string and convert to double and calculate cosinus value of string
     */
    private double cosinus(String temp){

        double num=0,num2=0,result;
        int i =0;

        String []a=temp.split(" ");
        if(a.length==3){
            num=control(a[1]);
        } else if(a.length==5) {
            if (a[1].contains("abs") || a[1].contains("cos") || a[1].contains("sin")){
                String tm=a[1]+" "+a[2]+" "+a[3];
                num=control(tm);
            }else
                num=control(a[1])+control(a[3]);

        }else if(a.length>5){

            i=2;
            if(a[1].contains("abs") || a[1].contains("cos") || a[1].contains("sin")){
                String string=a[1];
                while (!a[i].equals(")")){
                    string +=" "+a[i];
                    i++;
                }
                string+=" "+a[i];
                num2=control(string);

            }else{
                num2=control(a[1]);
                i=-1;
            }
            if(i==-1){
                String string=a[3];
                for(int j=4; j<a.length-1; j++)
                    string+=" "+a[j];
                num=control(string);

            }else{
                i++;
                if(!a[i].equals(")")) {
                    i++;
                    num = control(a[i]);
                }
            }

        }
        if(a.length<=5)
            result =num;
        else{
            if(i==-1)
                result=helperFunction(num2,num,a[2]);
            else {
                if (a[i].equals(")"))
                    result = num2;
                else
                    result = helperFunction(num2, num, a[i - 1]);
            }

        }

        result=90-result;

        result = result / 180 * 3.14;


        double term = 1.0;
        double sum  = 0.0;

        for (i = 1; term != 0.0; i++) {
            term *= (result / i);
            if (i % 4 == 1) sum += term;
            if (i % 4 == 3) sum -= term;
        }


        return sum;
    }

    /**
     *
     * @param temp
     * @return abs value
     * take string and convert to double and calculate abs value of string
     */
    private double abs(String temp){

        double num=0,num2=0,result;
        int i =0;

        String []a=temp.split(" ");
        if(a.length==3){
            num=control(a[1]);
        } else if(a.length==5) {
            if (a[1].contains("abs") || a[1].contains("cos") || a[1].contains("sin")){
                String tm=a[1]+" "+a[2]+" "+a[3];
                num=control(tm);
            }else
                num=control(a[1])+control(a[3]);

        }else if(a.length>5){
            i=2;
            if(a[1].contains("abs") || a[1].contains("cos") || a[1].contains("sin")){
                String string=a[1];
                while (!a[i].equals(")")){
                    string +=" "+a[i];
                    i++;
                }
                string+=" "+a[i];
                num2=control(string);
            }else{
                num2=control(a[1]);
                i=-1;
            }
            if(i==-1){
                String string=a[3];
                for(int j=4; j<a.length-1; j++)
                    string+=" "+a[j];
                num=control(string);
            }else{
                i++;
                if(!a[i].equals(")")) {
                    i++;
                    num = control(a[i]);
                }
            }
        }
        if(a.length<=5)
            result =num;
        else{
            if(i==-1)
                result=helperFunction(num2,num,a[2]);
            else {
                if (a[i].equals(")"))
                    result = num2;
                else
                    result = helperFunction(num2, num, a[i - 1]);
            }

        }

        if(result<0)
            result*=(-1);

        return result;

    }

    /**
     *
     * @param arr
     * take string array and convert equation to postfix equation
     */
    public void convertToPostfix(String [] arr) {

        String[] temp = new String[arr.length];

        STACK<String> stack = new STACK<>();
        int j=0;
        for (int i = 0; i < arr.length; ++i) {
            String c = arr[i];

            char d = c.charAt(0);

            if (c.equals("(")) {
                stack.push(c);

            } else if (c.equals(")")) {
                while (!stack.empty() && !stack.peek().equals("(")){
                    temp[j] = stack.pop();
                    j++;
                }
                if (!stack.empty() && !stack.peek().equals("("))
                    //return "Invalid Expression"; // invalid expression
                    break;
                else
                    stack.pop();
            } else if (c.equals("+") || c.equals("-") || c.equals("*") || c.equals("/")) {
                while (!stack.empty() && convertToPostfixHelperFunction(c) <= convertToPostfixHelperFunction(stack.peek())) {
                    temp[j] = stack.pop();
                    j++;
                }
                stack.push(c);

            } else {
                temp[j] = c;
                j++;
            }

        }

        int i = 0;
        while (!stack.empty()){
            temp[j] = stack.pop();
            j++;
        }


        for (String aTemp : temp)
            if (aTemp != null) {
                equation.push(aTemp);
            }

    }

    /**
     *
     * @param temp
     * @return integer value
     * take a string and control which operator is it
     * and retutn integer value
     */
    private int convertToPostfixHelperFunction(String temp)
    {
        if(temp.equals("+") || temp.equals("-")) {
            return 1;
        }else if (temp.equals("*") || temp.equals("/"))
            return 2;

        return -1;
    }



}
