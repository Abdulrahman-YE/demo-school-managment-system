package models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LevelModel {

    /**
     * Get the level using id
     *
     * @params id
     */
    public Level getByID(int id) throws SQLException {
        Level level = new Level();

        try (Connection connection = DAO.getConnection()) {
            String selectQuery = "SELECT level_name FROM levels WHERE levels.level_id = ? ";

            try (PreparedStatement ps = connection.prepareStatement(selectQuery)) {
                ps.setInt(1, id);

                System.out.println(ps.toString());
                try (ResultSet result = ps.executeQuery()) {
                    while (result.next()) {
                        level.setID(id);
                        level.setName(result.getString("level_name"));

                    }
                    return level;

                }

            }

        }

    }

    /**
     * Get a all levels in the databse
     *
     * @return ArrayList<Level></>
     */
    public ObservableList<Level> getAll() throws SQLException {
        ObservableList<Level> levels = FXCollections.observableArrayList();

        try (Connection connection = DAO.getConnection()) {
            String selectQuery = "SELECT level_id, level_name FROM levels ";

            try (PreparedStatement ps = connection.prepareStatement(selectQuery)) {
                try (ResultSet result = ps.executeQuery()) {
                    while (result.next()) {
                        Level level = new Level();
                        level.setID(result.getInt("level_id"));
                        level.setName(result.getString("level_name"));

                        levels.add(level);

                    }

                    return levels;

                }

            }

        }
    }

    public int add(Level level) throws SQLException {
        try (Connection connection = DAO.getConnection()) {
            String insertQuery = "INSERT INTO levels(level_name) " +
                    "VALUES ( ? )";

            try (PreparedStatement ps = connection.prepareStatement(insertQuery)) {
                ps.setString(1, level.getName());

                if (ps.executeUpdate() >= 0) {
                    return 1;
                }

                return 0;
            }
        }
    }

    public int update(Level level) throws SQLException {
        try (Connection connection = DAO.getConnection()) {
            String updateQuery = "UPDATE levels " +
                    "SET level_name = ? " +
                    "WHERE levels.level_id = ? ";

            try (PreparedStatement ps = connection.prepareStatement(updateQuery)) {
                ps.setString(1, level.getName());
                ps.setInt(2, level.getID());

                System.out.println(ps.toString());
                if (ps.executeUpdate() >= 0) {
                    return 1;
                }

                return 0;
            }
        }

    }

    public int delete(Level level) throws SQLException {
        try (Connection connection = DAO.getConnection()) {
            String deleteQuery = "DELETE FROM levels  WHERE level_id = ? ";

            try (PreparedStatement ps = connection.prepareStatement(deleteQuery)) {
                ps.setInt(1, level.getID());

                if (ps.executeUpdate() >= 0) {
                    return 1;
                }

                return 0;
            }
        }
    }
}
