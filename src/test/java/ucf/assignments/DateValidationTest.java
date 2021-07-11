package ucf.assignments;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
An item shall have a due date
A due date shall be a valid date within the Gregorian Calendar
A due date shall be displayed to users in the format: YYYY-MM-DD
 */
class DateValidationTest {
    @Test
    void CheckMonth(){
        String date = "2002-13-20";
        Boolean actual = DateValidation.dateValidation(date);
        Boolean expected = false;

        assertEquals(actual, expected);
    }

    @Test
    void CheckDate(){
        String date = "2002-12-32";
        Boolean actual = DateValidation.dateValidation(date);
        Boolean expected = false;

        assertEquals(actual, expected);
    }

    @Test
    void ValidDate(){
        String date = "2002-02-25";
        Boolean actual = DateValidation.dateValidation(date);
        Boolean expected = true;

        assertEquals(actual, expected);
    }

    @Test
    void CheckRightFormat(){
        String date = "2002-02-25";
        Boolean actual = DateValidation.dateValidation(date);
        Boolean expected = true;

        assertEquals(actual, expected);
    }

    @Test
    void CheckWrongFormat(){
        String date = "02-25-2022";
        Boolean actual = DateValidation.dateValidation(date);
        Boolean expected = false;

        assertEquals(actual, expected);
    }
}