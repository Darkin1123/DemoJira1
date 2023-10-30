
package Validation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

public class Check {
    public static Scanner sc = new Scanner(System.in);
    public static String PRODUCTCODE_REGEX = "[P]\\d{3}"; // PXXX
    public static String IMPORTCODE_REGEX = "[I]\\d{3}"; //RXXX
    public static String EXPORTCODE_REGEX = "[E]\\d{3}"; //EXXX
    public static String STRING_REGEX= ".+"; // Accept String not allow Null
    public static String DATE_REGEX = "\\d{2}[/]\\d{2}[/]\\d{4}";
    public static String YESNO_REGEX = "[YyNn]";
    
    public static  DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    public static String checkString(String msg, String error,String regex){
        while(true){
            try{
                System.out.println(msg);
                String str = sc.nextLine();
                if(str.isEmpty() || str==null){
                    System.out.println("The input must not be Empty !");
                }
                else if(str.matches(regex)){
                    return str;
                }
                else System.out.println(error);
            }
            catch(NumberFormatException e){
                System.out.println("The input must be a String value !");
            }
        }
    }
    
    public static int checkInt(String msg, String error){
        while(true){
            try{
                System.out.println(msg);
                int num = Integer.parseInt(sc.nextLine());
                if(num < 0){
                    System.out.println(error);
                }
                else return num;
            }
            catch(NumberFormatException e){
                System.out.println("The input must be an Integer value !");
            }
        }
    }
    
    public static double checkDouble(String msg, String error){
        while(true){
            try{
                System.out.println(msg);
                double num = Double.parseDouble(sc.nextLine());
                if(num < 0){
                    System.out.println(error);
                }
                else return num;
            }
            catch(NumberFormatException e){
                System.out.println("The input must be a Double value !");
            }
        }
    }
    
    public static boolean checkUpdate(String sth){
        boolean isUpdate = checkString("Do you want to update "+sth+" [Y/N]","Must be Y or N",YESNO_REGEX)
                .equalsIgnoreCase("Y");
        return isUpdate;
    }
    
    public static String checkDate(String msg){
        String importDate = null;
        while(importDate == null){
            System.out.println(msg);
            String input = sc.nextLine();
            if(checkValidDateFormat(input)){
                importDate = input;
            }
            else System.out.println("Invalid Date, must input Date in form of [dd/MM/yyyy]");
        }
        return importDate;
    }
    
    public static boolean checkValidDateFormat(String date) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        format.setLenient(false);
        try {
            Date parsedDate = format.parse(date);
            return true;
        }
        catch (Exception e) {
            return false;
            }
        }
    
    public static LocalDate convertStringToLocalDate(String date){
        LocalDate pasredDate = LocalDate.parse(date, dateFormatter);
        return pasredDate;
    }
    
}
