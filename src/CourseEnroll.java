/**
 * Created by tianzhang on 5/23/15.
 */
class UndergradStudent extends Student{
    static final String degree = "undergrad";

    //Constructor
    public UndergradStudent(int i, String nm) {
        super(i, nm, degree);
    }

    @Override
    public String getDegree(){return degree;}

    public boolean add(Course cou){
        //Undergraduate students could only select courses under 400
        if (cou.getId()>=400){
            System.out.println("Couldn't select course on this level");
            return false;
        }
        else{
            if(!addCourse(cou)) return false;       //Failed to add the course
        }
        return true;
    }
}

class GradStudent extends Student{
    static final String degree = "grad";

    //Constructor
    public GradStudent(int i, String nm) {
        super(i, nm, degree);
    }

    @Override
    public String getDegree(){return degree;}

    public boolean add(Course cou){
        //Graduate students could only select courses above 300
        if (cou.getId()<300){
            System.out.println("Couldn't select course on this level");
            return false;
        }
        else{
            if(!addCourse(cou)) return false;       //Failed to select the course
        }
        return true;
    }
}


public class CourseEnroll implements EnrollActivity{

    //Implement methods in EnrollActivity
    public void add(Course cou, UndergradStudent stu){
        if(stu.add(cou)) cou.addStudent(stu);       //If stu selected cou successfully, add the stu to cou's list
        stu.printCourseList();
        cou.printStudentList();
        System.out.println("===============");
    }

    public void add(Course cou, GradStudent stu){
        if(stu.add(cou)) cou.addStudent(stu);
        stu.printCourseList();
        cou.printStudentList();
        System.out.println("===============");
    }

    public void delete(Course cou, Student stu){
        stu.deleteCourse(cou);
        cou.deleteStudent(stu);
        stu.printCourseList();
        cou.printStudentList();
        System.out.println("===============");
    }


    public static void main(String[] args) {
        CourseEnroll ce = new CourseEnroll();       //Initialize a CourseEnroll instance to implement methods above

        //Initialize some courses
        Course c0 = new Course(100, "Intro to Computer Science", "Alice", 5, 4);
        Course c1 = new Course(211, "Data Structures", "Bob", 3, 5);
        Course c2 = new Course(349, "Machine Learning", "Cindy", 2, 3);
        Course c3 = new Course(336, "Algorithms", "Quiz", 4, 1);
        Course c4 = new Course(343, "Operating System", "Ruby", 3, 2);
        Course c5 = new Course(432, "Computer Vision", "David", 5, 2);
        Course c6 = new Course(510, "Data Mining", "Emma", 4, 3);

        //Initialize some students
        UndergradStudent s0 = new UndergradStudent(12300, "Frank");
        UndergradStudent s1 = new UndergradStudent(12301, "Geek");
        GradStudent s2 = new GradStudent(12302, "Harry");
        UndergradStudent s3 = new UndergradStudent(12303, "Ivy");
        GradStudent s4 = new GradStudent(12304, "Jack");
        GradStudent s5 = new GradStudent(12305, "Katie");
        GradStudent s6 = new GradStudent(12306, "Lana");
        UndergradStudent s7 = new UndergradStudent(12307, "Mike");
        UndergradStudent s8 = new UndergradStudent(12308, "Nancy");
        GradStudent s9 = new GradStudent(12309, "Owen");
        UndergradStudent s10 = new UndergradStudent(12310, "Peter");

        //Enrollment activities
        ce.add(c1, s1);
        ce.add(c1, s5);
        ce.add(c1, s8);
        ce.add(c5, s2);
        ce.add(c2, s2);
        ce.delete(c1, s5);
        ce.add(c3, s2);
        ce.add(c6, s2);
        ce.add(c2, s10);
        ce.add(c2, s6);
        ce.add(c2, s8);
        ce.add(c2, s3);
        ce.add(c0, s0);
        ce.add(c4, s4);
        ce.add(c3, s7);
        ce.add(c3, s9);
        ce.delete(c2, s8);
        ce.add(c4, s8);
        ce.delete(c3, s9);
        ce.add(c3, s2);
        ce.add(c2, s4);
        ce.add(c2, s5);
        ce.add(c3, s2);
    }
}
