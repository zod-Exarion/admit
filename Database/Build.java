package Database;

import Database.Fees;
import Database.Student;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

public class Build{
    public static void main(String[] args){
        for(int i = 0; i < 10; i++) {
            Student student = new Student("Student " + (i+1), new Date(2006 + (int) (8*(Math.random())), Calendar.NOVEMBER, 28), "F", 70 + (int) (30*Math.random()), "a", 1L, "b", 2L, "Somewhere in this universe of aww, pain, agony and death i guess.");
            student.setMail(i+"X");
            student.save();

            if(i%3==0)student.toggleAdmitted(true);

            student.display();
        }
    }
}