package AdminPage;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class Student implements Serializable{
    private String name,gender,fname,mname,ID;
    private int age, marks, grade, roll, sch;
    private long fno,mno;
    private char section;
    private Date dob;

    public static final int MIN_GRADE = 5;
    public static final int MAX_GRADE = 12;

    public static final FileSystem directory = new FileSystem("Students/",true);

    public Student(){};

    public Student(String name, Date dob, String gender, int marks, String fname, long fno, String mname, long mno){
        this.name = name;
        this.dob = dob;
        this.age = 2024 - dob.getYear();
        this.gender = gender;
        this.marks = marks;
        this.fname = fname;
        this.fno = fno;
        this.mname = mname;
        this.mno = mno;

        assign();
    }

    @Override
    public String toString(){
        return name + ID;
    }

    void assign(){
        grade = age - 6;
        section = (char) (74 - marks/10);
        //roll = ...
        sch = marks>95?90:(marks>85?75:marks-50);

        ID = grade + Character.toString(section) + "_" + (int) (Math.random() * 1000 + 9000);
    }

    void display(){
        System.out.println("Main Details for " + name + "\n" + grade + " " + section + " " + sch + " " + dob.toString());
        System.out.println("Unique ID= "+ID + "\n\n");
    }

    void save(){
        try{
            FileOutputStream fos = new FileOutputStream("Students/"+this.ID+".txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.close();

        }catch(Exception e){

            System.out.println("Failed");
            e.printStackTrace();
        }
    }

    public static ArrayList<Student> fetch(){
        ArrayList<Student> students = new ArrayList<>();

        try{
            String[] files = directory.readAll();

            for(String file : files){
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                Student newStudent = (Student) ois.readObject();
                ois.close();

                students.add(newStudent);
            }
        }
        catch(Exception e){
            System.out.println("Error");
            e.printStackTrace();
        }

        return students;
    }

    public static ArrayList<Student> fetch(int grade){
        ArrayList<Student> students = new ArrayList<>();

        try{
            String[] files = directory.readAll();

            for(String file : files){
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                Student newStudent = (Student) ois.readObject();
                ois.close();

                if(newStudent.getGrade() == grade)
                    students.add(newStudent);
            }
        }
        catch(Exception e){
            System.out.println("Error");
            e.printStackTrace();
        }

        return students;
    }


    public static int numberOfStudents(){
        return directory.readAll().length;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getFname() {
        return fname;
    }

    public String getMname() {
        return mname;
    }

    public String getID() {
        return ID;
    }

    public int getAge() {
        return age;
    }

    public int getMarks() {
        return marks;
    }

    public int getGrade() {
        return grade;
    }

    public int getRoll() {
        return roll;
    }

    public int getSch() {
        return sch;
    }

    public long getFno() {
        return fno;
    }

    public long getMno() {
        return mno;
    }

    public char getSection() {
        return section;
    }

    public Date getDob(){
        return dob;
    }
}
