package Database;

import Tools.FileSystem;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class Student implements Serializable{
    private String name,gender,fname,mname,ID,address;
    private int age, marks, grade, roll, sch;
    private long fno,mno;
    private char section;
    private Date dob;

    private boolean isActive,isPaid,isAdmitted;

    public static final int MIN_GRADE = 5,MAX_GRADE = 12;
    public static String path;

    public static FileSystem directory;

    public Student(){
        this.isActive = false;
        this.isPaid = false;
        this.isAdmitted = false;
        configure();
    }

    public Student(String name, Date dob, String gender, int marks, String fname, long fno, String mname, long mno, String address){
        this.name = name;
        this.dob = dob;
        this.age = 2024 - dob.getYear();
        this.gender = gender;
        this.marks = marks;
        this.fname = fname;
        this.fno = fno;
        this.mname = mname;
        this.mno = mno;
        this.address = address;
        this.isActive = false;
        this.isPaid = false;
        this.isAdmitted = false;

        assign();
    }

    void assign(){
        if(getAdmitted()){
            grade = age - 6;
            section = (char) (74 - marks/10);
            sch = marks>95?90:(marks>85?(170-marks):marks-50);
        }
        else{
            grade = 0; 
            section = 'X'; 
            sch = 0; 
        }

        configure(); 
    }

    void configure(){
        path = getAdmitted()?"Students/":"Pending/";
        directory = new FileSystem(path,true);

        ID = "" + countSize();
        this.roll = 1;
        setRoll();
    }

    public void save(){
        try{
            FileOutputStream fos = new FileOutputStream(path+this.ID+".txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.close();

        }catch(Exception e){

            System.out.println("Failed");
            e.printStackTrace();
        }
    }

    public ArrayList<Student> fetch(int grade){
        ArrayList<Student> students = new ArrayList<>();

        try{
            String[] files = directory.readAll(path);

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

    void setRoll(){
        ArrayList<Student> arr = fetch(grade);
        for(Student s : arr)
            if(section==s.section)roll++;
    }

    public static int countSize(){
        return Objects.requireNonNull(new File(path).listFiles()).length;
    }

    @Override
    public String toString(){return name + ID;}
    public String getName() {return name;}
    public String getGender() {return gender;}
    public String getFname() {return fname;}
    public String getMname() {return mname;}
    public String getID() {return ID;}
    public int getAge() {return age;}
    public int getMarks() {return marks;}
    public int getGrade() {return grade;}
    public int getRoll() {return roll;}
    public double getSch(){return sch;}
    public long getFno() {return fno;}
    public long getMno() {return mno;}
    public char getSection() {return section;}
    public Date getDob(){return dob;}
    public String getAddress(){return address;}
    public boolean getActive(){return isActive;}
    public void toggleActive(boolean flag){isActive=flag;}
    public boolean getPaid(){return isPaid;}
    public void togglePaid(boolean flag){isPaid=flag;}
    public boolean getAdmitted(){return isAdmitted;}

    public void toggleAdmitted(boolean flag){
        File file = new File(path+ID+".txt");
        if(file.delete()) System.out.println("Success");
        else System.out.println("Fail");
        isAdmitted=flag;
        assign(); 
        save();
    }

    public void display(){
        System.out.println(getName()); 
        System.out.println(getGrade()+" "+getSection()+" "+getRoll()); 
        System.out.println(getSch()); 
    }
}
