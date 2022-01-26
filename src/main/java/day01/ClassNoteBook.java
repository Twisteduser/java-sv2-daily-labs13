package day01;

import java.util.*;

public class ClassNoteBook {
    private Map<Student, List<Integer>> students = new TreeMap<>(new Comparator<Student>() {
        @Override
        public int compare(Student o1, Student o2) {
            return o1.compareTo(o2);
        }
    });

    public void addStudent(Student student){
        students.put(student, new ArrayList<>());
    }

    public void addMark(int id, int mark){
        Student student;
        for(Student actual : students.keySet()){
            if(actual.getID() == id){
                student = actual;
                students.replace(student,addMarkToList(student,mark));
            }
        }
    }

    private List<Integer> addMarkToList(Student student, int mark){
        students.get(student).add(mark);
        return students.get(student);
    }

    public Map<Student, List<Integer>> getStudents() {
        return students;
    }

}