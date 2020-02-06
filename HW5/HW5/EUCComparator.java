package HW5;

import java.util.Comparator;

public class EUCComparator implements Comparator {

    /**
     *
     * @param o1 first parameter
     * @param o2 second parameter
     * The square root of the sum of the squares of the red, green and blue
     * values of the incoming numbers is taken. Then two numbers are compared.
     * @return integer value of compare
     */
    @Override
    public int compare(Object o1, Object o2) {


        int red1 = ((int)o1>>16)&0xff;
        int red2 = ((int)o2>>16)&0xff;

        int green1 = ((int)o1>>8)&0xff;
        int green2 = ((int)o2>>8)&0xff;

        int blue1 = (int)o1&0xff;
        int blue2 = (int)o2&0xff;


        double num1=Math.sqrt(Math.pow(red1,2) + Math.pow(green1,2) + Math.pow(blue1,2));

        double num2=Math.sqrt(Math.pow(red2,2) + Math.pow(green2,2) + Math.pow(blue2,2));


        return Double.compare(num1, num2);
    }

}
