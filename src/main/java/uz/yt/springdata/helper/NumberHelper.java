package uz.yt.springdata.helper;

public class NumberHelper {

    public static boolean isValid(Integer i){
        return i != null && i > -1;
    }

    public static Integer toInt(String s){
        try {
            return Integer.parseInt(s);
        }catch (NumberFormatException e){
            return null;
        }
    }
}
