package school.models;

import school.utils.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountModel {

    public boolean checkAccount(Account account) throws SQLException {
        try (Connection connection = DAO.getConnection()) {
            String selectQuery = "SELECT username , password FROM accounts WHERE username = ? AND password = ? ";

            try (PreparedStatement ps = connection.prepareStatement(selectQuery)) {
                ps.setString(1, account.getUsername());
                ps.setString(2, account.getPassword());

                try (ResultSet result = ps.executeQuery()) {
                    if (result.first()) {
                        return true;
                    }
                    return false;
                    }

                }

            }

        }
    }
