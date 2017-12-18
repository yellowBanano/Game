package dao;

import connection.ConnectionManager;
import entity.Spell;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SpellDao {
    private static final String SPELLS_TABLE_NAME = "spells";
    private static SpellDao INSTANCE = null;

    public static SpellDao getInstance() {
        if (INSTANCE == null) {
            synchronized (SpellDao.class) {
                if (INSTANCE == null) {
                    INSTANCE = new SpellDao();
                }
            }
        }
        return INSTANCE;
    }

//    public void create(Spell spell) {
//        try (Connection connection = ConnectionManager.getConnection()) {
//            String sql = "INSERT INTO spells (name, power, cost) VALUES (?, ?, ?)";
//            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//                preparedStatement.setString(1, spell.getName());
//                preparedStatement.setDouble(2, spell.getPower());
//                preparedStatement.setDouble(3, spell.getCost());
//                preparedStatement.executeUpdate();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void update(Spell spell) {
//        try (Connection connection = ConnectionManager.getConnection()) {
//            String sql = "UPDATE spells SET name = ?, power = ?, cost = ? WHERE id = ?";
//            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//                preparedStatement.setString(1, spell.getName());
//                preparedStatement.setDouble(2, spell.getPower());
//                preparedStatement.setDouble(3, spell.getCost());
//                preparedStatement.setLong(4, spell.getId());
//                preparedStatement.executeUpdate();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public List<Spell> findAll() {
        List<Spell> spells = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "SELECT * FROM spells";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        spells.add(createSpellFromResultSet(resultSet));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return spells;
    }

    public Optional<Spell> findById(long id) {
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "SELECT * FROM spells WHERE spells.id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setLong(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return Optional.of(createSpellFromResultSet(resultSet));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public static Spell createSpellFromResultSet(ResultSet resultSet) throws SQLException {
        return new Spell(
                resultSet.getLong(SPELLS_TABLE_NAME + ".id"),
                resultSet.getString(SPELLS_TABLE_NAME + ".name"),
                resultSet.getDouble(SPELLS_TABLE_NAME + ".power"),
                resultSet.getInt(SPELLS_TABLE_NAME + ".cost"));
    }
}
