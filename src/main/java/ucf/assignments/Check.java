package ucf.assignments;

public class Check {
    /*
    CheckLength(input)
        if(the length of the input is less than 0 or larger than 256
            return false;
        else
            return true
     */
    static boolean CheckLength(String name) {
        if(name.length()<=0 || name.length()>=256){
            return false;
        } else {
            return true;
        }
    }

    /*
    ChangeDate(New and Old String)
        if(the new Date is valid)
            return New;
        else
            return Old
     */
    static String ChangeDate(String New, String Old){
        if(DateValidation.dateValidation(New)){
            return New;
        } else{
            return Old;
        }
    }

    /*
    ChangeDescription(New and Old String)
        if(the new Description is valid)
            return New;
        else
            return Old
     */
    static String ChangeDescription(String New, String Old){
        if(CheckLength(New)){
            return New;
        } else{
            return Old;
        }
    }

    /*
    ChangeProgress(New and Old String)
        if(the new Progress is valid)
            return New;
        else
            return Old
     */
    static String ChangeProgress(String New, String Old){
        if(New.equals("Completed") || New.equals("Incomplete")){
            return New;
        } else {
            return Old;
        }
    }
}
