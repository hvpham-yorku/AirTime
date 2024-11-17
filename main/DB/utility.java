package main.DB;

//import java.util.Arrays;

public class utility {
    public void printResult(String[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].getClass().isArray()) {
            }
            // System.out.println(array[i].getClass().toString());
        }
    }

    public Boolean checkEmptyOrNullString(String... args) {
        for (String arg : args) {
            if (arg == null || arg.isEmpty()) {
                return false;
            }
        }
        return true;
    }
}
