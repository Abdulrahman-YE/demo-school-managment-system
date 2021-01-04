package models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DAO;
import utils.DateUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TeacherModel {

    /**
     * Get the teacher using id
     *
     * @params id
     */
    public Teacher getByID(int id) throws SQLException {
        Teacher teacher = new Teacher();

        try (Connection connection = DAO.getConnection()) {
            String selectQuery = "SELECT teacher_name, teacher_gender, teacher_address, teacher_dob, teacher_phone, teacher_salary " +
                    " FROM teachers WHERE teacher_id = ? ";

            try (PreparedStatement ps = connection.prepareStatement(selectQuery)) {
                ps.setInt(1, id);

                try (ResultSet result = ps.executeQuery()) {
                    while (result.next()) {
                        teacher.setID(id);
                        teacher.setName(result.getString("teacher_name"));
                        teacher.setGender(result.getString("teacher_gender"));
                        teacher.setAddress(result.getString("teacher_address"));

                        //Convert from sql.Date to LocalDate
                        teacher.setDOB(DateUtil.toLocalDate(
                                result.getDate("teacher_dob")
                        ));
                        teacher.setPhone(result.getString("teacher_phone"));
                        teacher.setSalary(result.getInt("teacher_salary"));
                    }
                    return teacher;

                }

            }

        }

    }

    /**
     * Get a all levels in the databse
     *
     * @return ArrayList<Level></>
     */
    public ObservableList<Teacher> getAll() throws SQLException {
        ObservableList<Teacher> teachers = FXCollections.observableArrayList();

        try (Connection connection = DAO.getConnection()) {
            String selectQuery = "SELECT teacher_id, teacher_name, teacher_gender, teacher_address, teacher_dob, teacher_phone, teacher_salary " +
                    "FROM teachers ";
            try (PreparedStatement ps = connection.prepareStatement(selectQuery)) {
                try (ResultSet result = ps.executeQuery()) {
                    while (result.next()) {
                        Teacher teacher = new Teacher();
                        teacher.setID(result.getInt("teacher_id"));
                        teacher.setName(result.getString("teacher_name"));
                        teacher.setGender(result.getString("teacher_gender"));
                        teacher.setAddress(result.getString("teacher_address"));

                        //Convert from sql.Date to LocalDate
                        teacher.setDOB(DateUtil.toLocalDate(
                                result.getDate("teacher_dob")
                        ));
                        teacher.setPhone(result.getString("teacher_phone"));
                        teacher.setSalary(result.getInt("teacher_salary"));

                        teachers.add(teacher);


                    }

                    return teachers;

                }

            }

        }
    }

    public int add(Teacher teacher) throws SQLException {
        try (Connection connection = DAO.getConnection()) {
            String insertQuery = "INSERT INTO teachers(teacher_name, teacher_gender, teacher_address, teacher_dob, teacher_phone, teacher_salary ) " +
                    "VALUES ( ?, ?, ?, ?, ?, ? )";

            try (PreparedStatement ps = connection.prepareStatement(insertQuery)) {
                ps.setString(1, teacher.getName());
                ps.setString(2, teacher.getGender());
                ps.setString(3, teacher.getAddress());

                ps.setDate(4, DateUtil.toSqlDate(teacher.getDOB()));

                ps.setString(5, teacher.getPhone());
                ps.setInt(6, teacher.getSalary());

                if (ps.executeUpdate() >= 0) {
                    return 1;
                }

                return 0;
            }
        }
    }

    public int update(Teacher teacher) throws SQLException {
        try (Connection connection = DAO.getConnection()) {
            String updateQuery = "UPDATE teachers " +
                    "SET teacher_name = ? ," +
                    "teacher_gender = ? ," +
                    "teacher_address = ? ," +
                    "teacher_dob = ? ," +
                    "teacher_phone = ? ," +
                    "teacher_salary = ? " +
                    "WHERE teacher_id = ? ";

            try (PreparedStatement ps = connection.prepareStatement(updateQuery)) {
                ps.setString(1, teacher.getName());
                ps.setString(2, teacher.getGender());
                ps.setString(3, teacher.getAddress());

                //Convert From LocalDate to Sql.Date
                ps.setDate(4, DateUtil.toSqlDate(teacher.getDOB()));

                ps.setString(5, teacher.getPhone());
                ps.setInt(6, teacher.getSalary());
                ps.setInt(7, teacher.getID());

                if (ps.executeUpdate() >= 0) {
                    return 1;
                }

                return 0;
            }
        }

    }

    public int delete(Teacher teacher) throws SQLException {
        try (Connection connection = DAO.getConnection()) {
            String updateQuery = "DELETE FROM teachers WHERE teacher_id = ? ";

            try (PreparedStatement ps = connection.prepareStatement(updateQuery)) {
                ps.setInt(1, teacher.getID());

                if (ps.executeUpdate() >= 0) {
                    return 1;
                }

                return 0;
            }
        }
    }
}
