# Course Enrollment System
This is an OO design of course enrollment system.

The class ```CourseEnroll``` implements interface ```EnrollActivity``` and contains a main function. You could add some courses and students and modify the enrollment activities to play with the system.

The subclasses ```UndergradStudent``` and ```GradStudent``` inherit from superclass ```Student```. And I also designed a class ```Course```.

## Enrollment Rules
* Each course has three status: open, wl(waiting list) and close. When initialized, a course's status will be set as "open".
* Each course has two student lists. One is for students that have been enrolled successfully, and the other is for students on the waiting list. 
* When a student adds a course, (s)he will be prior added to the first list. When it is full, the course status will be set as "wl", and students will be added to the waiting list then. And when the waiting list is full, the course status will be set as "close", and students couldn't select this course anymore.
* When a student delete a course, (s)he will be deleted from the lists. And if there appears a position on the "enrolled" list, the first student on the waiting list will be enrolled. Besides, the course status will change correspondingly.
* A student could select as many as 4 courses. Duplicated courses couldn't be selected.
* Undergraduate students could only select courses with id under 400, and Graduate students could only select courses with id over 300.

## Class & Interface Details
```interface EntollActivity``` methods:
* ```void add(Course cou, UndergradStudent stu)```
* ```void add(Course cou, GradStudent stu)```
* ```void delete```

```class CourseEnroll implements EnrollActivity``` methods:
* ```void add(Course cou, UndergradStudent stu)```
  * Call ```UndergradStudent.add, Course.addStudent, Student.printCourseList(), Course.printStudentList()```
* void add(Course cou, GradStudent stu)
  * Call ```GradStudent.add, Course.addStudent, Student.printCourseList(), Course.printStudentList()```
* void delete
  * Call ```Student.deleteCourse(cou), Course.deleteStudent(stu), Student.printCourseList(), Course.printStudentList()```
* main function

```class Student``` fields (all private):
* ```static final int MAX_COURSE=4``` (A student could select as many as 4 courses per quarter)
* ```int id``` (Unique for each student)
* ```String degree``` ("undergrad" or "grad")
* ```String name```
* ```ArrayList<Course> enrollList``` (The courses this student has been enrolled into)
* ```ArrayList<Course> waitList``` (The courses whose waiting lists this student is on)

```class Student``` methods (all public):
* Constructor
* Setters & Getters
* ```void printCourseList``` (Print out the enrollList & waitList)
* ```boolean addCourse``` (Add a course to this student's enrollList or waitList)
* ```void deleteCourse``` (Delete a course from this student's enrollList or waitList)

```class UndergradStudent extends Student``` extra field:  
* ```static final String degree = "undergrad"```

```class UndergradStudent extends Student``` extra method:
* ```boolean add```
    * Call ```boolean addCourse```

```class GradStudent extends Student``` extra field:
* ```static final String degree = "grad"```

```class GradStudent extends Student``` extra method:
* ```boolean add``` 
    * Call ```boolean addCourse```

```class Course``` fields (all private):
* ```int id``` (Unique for each course)
* ```String name```
* ```String instructor```
* ```int unit```
* ```int capacity```
* ```int wlCapacity```
* ```String state``` ("open", "wl", "close")
* ```ArrayList<Student> studentList``` (Students that has been enrolled)
* ```ArrayList<Student> studentWl``` (Students on waiting list)

```class Course``` methods (all public):
* Constructor
* Setters & Getters
* ```void printStudentList``` (Print out studentList & studentWl)
* ```void addStudent``` (Add a student to this course's studentList or studentWl)
* ```void deleteStudent``` (Delete a student from this course's studentList or studentWl)
