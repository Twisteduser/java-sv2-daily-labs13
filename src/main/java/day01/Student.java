package day01;

public class Student implements Comparable<Student> {

    private int ID;
    private String name;

    public Student(int ID, String name) {
        this.ID = ID;
        this.name = name;
    }


    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Student o) {
        return Integer.valueOf(ID).compareTo(o.ID);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + ID +
                ", name='" + name + '\'' +
                '}';
    }
}
