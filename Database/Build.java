package Database;

import java.util.Calendar;
import java.util.Date;

public class Build{
    public static void main(String[] args){
        Student student = new Student("The God 2",new Date(2008 - 1900, Calendar.DECEMBER, 5),"F",81,"a",1L,"b",2L);
        student.assign();
        student.save();

        student.fetch();
    }
}
