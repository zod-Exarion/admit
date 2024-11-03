package Database;

import java.util.Calendar;
import java.util.Date;

public class Build{
    public static void main(String[] args){
        for(int i = 0; i < 5; i++) {
            Student student = new Student("The God 3", new Date(2010, Calendar.DECEMBER, 5), "F", 81, "a", 1L, "b", 2L);
            student.assign();
            student.save();
            System.out.println(student.getGrade());

        }
    }
}
