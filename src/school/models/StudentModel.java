package school.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import school.utils.DAO;
import school.utils.DateUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentModel {

    /**
     * Get the Student using id
     *
     * @params id
     */
    public Student getByID(int id) throws SQLException {
        Student student = new Student();

        try (Connection connection = DAO.getConnection()) {
            String selectQuery = "SELECT student_name, student_gender, student_address, student_dob, student_parent_number, level_id " +
                    " FROM students WHERE student_id = ? ";

            try (PreparedStatement ps = connection.prepareStatement(selectQuery)) {
                ps.setInt(1, id);

                try (ResultSet result = ps.executeQuery()) {
                    while (result.next()) {
                        student.setID(id);
                        student.setName(result.getString("student_name"));
                        student.setGender(result.getString("student_gender"));
                        student.setAddress(result.getString("student_address"));

                        //Convert from sql.Date to LocalDate
                        student.setDOB(DateUtil.toLocalDate(
                                result.getDate("student_dob")
                        ));
                        student.setParentPhone(result.getString("student_phone"));
                        student.setLevelID(result.getInt("level_id"));
                    }
                    return student;

                }

            }

        }

    }

    /**
     * Get a all levels in the databse
     *
     * @return ArrayList<Level></>
     */
    public ObservableList<Student> getAll() throws SQLException {
        ObservableList<Student> students = FXCollections.observableArrayList();

        try (Connection connection = DAO.getConnection()) {
            String selectQuery = "SELECT student_id, student_name, student_gender, student_address, student_dob, student_parent_number, level_id  " +
                    " FROM students ";
            try (PreparedStatement ps = connection.prepareStatement(selectQuery)) {
                try (ResultSet result = ps.executeQuery()) {
                    while (result.next()) {
                        Student student = new Student();
                        student.setID(result.getInt("student_id"));
                        student.setName(result.getString("student_name"));
                        student.setGender(result.getString("student_gender"));
                        student.setAddress(result.getString("student_address"));

                        //Convert from sql.Date to LocalDate
                        student.setDOB(DateUtil.toLocalDate(
                                result.getDate("student_dob")
                        ));
                        student.setParentPhone(result.getString("student_parent_number"));
                        student.setLevelID(result.getInt("level_id"));

                        students.add(student);


                    }

                    return students;

                }

            }

        }
    }

    public int add(Student student) throws SQLException {
        try (Connection connection = DAO.getConnection()) {
            String insertQuery = "INSERT INTO students(student_name, student_gender, student_address, student_dob, student_parent_number, level_id ) " +
                    "VALUES ( ?, ?, ?, ?, ?, ? )";

            try (PreparedStatement ps = connection.prepareStatement(insertQuery)) {
                ps.setString(1, student.getName());
                ps.setString(2, student.getGender());
                ps.setString(3, student.getAddress());

                ps.setDate(4, DateUtil.toSqlDate(student.getDOB()));

                ps.setString(5, student.getParentPhone());
                ps.setInt(6, student.getLevelID());

                if (ps.executeUpdate() >= 0) {
                    return 1;
                }

                return 0;
            }
        }
    }

    public int update(Student student) throws SQLException {
        try (Connection connection = DAO.getConnection()) {
            String updateQuery = "UPDATE students " +
                    "SET student_name = ? ," +
                    "student_gender = ? ," +
                    "student_address = ? ," +
                    "student_dob = ? ," +
                    "student_parent_number = ? ," +
                    "level_id = ? " +
                    "WHERE student_id = ? ";

            try (PreparedStatement ps = connection.prepareStatement(updateQuery)) {
                ps.setString(1, student.getName());
                ps.setString(2, student.getGender());
                ps.setString(3, student.getAddress());

                //Convert From LocalDate to Sql.Date
                ps.setDate(4, DateUtil.toSqlDate(student.getDOB()));

                ps.setString(5, student.getParentPhone());
                ps.setInt(6, student.getLevelID());
                ps.setInt(7, student.getID());

                if (ps.executeUpdate() >= 0) {
                    return 1;
                }

                return 0;
            }
        }

    }

    public int delete(Student student) throws SQLException {
        try (Connection connection = DAO.getConnection()) {
            String updateQuery = "DELETE FROM students WHERE student_id = ? ";

            try (PreparedStatement ps = connection.prepareStatement(updateQuery)) {
                ps.setInt(1, student.getID());

                if (ps.executeUpdate() >= 0) {
                    return 1;
                }

                return 0;
            }
        }
    }
}
