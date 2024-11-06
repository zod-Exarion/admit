package AdminPage;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        ArrayList<Student> s = Student.fetch();
        for(var ss: s){
            System.out.println(ss.getAge());
            System.out.println(ss.getGrade());
        }
    }
}
