package org.example;

import org.example.entity.Student;
import org.example.process.DBProcess;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Student student1 = new Student(3,"Ramiz","Abdurrehimov",1971,"34242342");
        Student student2 = new Student(4,"Jalal","Manafi",1998,"5356443");
        Student student3 = new Student(5,"Aydin","Askerov",1992,"53453524524");

        Student updateStudent = new Student(5,"Elsad",",Musayev",1995,"3423432");

//        List<Student>listOfStudents = new ArrayList<>();
//
//
//        listOfStudents.add(student1);
//        listOfStudents.add(student2);
//        listOfStudents.add(student3);
//
//        DBProcess.insertStudent(listOfStudents);

        DBProcess.deleteStudent(5);
    }
}