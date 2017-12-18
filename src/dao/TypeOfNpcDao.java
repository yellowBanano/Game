package dao;

import connection.ConnectionManager;
import entity.Npc;
import entity.TypeOfNpc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TypeOfNpcDao {
    private static final String TYPES_OF_NPCS_TABLE_NAME = "types_of_npcs";
    private static TypeOfNpcDao INSTANCE = null;

    public static TypeOfNpcDao getInstance() {
        if (INSTANCE == null) {
            synchronized (TypeOfNpcDao.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TypeOfNpcDao();
                }
            }
        }
        return INSTANCE;
    }

//    public void create(TypeOfNpc type) {
//        try (Connection connection = ConnectionManager.getConnection()) {
//            String sql = "INSERT INTO types_of_npcs (name, HP_multiplier, MP_multiplier, ATK_multiplier," +
//                    " DEF_multiplier, spell_power_multiplier, magic_resistance, hit_chance, evade_chance," +
//                    " crit_chance, crit_multiplier) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//                preparedStatement.setString(1, type.getName());
//                preparedStatement.setDouble(2, type.getMultiplierHP());
//                preparedStatement.setDouble(3, type.getMultiplierMP());
//                preparedStatement.setDouble(4, type.getMultiplierATK());
//                preparedStatement.setDouble(5, type.getMultiplierDEF());
//                preparedStatement.setDouble(6, type.getSpellPowerMultiplier());
//                preparedStatement.setDouble(7, type.getMagicResistance());
//                preparedStatement.setDouble(8, type.getHitChance());
//                preparedStatement.setDouble(9, type.getEvadeChance());
//                preparedStatement.setDouble(10, type.getCritChance());
//                preparedStatement.setDouble(11, type.getCritMultiplier());
//                preparedStatement.executeUpdate();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void update(TypeOfNpc type) {
//        try (Connection connection = ConnectionManager.getConnection()) {
//            String sql = "UPDATE NPCs SET name, HP_multiplier = ?, MP_multiplier = ?, ATK_multiplier = ?," +
//                    " DEF_multiplier = ?, spell_power_multiplier = ?, magic_resistance = ?, hit_chance = ?, evade_chance = ?," +
//                    " crit_chance = ?, crit_multiplier = ? WHERE id = ?";
//            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//                preparedStatement.setString(1, type.getName());
//                preparedStatement.setDouble(2, type.getMultiplierHP());
//                preparedStatement.setDouble(3, type.getMultiplierMP());
//                preparedStatement.setDouble(4, type.getMultiplierATK());
//                preparedStatement.setDouble(5, type.getMultiplierDEF());
//                preparedStatement.setDouble(6, type.getSpellPowerMultiplier());
//                preparedStatement.setDouble(7, type.getMagicResistance());
//                preparedStatement.setDouble(8, type.getHitChance());
//                preparedStatement.setDouble(9, type.getEvadeChance());
//                preparedStatement.setDouble(10, type.getCritChance());
//                preparedStatement.setDouble(11, type.getCritMultiplier());
//                preparedStatement.setLong(12, type.getId());
//                preparedStatement.executeUpdate();
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    public List<TypeOfNpc> findAll() {
        List<TypeOfNpc> types = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "SELECT * FROM types_of_npcs";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        types.add(createTypeOfNpcFromResultSet(resultSet));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return types;
    }

    public Optional<TypeOfNpc> findById(long id) {
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "SELECT * FROM types_of_npcs WHERE types_of_npcs.id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setLong(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return Optional.of(createTypeOfNpcFromResultSet(resultSet));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public static TypeOfNpc createTypeOfNpcFromResultSet(ResultSet resultSet) throws SQLException {
        return new TypeOfNpc(
                resultSet.getLong(TYPES_OF_NPCS_TABLE_NAME + ".id"),
                resultSet.getString(TYPES_OF_NPCS_TABLE_NAME + ".name"),
                resultSet.getDouble(TYPES_OF_NPCS_TABLE_NAME + ".HP_multiplier"),
                resultSet.getDouble(TYPES_OF_NPCS_TABLE_NAME + ".MP_multiplier"),
                resultSet.getDouble(TYPES_OF_NPCS_TABLE_NAME + ".ATK_multiplier"),
                resultSet.getDouble(TYPES_OF_NPCS_TABLE_NAME + ".DEF_multiplier"),
                resultSet.getDouble(TYPES_OF_NPCS_TABLE_NAME + ".spell_power_multiplier"),
                resultSet.getDouble(TYPES_OF_NPCS_TABLE_NAME + ".magic_resistance"),
                resultSet.getDouble(TYPES_OF_NPCS_TABLE_NAME + ".hit_chance"),
                resultSet.getDouble(TYPES_OF_NPCS_TABLE_NAME + ".evade_chance"),
                resultSet.getDouble(TYPES_OF_NPCS_TABLE_NAME + ".crit_chance"),
                resultSet.getDouble(TYPES_OF_NPCS_TABLE_NAME + ".crit_multiplier"));
    }
}
