package school.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import school.utils.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SubjectModel {

    /**
     * Get the subject using id
     *
     * @params id
     */
    public Subject getByID(int id) throws SQLException {
        Subject subject = new Subject();

        try (Connection connection = DAO.getConnection()) {
            String selectQuery = "SELECT subject_name, level_id, teacher_id FROM subjects WHERE subject_id = ? ";

            try (PreparedStatement ps = connection.prepareStatement(selectQuery)) {
                ps.setInt(1, id);

                System.out.println(ps.toString());
                try (ResultSet result = ps.executeQuery()) {
                    while (result.next()) {
                        subject.setID(id);
                        subject.setName(result.getString("subject_name"));
                        subject.setLevelID(result.getInt("level_id"));
                        subject.setTeacherID(result.getInt("teacher_id"));


                    }
                    return subject;

                }

            }

        }

    }

    /**
     * Get a all levels in the databse
     *
     * @return ArrayList<Level></>
     */
    public ObservableList<Subject> getAll() throws SQLException {
        ObservableList<Subject> subjects = FXCollections.observableArrayList();

        try (Connection connection = DAO.getConnection()) {
            String selectQuery = "SELECT subject_id, subject_name, level_id, teacher_id FROM subjects ";

            try (PreparedStatement ps = connection.prepareStatement(selectQuery)) {
                try (ResultSet result = ps.executeQuery()) {
                    while (result.next()) {
                        Subject subject = new Subject();
                        subject.setID(result.getInt("subject_id"));
                        subject.setName(result.getString("subject_name"));
                        subject.setLevelID(result.getInt("level_id"));
                        subject.setTeacherID(result.getInt("teacher_id"));

                        subjects.add(subject);

                    }

                    return subjects;

                }

            }

        }
    }

    public int add(Subject subject) throws SQLException {
        try (Connection connection = DAO.getConnection()) {
            String insertQuery = "INSERT INTO subjects(subject_name, level_id, teacher_id) " +
                    "VALUES ( ?, ?, ? )";

            try (PreparedStatement ps = connection.prepareStatement(insertQuery)) {
                ps.setString(1, subject.getName());
                ps.setInt(2, subject.getLevelID());
                ps.setInt(3, subject.getTeacherID());


                if (ps.executeUpdate() >= 0) {
                    return 1;
                }

                return 0;
            }
        }
    }

    public int update(Subject subject) throws SQLException {
        try (Connection connection = DAO.getConnection()) {
            String updateQuery = "UPDATE subjects " +
                    "SET subject_name = ? ," +
                    "level_id = ? ," +
                    "teacher_id = ? " +
                    "WHERE subject_id = ? ";

            try (PreparedStatement ps = connection.prepareStatement(updateQuery)) {
                ps.setString(1, subject.getName());
                ps.setInt(2, subject.getLevelID());
                ps.setInt(3, subject.getTeacherID());
                ps.setInt(4, subject.getID());

                System.out.println(ps.toString());
                if (ps.executeUpdate() >= 0) {
                    return 1;
                }

                return 0;
            }
        }

    }

    public int delete(Subject subject) throws SQLException {
        try (Connection connection = DAO.getConnection()) {
            String deleteQuery = "DELETE FROM subjects  WHERE subject_id = ? ";

            try (PreparedStatement ps = connection.prepareStatement(deleteQuery)) {
                ps.setInt(1, subject.getID());

                if (ps.executeUpdate() >= 0) {
                    return 1;
                }

                return 0;
            }
        }
    }
}
