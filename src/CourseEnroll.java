import java.util.ArrayList;
import java.util.Random;

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
    public boolean addCourse(Course cou){
        //Undergraduate students could only select courses under 400
        if (cou.getId()>=400){
            System.out.println("Couldn't select course on this level");
            return false;
        }
        else{
            if(!super.addCourse(cou)) return false;       //Failed to add the course
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
    public boolean addCourse(Course cou){
        //Graduate students could only select courses above 300
        if (cou.getId()<300){
            System.out.println("Couldn't select course on this level");
            return false;
        }
        else{
            if(!super.addCourse(cou)) return false;       //Failed to select the course
        }
        return true;
    }
}


public class CourseEnroll implements EnrollActivity{

    //Implement methods in EnrollActivity
    public void add(Course cou, UndergradStudent stu){
        System.out.println("Undergraduate student "+stu.getId()+" adds Course "+cou.getId());
        System.out.println();
        if(stu.addCourse(cou)) cou.addStudent(stu);       //If stu selected cou successfully, add the stu to cou's list
        stu.printCourseList();
        cou.printStudentList();
        System.out.println("===============");
    }

    public void add(Course cou, GradStudent stu){
        System.out.println("Graduate student "+stu.getId()+" adds Course "+cou.getId());
        System.out.println();
        if(stu.addCourse(cou)) cou.addStudent(stu);
        stu.printCourseList();
        cou.printStudentList();
        System.out.println("===============");
    }

    public void delete(Course cou, Student stu){
        System.out.println("Student "+stu.getId()+" deletes Course "+cou.getId());
        System.out.println();
        stu.deleteCourse(cou);
        cou.deleteStudent(stu);
        stu.printCourseList();
        cou.printStudentList();
        System.out.println("===============");
    }


    public static void main(String[] args) {
        CourseEnroll ce = new CourseEnroll();       //Initialize a CourseEnroll instance to implement methods above

        //Initialize some courses
        ArrayList<Course> courses = new ArrayList<>();
        courses.add(new Course(100, "Intro to Computer Science", "Alice", 2, 4));
        courses.add(new Course(211, "Data Structures", "Bob", 3, 1));
        courses.add(new Course(349, "Machine Learning", "Cindy", 2, 3));
        courses.add(new Course(336, "Algorithms", "Quiz", 3, 1));
        courses.add(new Course(343, "Operating System", "Ruby", 3, 2));
        courses.add(new Course(432, "Computer Vision", "David", 2, 2));
        courses.add(new Course(510, "Data Mining", "Emma", 3, 4));

        //Initialize some students
        ArrayList<UndergradStudent> undergradStudents = new ArrayList<>();
        undergradStudents.add(new UndergradStudent(12300, "Frank"));
        undergradStudents.add(new UndergradStudent(12301, "Geek"));
        undergradStudents.add(new UndergradStudent(12303, "Ivy"));
        undergradStudents.add(new UndergradStudent(12307, "Mike"));
        undergradStudents.add(new UndergradStudent(12308, "Nancy"));
        undergradStudents.add(new UndergradStudent(12310, "Peter"));

        ArrayList<GradStudent> gradStudents = new ArrayList<>();
        gradStudents.add(new GradStudent(12302, "Harry"));
        gradStudents.add(new GradStudent(12304, "Jack"));
        gradStudents.add(new GradStudent(12305, "Katie"));
        gradStudents.add(new GradStudent(12306, "Lana"));
        gradStudents.add(new GradStudent(12309, "Owen"));

        //Random enrollment activities
        for (int i=0;i<15;i++){
            if (new Random().nextDouble()>=0.5){
                ce.add(courses.get(randInt(0, courses.size()-1)), undergradStudents.get(randInt(0, undergradStudents.size()-1)));
            }
            if (new Random().nextDouble()>=0.5){
                ce.add(courses.get(randInt(0, courses.size()-1)), gradStudents.get(randInt(0, gradStudents.size()-1)));
            }
            if (new Random().nextDouble()>=0.5){
                ce.delete(courses.get(randInt(0, courses.size() - 1)), gradStudents.get(randInt(0, gradStudents.size() - 1)));
            }
            if (new Random().nextDouble()>=0.5){
                ce.delete(courses.get(randInt(0, courses.size() - 1)), undergradStudents.get(randInt(0, undergradStudents.size() - 1)));
            }
        }
    }

    public static int randInt(int min, int max){
        Random rm = new Random();
        return rm.nextInt(max-min+1)+min;
    }
}
