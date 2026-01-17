package Basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Student implements Comparable<Student> {

    int id;
    String name;
    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }
    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int compareTo(Student o) {
        return this.name.compareTo(o.name);
    }
}
public class ComparableClassDemo {

    public static  void main(String arr[]){

        List<Student> list = new ArrayList<>();
        list.add(new Student(11,"Raman"));
        list.add(new Student(33,"Krishna"));
        list.add(new Student(22,"Visnu"));
        System.out.println(list);
        Collections.sort(list);
    }
}
