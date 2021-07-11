package ucf.assignments;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
Check length

An item shall have a description
A description shall be between 1 and 256 characters in length
 */
class CheckTest {
    @Test
    void CheckDescriptionLength(){
        String description = "";
        Boolean actual = Check.CheckLength(description);
        Boolean expected = false;

        assertEquals(actual, expected);
    }

    @Test
    void CheckNameLength(){
        String name = "";
        Boolean actual = Check.CheckLength(name);
        Boolean expected = false;

        assertEquals(actual, expected);
    }

}