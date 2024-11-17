package Database;

import java.util.HashMap;

public class Fees implements java.io.Serializable {

    private final Student s;
    private boolean paid;
    private double admit;
    private double tuition;
    private double practical;
    private double fine;
    private double monthly;
    private double total;

    Fees(Student student){
        s = student;
        setFees();
    }

    void setFees(){
        admit = 100000;
        tuition = (1+(s.getGrade()/10.0))*4000;
        practical = s.getGrade()>10?3000:500;
        if(!paid) fine = 500;
        monthly = tuition + practical + (isPaid()?fine:0);

        total = monthly*9 + admit;
        total *= (1 - (s.getSch() / 100));
        total = Math.round(100 * total) /100.0;
    }

    void togglePaid(){paid = !paid;}

    public boolean isPaid() {return paid;}
    public double getAdmit() {return admit;}
    public double getTuition() {return tuition;}
    public double getPractical() {return practical;}
    public double getFine() {return fine;}
    public double getMonthly() {return monthly;}
    public double getTotal() {return total;}

    public HashMap<String,Double> displayFees(){
        HashMap<String,Double> map = new HashMap<>();
        String[] arr = {"Admission Fees: Rs.","Tuition Fees: Rs.","Practical Fees: Rs.","Late Fine: Rs.","Monthly Fees: Rs.","Student Scholarship: Rs.","Total Fees: Rs."};
        map.put(arr[0],getAdmit());
        map.put(arr[1],getTuition());
        map.put(arr[2],getPractical());
        map.put(arr[3],getFine());
        map.put(arr[4],getMonthly());
        map.put(arr[5],s.getSch());
        map.put(arr[6],getTotal());

        return map;
    }
}
