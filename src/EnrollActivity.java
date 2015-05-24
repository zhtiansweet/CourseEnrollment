/**
 * Created by tianzhang on 5/23/15.
 */
public interface EnrollActivity {
    
    void add(Course cou, UndergradStudent stu);     //The undergrad stu want to select cou
    void add(Course cou, GradStudent stu);      //The grad stu want to select cou
    void delete(Course cou, Student stu);       //The stu want to delete cou

}
