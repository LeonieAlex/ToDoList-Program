package ucf.assignments;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

/*
A user shall be able to add a new item to the list
A user shall be able to remove an item from the list
A user shall be able to clear the list of all items
 */
class ToDoListControllerTest {
    @Test
    void addAndClearTable(){
        String actual= "2 0";
        String expected = ToDoListController.CountRows("Java Hw", "Due tomorrow Cop3330 to do list assignment", LocalDate.of(2021, Month.JULY, 11), "Incomplete");;

        assertEquals(actual, expected);
    }

    @Test
    void CheckCompleted(){

    }
}