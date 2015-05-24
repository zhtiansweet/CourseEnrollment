import java.util.ArrayList;

/**
 * Created by tianzhang on 5/23/15.
 */
public abstract class Student {
    public static final int MAX_COURSE=4;       //A student could select as many as 4 courses per quarter
    private int id;     //Unique for each student
    private String degree;      //undergrad, grad
    private String name;
    private ArrayList<Course> enrollList;      //The courses the student has been enrolled
    private ArrayList<Course> waitList;     //The courses whose waiting lists the student is on

    //Constructor
    public Student(int i, String nm, String deg){
        id = i;
        name = nm;
        degree = deg;
        enrollList = new ArrayList<Course>();
        waitList = new ArrayList<Course>();
    }

    //Setters & Getters
    public int getId(){return id;}
    public String getName(){return name;}
    public String getDegree(){return degree;}
    public ArrayList<Course> getEnrollList(){return enrollList;}
    public ArrayList<Course> getWaitList(){return enrollList;}

    //Print out the enrollList & waitList
    public void printCourseList(){
        System.out.println();
        System.out.println("Student "+id+" has been enrolled into ("+getEnrollList().size()+"/"+MAX_COURSE+"):");
        for (Course c:enrollList) System.out.println(c.getId()+" "+c.getName());
        System.out.println("Student "+id+" is on waiting list of: ");
        for (Course c:waitList) System.out.println(c.getId()+" "+c.getName());
    }

    //Add a course to this student's enrollList or waitList
    public boolean addCourse(Course cou){
        if (cou.getState()=="close"){       //If the course is close, don't add
            System.out.println("Failed to add course "+cou.getId());
            return false;
        }
        else {      //The course is open or wl
            //Check whether the student has been enrolled into this course
            for (Course c:enrollList){
                if (c.getId()==cou.getId()){
                    System.out.println("Couldn't select duplicated course");
                    return false;
                }
            }
            //Check whether the student is on the waiting list of this course
            for (Course c:waitList){
                if (c.getId()==cou.getId()){
                    System.out.println("Couldn't select duplicated course");
                    return false;
                }
            }
            if (cou.getState()=="open") {       //The course is open
                if (enrollList.size()<MAX_COURSE){
                    enrollList.add(cou);      //If this student has selected less than 4 courses, add
                }
                else {      //This student has select 4 courses
                    System.out.println("Couldn't selected more than 4 courses");
                    return false;
                }
            }
            else waitList.add(cou);     //If the course is wl, add
        }
        return true;
    }

    //Delete a course from this student's enrollList or waitList
    public void deleteCourse(Course cou){
        if (enrollList.remove(cou) || waitList.remove(cou)) System.out.println("Course "+cou.getId()+" deleted");
        else System.out.println("Course "+cou.getId()+" not found");
    }


}
