package ucf.assignments;

import org.junit.jupiter.api.Test;

import java.io.IOException;
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
    To check whether Add
        actual string is 2
        expected is call CountAdd from ToDoListController

        assert
     */
    @Test
    void CheckAdd(){
        Integer actual= 2;
        Integer expected = ToDoListController.CountAdd();

        assertEquals(actual, expected);
    }

    /*
    To check whether Remove
        actual string is 2
        expected is call CountAdd from ToDoListController

        assert
     */
    @Test
    void CheckRemove(){
        Integer actual= 2;
        Integer expected = ToDoListController.CountRemove();

        assertEquals(actual, expected);
    }

    /*
    To check whether Clear
        actual string is 2
        expected is call CountAdd from ToDoListController

        assert
     */
    @Test
    void CheckClear(){
        Integer actual= 0;
        Integer expected = ToDoListController.CountClear();

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

    /*
    CheckJson
    set actual as 1
    set expected as method CheckJson call from ToDoListController

    assert
     */
    @Test
    void CheckJson() throws IOException {
        Integer actual = 1;
        Integer expected = ToDoListController.CheckJson();

        assertEquals(actual, expected);
    }

    /*
    CheckSaveJson
    set actual as 1
    set expected as method CheckSaveJson call from ToDoListController

    assert
     */
    @Test
    void CheckSaveJson() throws IOException {
        Integer actual = 1;
        Integer expected = ToDoListController.CheckSaveJson();

        assertEquals(actual, expected);
    }
}