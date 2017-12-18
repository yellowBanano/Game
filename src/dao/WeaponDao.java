package dao;

import connection.ConnectionManager;
import entity.Spell;
import entity.Weapon;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WeaponDao {
    private static final String WEAPONS_TABLE_NAME = "weapons";
    private static WeaponDao INSTANCE = null;

    public static WeaponDao getInstance() {
        if (INSTANCE == null) {
            synchronized (WeaponDao.class) {
                if (INSTANCE == null) {
                    INSTANCE = new WeaponDao();
                }
            }
        }
        return INSTANCE;
    }

//    public void create(Weapon weapon) {
//        try (Connection connection = ConnectionManager.getConnection()) {
//            String sql = "INSERT INTO weapons (name, ATK) VALUES (?, ?)";
//            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//                preparedStatement.setString(1, weapon.getName());
//                preparedStatement.setInt(2, weapon.getATK());
//                preparedStatement.executeUpdate();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void update(Weapon weapon) {
//        try (Connection connection = ConnectionManager.getConnection()) {
//            String sql = "UPDATE weapons SET name = ?, ATK = ? WHERE id = ?";
//            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//                preparedStatement.setString(1, weapon.getName());
//                preparedStatement.setDouble(2, weapon.getATK());
//                preparedStatement.setLong(3, weapon.getId());
//                preparedStatement.executeUpdate();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public List<Weapon> findAll() {
        List<Weapon> weapons = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "SELECT * FROM weapons";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        weapons.add(createWeaponFromResultSet(resultSet));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return weapons;
    }

    public Optional<Weapon> findById(long id) {
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "SELECT * FROM weapons WHERE weapons.id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setLong(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return Optional.of(createWeaponFromResultSet(resultSet));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public static Weapon createWeaponFromResultSet(ResultSet resultSet) throws SQLException {
        return new Weapon(
                resultSet.getLong(WEAPONS_TABLE_NAME + ".id"),
                resultSet.getString(WEAPONS_TABLE_NAME + ".name"),
                resultSet.getInt(WEAPONS_TABLE_NAME + ".ATK"));
    }
}
