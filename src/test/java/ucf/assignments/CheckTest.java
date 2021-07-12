package ucf.assignments;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
Check length

An item shall have a description
A description shall be between 1 and 256 characters in length
 */
class CheckTest {
    /*
    To test the Description Length
    String description is empty
    Actual to check if length is valid
    Expected is false

    assert
     */
    @Test
    void CheckDescriptionLength(){
        String description = "";
        Boolean actual = Check.CheckLength(description);
        Boolean expected = false;

        assertEquals(actual, expected);
    }

    /*
    To test the Name Length
    String Name is empty
    Actual to check if length is valid
    Expected is false

    assert
     */
    @Test
    void CheckNameLength(){
        String name = "";
        Boolean actual = Check.CheckLength(name);
        Boolean expected = false;

        assertEquals(actual, expected);
    }

    /*
    This is used to check the changing Date and Gregorian and format
    To test the Date
    String Old is a valid date
    String New is an invalid date
    String actual is checking the New and Old date
    (If the New date is valid, New date is written, else, the Old date)
    Expected is Old

    assert
     */
    @Test
    void ChangeDateUnapproved(){
        String Old = "2012-03-22";
        String New = "2012-42-21";
        String actual = Check.ChangeDate(New, Old);
        assertEquals(Old, actual);
    }

    /*
    This is used to check the changing Date and Gregorian and format
    To test the Date
    String Old is a valid date
    String New is a valid  date
    String actual is checking the New and Old date
    (If the New date is valid, New date is written, else, the Old date)
    Expected is New

    assert
     */
    @Test
    void ChangeDateApproved(){
        String Old = "2012-03-22";
        String New = "2012-12-21";
        String actual = Check.ChangeDate(New, Old);
        assertEquals(New, actual);
    }

    /*
   This is used to check the new description
   To test the Description
   String Old is a valid description
   String New is an valid description
   String actual is checking the New and Old description
   (If the New description is valid, New description is written, else, the Old description)
   Expected is new

   assert
    */
    @Test
    void ChangeDescriptionApproved(){
        String Old = "adfasf";
        String New = "adgjalgdjsag";
        String actual = Check.ChangeDescription(New, Old);
        assertEquals(New, actual);
    }

    /*
   This is used to check the new description
   To test the Description
   String Old is a valid description
   String New is an invalid description
   String actual is checking the New and Old description
   (If the New description is valid, New description is written, else, the Old description)
   Expected is old

   assert
    */
    @Test
    void ChangeDescriptionUnapproved(){
        String Old = "adfasf";
        String New = "";
        String actual = Check.ChangeDescription(New, Old);
        assertEquals(Old, actual);
    }

    /*
   This is used to check the new progress
   To test the progress
   String Old is a valid progress
   String New is an valid progrress
   String actual is checking the New and Old progress
   (If the New progress is valid, New progress is written, else, the Old progress)
   Expected is new

   assert
    */
    @Test
    void ChangeProgressApproved(){
        String Old = "Completed";
        String New = "Incomplete";
        String actual = Check.ChangeProgress(New, Old);
        assertEquals(New, actual);
    }

    /*
    This is used to check the new progress
    To test the progress
    String Old is a valid progress
     String New is an invalid progrress
    String actual is checking the New and Old progress
    (If the New progress is valid, New progress is written, else, the Old progress)
     Expected is old

  assert
   */
    @Test
    void ChangeProgressUnapproved(){
        String Old = "Completed";
        String New = "adf";
        String actual = Check.ChangeProgress(New, Old);
        assertEquals(Old, actual);
    }
}