package Database;

public class Build{
    public static void main(String[] args){
        Student student = new Student("No U!",2008,"M",77,"a",1,"b",2);
        student.assign();
        student.save();

        student.fetch();
    }
}