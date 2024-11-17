package Database;

import java.util.Calendar;
import java.util.Date;

public class Build{
    public static void main(String[] args){
        for(int i = 0; i < 50; i++) {
            Student student = new Student("Student " + (i+1), new Date(2006 + (int) (8*Math.random()), Calendar.NOVEMBER, 28), "F", 70 + (int) (30*Math.random()), "a", 1L, "b", 2L, "Somewhere in this universe of aww, pain, agony and death i guess.");
            student.save();
            System.out.println(student.getGrade()+"\t\t"+student.getMarks());

            Fees fees = new Fees(student);
            System.out.println(fees.displayFees());
        }
    }
}