package runjoy.tool;

import java.text.DecimalFormat;

/**
 * Created by JiachenWang on 2017/4/3.
 */

public class NumberUtils {

    private static final DecimalFormat df_double = new DecimalFormat("00.00");
    private static final DecimalFormat df_int = new DecimalFormat("#");

    public static String formatDouble(double input) {
        return df_double.format(input);
    }

    public static String formatInteger(int input) {
        return df_int.format(input);
    }

    public static double string2Double(String input) {
        double result = 0;
        try {
            result = Double.parseDouble(input);
        } catch (Exception e) {
            result = 0;
        }
        return result;
    }

    public static int string2Integer(String input) {
        int result = 0;
        try {
            result = Integer.parseInt(input);
        } catch (Exception e) {
            result = 0;
        }
        return result;
    }

    public static double doubleStander(double input){
        return  NumberUtils.string2Double(df_double.format(input));
    }
}
