package AdminPage;

import java.util.Calendar;
import java.util.Date;

public class Build{
    public static void main(String[] args){
        for(int i = 0; i < 5; i++) {
            Student student = new Student("The Imposter", new Date(2008 - 1900, Calendar.DECEMBER, 5), "F", 81, "a", 1L, "b", 2L);
            student.assign();
            student.save();
            System.out.println(student.getGrade());

        }
    }
}