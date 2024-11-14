package Database;

import java.util.Calendar;
import java.util.Date;

public class Build{
    public static void main(String[] args){
        for(int i = 0; i < 5; i++) {
            Student student = new Student("The Imposter", new Date(2008, Calendar.NOVEMBER, 28), "F", 81, "a", 1L, "b", 2L, "Somewhere in this universe of aww, pain, agony and death i guess.");
            student.assign();
            student.save();

            System.out.println(new Date(2008, Calendar.NOVEMBER, 5));

        }
    }
}