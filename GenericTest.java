package Java.Generic;

import java.util.Arrays;

class Util {
    public static <T extends Number> int compare(T t1, T t2) {
        double val1 = t1.doubleValue();
        double val2 = t2.doubleValue();
        return Double.compare(val1, val2);
    }
}

//---------------------------------------------------------------------------

class Course<T> {
    private String name;
    private T[] students;

    public Course(String name, int capacity) {
        this.name = name;
        students = (T[]) new Object[capacity];
        //타입 파라미터로 배열을 생성하면 new T[n]으로 생성할 수 없음.
    }

    public String getName() {
        return name;
    }

    public T[] getStudents() {
        return students;
    }

    public void add(T t) {
        for (int i = 0; i < students.length; i++) {
            if (students[i] == null) {
                students[i] = t;
                break;
            }
        }
    }
}

class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return name;
    }
}

class Worker extends Person {
    public Worker(String name) {
        super(name);
    }
}

class Student extends Person {
    public Student(String name) {
        super(name);
    }
}

class HighStudent extends Student {
    public HighStudent(String name) {
        super(name);
    }
}

//---------------------------------------------------------------------------

class Parent<T,M>{
    private T kind;
    private M model;

    public void setKind(T kind) {
        this.kind = kind;
    }

    public void setModel(M model) {
        this.model = model;
    }

    public M getModel() {
        return model;
    }

    public T getKind() {
        return kind;
    }
}

class Tv{}

class Child<T,M,C> extends Parent<T,M>{
    private C company;

    public C getCompany() {
        return company;
    }

    public void setCompany(C company) {
        this.company = company;
    }
}


interface Storage<T>{
    void add(T item,int index);
    T get(int index);
}

class StorageImpl<T> implements Storage<T>{
    private T[] array;

    public StorageImpl(int capacity){
        this.array=(T[])new Object[capacity];
    }

    @Override
    public void add(T item, int index) {
        array[index]=item;
    }

    @Override
    public T get(int index) {
        return array[index];
    }
}


public class GenericTest {

    public static void registerCourse(Course<?> course) {
        System.out.println(course.getName() + " 수강생: " + Arrays.toString(course.getStudents()));
    }

    public static void registerCourseStudent(Course<? extends Student> course) {
        System.out.println(course.getName() + " 수강생: " + Arrays.toString(course.getStudents()) );
    }

    public static void registerCourseWorker(Course<? super Worker> course) {
        System.out.println(course.getName() + " 수강생: " + Arrays.toString(course.getStudents()));
    }

    public static void main(String[] args) {
/*

        int result1 = Util.compare(3, 5);
        int result2 = Util.compare(4.3, 3);
        System.out.println("result1:" + result1);
        System.out.println("result2:" + result2);

*/
/*

        Course<Person> personCourse = new Course<Person>("일반인 과정",5);
        personCourse.add(new Person("일반인"));
        personCourse.add(new Worker("직장인"));
        personCourse.add(new Student("학생"));
        personCourse.add(new HighStudent("고등학생"));

        Course<Worker> workerCourse = new Course<Worker>("직장인 과정",5);
        workerCourse.add(new Worker("직장인"));

        Course<Student> studentCourse = new Course<Student>("학생 과정",5);
        studentCourse.add(new Student("학생"));
        studentCourse.add(new HighStudent("고등학생"));

        Course<HighStudent> highStudentCourse = new Course<HighStudent>("고등학생 과정",5);
        highStudentCourse.add(new HighStudent("고등학생"));

        // Course<?>
        registerCourse(personCourse);
        registerCourse(workerCourse);
        registerCourse(studentCourse);
        registerCourse(highStudentCourse);
        System.out.println();

        // Course<? extends Student>

        //registerCourseStudent(personCourse);
        //registerCourseStudent(workerCourse);
        registerCourseStudent(studentCourse);
        registerCourseStudent(highStudentCourse);
        System.out.println();

        // Course<? super Worker>

        registerCourseWorker(personCourse);
        registerCourseWorker(workerCourse);
        //registerCourseWorker(studentCourse);
        //registerCourseWorker(highStudentCourse);
        System.out.println();

*/

        Child<Tv,String,String> child = new Child<>();
        child.setKind(new Tv());
        child.setModel("OLED TV");
        child.setCompany("LG");

        Storage<Tv> storage = new StorageImpl<Tv>(1000);
        storage.add(new Tv(),0);
        Tv tv = storage.get(0);
        System.out.println("TV instance: "+tv.toString());
    }
}
