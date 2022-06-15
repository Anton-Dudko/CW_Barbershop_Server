package simple_data;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Valid_Session {
    public static boolean isHaveOnlyGoodChars(String data){
        if (data.equals("")) return true;
        Pattern pattern = Pattern.compile("[^(A-Za-z0-9)]");
        Matcher matcher = pattern.matcher(data);
        if(matcher.find()){
            return false;
        } else {
            return true;
        }
    }

    public static boolean isDate(String data){
        Pattern pattern = Pattern.compile("201[3-8]{1}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])");
        Matcher matcher = pattern.matcher(data);
        if(!matcher.find( )){
            return false;
        } else  {
            return true;
        }
    }

    public static boolean isEMail(String data){
        if (data.equals("")) return false;
        Pattern pattern = Pattern.compile("[A-Za-z0-9]{3,30}@[A-Za-z0-9]{2,10}\\.[A-Za-z0-9]{2,5}");
        Matcher matcher = pattern.matcher(data);
        if(matcher.find()){
            return true;
        } else {
            return false;
        }
    }

    public static boolean isInt(String string){
        try {
            Integer.valueOf(string);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public static boolean isDouble(String string){
        try {
            Double.valueOf(string);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    public static boolean isTXT(String data){
        if (data.equals("")) return false;
        Pattern pattern = Pattern.compile("[A-Za-z0-9_]{1,30}\\.txt");
        Matcher matcher = pattern.matcher(data);
        return matcher.find() ? true : false;
    }

    public static boolean isLOG(String data){
        if (data.equals("")) return false;
        Pattern pattern = Pattern.compile("[A-Za-z0-9_]{1,30}\\.log");
        Matcher matcher = pattern.matcher(data);
        return matcher.find() ? true : false;
    }

    public static boolean isPort(String string){
        try {
            int port = Integer.valueOf(string);
            return (port <= 1024 || port > 65535) ? false : true;
        } catch (Exception e){
            return false;
        }
    }


    private static final String IP_ADDRESS_PATTERN
            = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
            + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
            + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
            + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

    public static boolean isHost(String string){
        Pattern pattern = Pattern.compile(IP_ADDRESS_PATTERN);
        Matcher matcher = pattern.matcher(string);
        if(!matcher.find( )){
            return false;
        } else  {
            return true;
        }
    }
}
