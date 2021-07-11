package ucf.assignments;

public class Check {
    static boolean CheckLength(String name) {
        if(name.length()<=0 || name.length()>=256){
            return false;
        } else {
            return true;
        }
    }
    static Integer ChangeDate(String New, String Old){
        String output = "";
        if(DateValidation.dateValidation(New)){
            output = New;
            return 0;
        } else{
            AlertBox.display("Error", "Not the right format or invalid date");
            output = Old;
            return 1;
        }
    }
    static Integer ChangeDescription(String New, String Old){
        String output = "";
        if(CheckLength(New)){
            output = New;
            return 0;
        } else{
            AlertBox.display("Error", "There must be more than 0 and less than 256 characters");
            output = Old;
            return 1;
        }
    }
}
