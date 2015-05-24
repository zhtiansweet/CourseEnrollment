import java.util.ArrayList;

/**
 * Created by tianzhang on 5/23/15.
 */
public class Course {
    private int id;     //Unique for each course
    private String name;
    private String instructor;
    private int unit;
    private int capacity;
    private int wlCapacity;
    private String state;       //open, wl, close
    private ArrayList<Student> studentList;     //Students that has been enrolled
    private ArrayList<Student> studentWl;   //Students on waiting list

    //Constructor
    public Course(int i, String nm, String ins, int cap, int wlc){
        id = i;
        name = nm;
        instructor = ins;
        capacity = cap;
        wlCapacity = wlc;
        state = "open";
        studentList = new ArrayList<Student>();
        studentWl = new ArrayList<Student>();
    }

    //Setter & Getters
    public int getId(){return id;}
    public String getName(){return name;}
    public String getInstructor(){return instructor;}
    public void setInstructor(String ins){instructor = ins;}
    public int getUnit(){return unit;}
    public int getCapacity(){return capacity;}
    public void setCapacity(int cap){capacity = cap;}
    public int getWlCapacity(){return wlCapacity;}
    public void setWlCapacity(int wlc){wlCapacity = wlc;}
    public String getState(){return state;}
    public ArrayList<Student> getStudentList(){return studentList;}
    public ArrayList<Student> getStudentWl(){return studentWl;}

    //Print out studentList & studentWl
    public void printStudentList(){
        System.out.println();
        System.out.println("Course "+id+" student list ("+studentList.size()+"/"+capacity+"):");
        for (Student s:studentList) System.out.println(s.getId()+" "+s.getName());
        System.out.println("Course "+id+" student waiting list ("+studentWl.size()+"/"+wlCapacity+"):");
        for (Student s:studentWl) System.out.println(s.getId()+" "+s.getName());
    }

    //Add a student to this course's studentList or studentWl
    public void addStudent(Student stu){
        if (state == "close" || state == "") System.out.println("Fail to add student "+stu.getId());
        else if (state == "open"){      //If the course is open, enroll this student
            studentList.add(stu);
            System.out.println("Student "+Integer.toString(stu.getId())+" has been enrolled into course "+id);
            if (studentList.size()==capacity)   state = "wl";       //Check enrollment capacity

        }
        else {      //If the course is wl, put this student on the waiting list
            studentWl.add(stu);
            System.out.println("Student "+Integer.toString(stu.getId())+" has been added to waiting list");
            if (studentWl.size()==wlCapacity)   state = "close";       //Check waiting list capacity
        }
        System.out.println("Course "+Integer.toString(id)+" state: "+state);
    }

    //Delete a student from this course's studentList or studentWl
    public void deleteStudent(Student stu){
        if (studentList.remove(stu)){       //If the student has been enrolled (on studentList), delete him/her
            System.out.println("Student "+Integer.toString(stu.getId())+" deleted");
            if (studentList.size()==capacity-1){        //The studentList was full before deletion
                if (studentWl.isEmpty())    state = "open";     //If no student is on waiting list, set state as "open"
                else{       //There are some students on the waiting list
                    studentList.add(studentWl.get(0));
                    studentWl.remove(0);        //Enroll the first student on waiting list
                    if (studentWl.size()==wlCapacity-1){
                        state = "wl";       //If the waiting list is full before deletion, change state as "wl"
                    }
                }
            }
        }
        else if (studentWl.remove(stu)){        //If the student is on waiting list, delete him/her
            System.out.println("Student "+Integer.toString(stu.getId())+" has been deleted");
            if (studentWl.size()==wlCapacity-1){
                state = "wl";       //If the waiting list is full before deletion, change state as "wl"
            }
        }
        else{   //If student couldn't be found, print error info
            System.out.println("Student "+Integer.toString(stu.getId())+" not found");
        }
        System.out.println("Course "+Integer.toString(id)+" state: "+state);
    }
}
