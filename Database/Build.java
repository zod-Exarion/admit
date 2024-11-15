package Database;

import java.util.Calendar;
import java.util.Date;

public class Build{
    public static void main(String[] args){
        for(int i = 0; i < 30; i++) {
            Student student = new Student("Student " + (i+1), new Date(2003 + (int) (12*Math.random()), Calendar.NOVEMBER, 28), "F", 81, "a", 1L, "b", 2L, "Somewhere in this universe of aww, pain, agony and death i guess.");
            student.save();

            System.out.println("Success " + i);

        }
    }
}