package org.example.repository.user;

import org.example.model.User;
import org.example.model.builder.UserBuilder;
import org.example.repository.security.RightsRolesRepository;

import java.sql.*;
import java.util.List;

import static org.example.database.Constants.Tables.USER;

public class UserRepositoryMySQL implements UserRepository {

    private final Connection connection;
    private final RightsRolesRepository rightsRolesRepository;


    public UserRepositoryMySQL(Connection connection, RightsRolesRepository rightsRolesRepository) {
        this.connection = connection;
        this.rightsRolesRepository = rightsRolesRepository;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        try {
            String fetchUserSql = "SELECT * FROM `" + USER + "` WHERE `username`=? AND `password`=?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(fetchUserSql)) {
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);

                try (ResultSet userResultSet = preparedStatement.executeQuery()) {
                    if (userResultSet.next()) {
                        User user = new UserBuilder()
                                .setUsername(userResultSet.getString("username"))
                                .setPassword(userResultSet.getString("password"))
                                .setRoles(rightsRolesRepository.findRolesForUser(userResultSet.getLong("id")))
                                .build();

                        return user;
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return null;
    }


    @Override
    public boolean save(User user) {
        try {
            PreparedStatement insertUserStatement = connection
                    .prepareStatement("INSERT INTO user values (null, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            insertUserStatement.setString(1, user.getUsername());
            insertUserStatement.setString(2, user.getPassword());
            insertUserStatement.executeUpdate();

            ResultSet rs = insertUserStatement.getGeneratedKeys();
            rs.next();
            long userId = rs.getLong(1);
            user.setId(userId);

            rightsRolesRepository.addRolesToUser(user, user.getRoles());

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public void removeAll() {
        try {
            Statement statement = connection.createStatement();
            String sql = "DELETE from user where id >= 0";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean existsByUsername(String email) {
        try {
            String fetchUserSql = "SELECT * FROM " + USER + " WHERE username = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(fetchUserSql)) {
                preparedStatement.setString(1, email);

                try (ResultSet userResultSet = preparedStatement.executeQuery()) {
                    return userResultSet.next();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

}
