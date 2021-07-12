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
    /*
    To check whether Add, Remove and Clearing table is possible
        actual string is 2 1 0
        expected is call CountRows from ToDoListController

        assert
     */
    @Test
    void addRemoveAndClearTable(){
        String actual= "2 1 0";
        String expected = ToDoListController.CountRows("Java Hw", "Due tomorrow Cop3330 to do list assignment", LocalDate.of(2021, Month.JULY, 11), "Incomplete");;

        assertEquals(actual, expected);
    }

    /*
    To check the number of completed
        actual string is 2
        expected is call CountCompleted

        assert
     */
    @Test
    void CheckCompleted(){
        Integer expected = ToDoListController.CountCompleted();
        Integer actual = 2;

        assertEquals(actual, expected);
    }

    /*
    To check the number of incomplete
        actual string is 1
        expected is call CountIncomplete

        assert
     */
    @Test
    void CheckIncomplete(){
        Integer expected = ToDoListController.CountIncomplete();
        Integer actual = 1;

        assertEquals(actual, expected);
    }

    /*
    To check the number of tasks
        actual string is 3
        expected is call CountAll

        assert
     */
    @Test
    void CheckAll(){
        Integer expected = ToDoListController.CountAll();
        Integer actual = 3;

        assertEquals(actual, expected);
    }
}