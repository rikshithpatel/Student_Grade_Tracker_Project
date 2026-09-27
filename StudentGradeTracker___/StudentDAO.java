package code.alpha.studentgradetracker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentDAO {

    public int addStudent(String name) {

        String query = "insert into project_students(name) values(?) returning id";

        try {
            Connection con = DatabaseConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, name);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");

                rs.close();
                ps.close();
                con.close();

                return id;
            }

            rs.close();
            ps.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return -1;
    }

    public void addGrade(int studentId, int grade) {

        String query =
                "insert into project_grades(student_id, grade) values(?, ?)";

        try {
            Connection con = DatabaseConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, studentId);
            ps.setInt(2, grade);

            ps.executeUpdate();

            ps.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public ArrayList<Student> getStudents() {

        ArrayList<Student> students = new ArrayList<>();

        String query = "select id, name from project_students order by id";

        try {
            Connection con = DatabaseConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                int id = rs.getInt("id");
                String name = rs.getString("name");

                Student student = new Student(id, name);

                getGrades(student);

                students.add(student);
            }

            rs.close();
            ps.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return students;
    }

    private void getGrades(Student student) {

        String query =
                "select grade from project_grades where student_id = ? order by id";

        try {
            Connection con = DatabaseConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, student.getId());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                student.addGrade(rs.getInt("grade"));
            }

            rs.close();
            ps.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}