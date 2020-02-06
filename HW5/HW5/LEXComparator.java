package HW5;

import java.util.Comparator;

public class LEXComparator implements Comparator {

    /**
     *
     * @param o1 first parameter
     * @param o2 second parameter
     * First, the red values of the corresponding numbers are compared
     * and the results are compared to the green, and the blue values are
     * compared to the result from the green.
     * @return integer value of compare
     */

    @Override
    public int compare(Object o1, Object o2) {


        int red1 = ((int)o1>>16)&0xff;
        int red2 = ((int)o2>>16)&0xff;

        if(red1 > red2){

            return 1;

        }else if(red1 < red2){

            return -1;

        }else{

            int green1 = ((int)o1>>8)&0xff;
            int green2 = ((int)o2>>8)&0xff;

            if(green1 > green2){

                return 1;

            }else if(green1 < green2){

                return -1;

            }else{

                int blue1 = (int)o1&0xff;
                int blue2 = (int)o2&0xff;

                return Integer.compare(blue1, blue2);
            }
        }

    }


}
