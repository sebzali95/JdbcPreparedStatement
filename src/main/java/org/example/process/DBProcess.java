package org.example.process;

import org.example.connection.DBConnection;
import org.example.entity.Student;

import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class DBProcess {

    private static Connection connection = DBConnection.getConnetion();

    private static PreparedStatement preparedStatement = null;

    private static ResultSet resultSet = null;

    public static void createStudentTable() {

        try {
            String query = "CREATE TABLE student(studentId INT PRIMARY KEY NOT NULL, name VARCHAR(255)," +
                    " surname VARCHAR(255),birthdate INT,studentNumber VARCHAR(30))";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
            System.out.println("Student table has created successfully !");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        DBConnection.closeConnection();


    }

    public static void insertStudent(List<Student> listOfStudents) {
        String query = "INSERT INTO student (studentId,name,surname,birthdate,studentNumber) Values(?,?,?,?,?)";

        try {
            preparedStatement = connection.prepareStatement(query);

            for (Student student : listOfStudents) {
                preparedStatement.setInt(1, student.getId());
                preparedStatement.setString(2, student.getName());
                preparedStatement.setString(3, student.getSurname());
                preparedStatement.setInt(4, student.getBirthdate());
                preparedStatement.setString(5, student.getStudentNumber());

                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();

            System.out.println("Data inserted successfully !");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        DBConnection.closeConnection();
    }


    public static void updateStudent(Student student) {
        String query = "UPDATE student set name=?,surname=?,birthdate = ?,studentNumber = ? where studentId =?";
        try {

            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getSurname());
            preparedStatement.setInt(3, student.getBirthdate());
            preparedStatement.setString(4, student.getStudentNumber());
            preparedStatement.setInt(5, student.getId());

            preparedStatement.executeUpdate();


            System.out.println("Student updated from successfully !");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        DBConnection.closeConnection();

    }

    public static void deleteStudent(Integer id) {

        String query = "DELETE from student where studentId=?";
        try {

            preparedStatement = connection.prepareStatement(query);

            preparedStatement.setInt(1, id);
            preparedStatement.execute();

            System.out.println("Student deleted from successfully !");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        DBConnection.closeConnection();
    }

    public static void findStudentById() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter student ID : ");
        int studentId = scanner.nextInt();

        String query = "SELECT * FROM  student WHERE studentId=?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, studentId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Integer id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String surname = resultSet.getString(3);
                Integer birthdate = resultSet.getInt(4);
                String studentNumber = resultSet.getString(5);


                System.out.println(id + " " + name + " " + surname + " " + birthdate + " " + studentNumber + " ");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        DBConnection.closeConnection();

    }

}
