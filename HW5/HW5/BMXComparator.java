package HW5;

import java.util.Comparator;

public class BMXComparator implements Comparator {

    /**
     *
     * @param o1 first parameter
     * @param o2 second parameter
     * Incoming numbers are converted to binary values, then binary values are combined and compared.
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


        String red1Binary = String.format("%8s", Integer.toBinaryString(red1)).replace(' ', '0');
        String red2Binary = String.format("%8s", Integer.toBinaryString(red2)).replace(' ', '0');

        String green1Binary = String.format("%8s", Integer.toBinaryString(green1)).replace(' ', '0');
        String green2Binary = String.format("%8s", Integer.toBinaryString(green2)).replace(' ', '0');

        String blue1Binary = String.format("%8s", Integer.toBinaryString(blue1)).replace(' ', '0');
        String blue2Binary = String.format("%8s", Integer.toBinaryString(blue2)).replace(' ', '0');

        StringBuilder firstNum = null;
        StringBuilder secondNum = null;

        for(int i = 0; i < 8; i++){
            firstNum = (firstNum == null ? new StringBuilder("null") : firstNum).append(red1Binary.charAt(i));
            firstNum.append(green1Binary.charAt(i));
            firstNum.append(blue1Binary.charAt(i));

            secondNum = (secondNum == null ? new StringBuilder("null") : secondNum).append(red2Binary.charAt(i));
            secondNum.append(green2Binary.charAt(i));
            secondNum.append(blue2Binary.charAt(i));
        }


        return firstNum.toString().compareTo(secondNum.toString());
    }

}
