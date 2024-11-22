package Database;

import Database.Fees;
import Database.Student;

import java.util.Calendar;
import java.util.Date;

public class Build{
    public static void main(String[] args){
        for(int i = 0; i < 50; i++) {
            Student student = new Student("Student " + (i+1), new Date(2006 + 3, Calendar.NOVEMBER, 28), "F", 70 + (int) (30*Math.random()), "a", 1L, "b", 2L, "Somewhere in this universe of aww, pain, agony and death i guess.");
            student.save();
        }
    }
}