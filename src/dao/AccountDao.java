package dao;

import connection.ConnectionManager;
import entity.Account;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
public class AccountDao {
    private static final String ACCOUNTS_TABLE_NAME = "accounts";
    private static AccountDao INSTANCE = null;

    public static AccountDao getInstance() {
        if (INSTANCE == null) {
            synchronized (AccountDao.class) {
                if (INSTANCE == null) {
                    INSTANCE = new AccountDao();
                }
            }
        }
        return INSTANCE;
    }

    public void create(Account account, long idAvatar) {
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "INSERT INTO accounts (email, login, password, id_avatar) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, account.getEmail());
                preparedStatement.setString(2, account.getLogin());
                preparedStatement.setString(3, account.getPassword());
                preparedStatement.setLong(4, idAvatar);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Account> findAll() {
        List<Account> accounts = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "SELECT * FROM accounts JOIN avatars ON accounts.id_avatar = avatars.id " +
                    "JOIN armors ON avatars.id_armor = armors.id " +
                    "JOIN weapons ON avatars.id_weapon = weapons.id " +
                    "JOIN classes ON avatars.id_class = classes.id ";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        accounts.add(createAccountFromResultSet(resultSet));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accounts;
    }

    public Optional<Account> findById(long id) {
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "SELECT * FROM accounts JOIN avatars ON accounts.id_avatar = avatars.id " +
                    "JOIN armors ON avatars.id_armor = armors.id " +
                    "JOIN weapons ON avatars.id_weapon = weapons.id " +
                    "JOIN classes ON avatars.id_class = classes.id " +
                    "WHERE accounts.id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setLong(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return Optional.of(createAccountFromResultSet(resultSet));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public static Account createAccountFromResultSet(ResultSet resultSet) throws SQLException {
        return new Account(
                resultSet.getLong(ACCOUNTS_TABLE_NAME + ".id"),
                resultSet.getString(ACCOUNTS_TABLE_NAME + ".email"),
                resultSet.getString(ACCOUNTS_TABLE_NAME + ".login"),
                resultSet.getString(ACCOUNTS_TABLE_NAME + ".password"),
                AvatarDao.createAvatarFromResultSet(resultSet));
    }
}