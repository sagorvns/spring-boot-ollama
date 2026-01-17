package Basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Emp   {

    int id;
    String name;
    public Emp(int id, String name) {
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

}
public class ComparatorClassDemo {

    public static  void main(String arr[]){

        List<Student> list = new ArrayList<>();
        list.add(new Student(11,"Raman"));
        list.add(new Student(33,"Krishna"));
        list.add(new Student(22,"Visnu"));

         System.out.println(list);
        Collections.sort(list,(o1,o2)->o1.id-(o2.id) );
        System.out.println(list);

    }
}
