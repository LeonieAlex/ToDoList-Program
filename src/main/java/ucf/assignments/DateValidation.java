package ucf.assignments;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/*
This class is made to validate date when input is entered (for editable cell)

Create a function to check date validation which takes a string
    set boolean as false
    if(call a CheckDate function which checks the string is true)
        Check its date format
        try{
            parse the date
            set the boolean as true cause it works
        } catch exception
            boolean is false
        }
    }
    return the boolean variable
}

CheckDate function to check the date
    Create a String pattern (in this case it is YYYY-MM-DD)
    Create a boolean variable
    if the date string matches the pattern
        boolean variable is true
    return the variable
 */
public class DateValidation {
    static boolean dateValidation(String date) {
        boolean status = false;
        if (checkDate(date)) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateFormat.setLenient(false);
            try {
                dateFormat.parse(date);
                status = true;
            } catch (Exception e) {
                status = false;
            }
        }
        return status;
    }

    static boolean checkDate(String date) {
        String pattern = "^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$";
        boolean flag = false;
        if (date.matches(pattern)) {
            flag = true;
        }
        return flag;
    }
}
