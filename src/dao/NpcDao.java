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

public class NpcDao {
    private static final String NPCS_TABLE_NAME = "NPCs";
    private static NpcDao INSTANCE = null;

    public static NpcDao getInstance() {
        if (INSTANCE == null) {
            synchronized (NpcDao.class) {
                if (INSTANCE == null) {
                    INSTANCE = new NpcDao();
                }
            }
        }
        return INSTANCE;
    }

    public void create(Npc npc, long idType) {
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "INSERT INTO NPCs (name, money, EXP, id_type) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, npc.getName());
                preparedStatement.setInt(2, npc.getMoney());
                preparedStatement.setInt(3, npc.getEXP());
                preparedStatement.setLong(4, idType);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Npc npc, long idType) {
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "UPDATE NPCs SET name = ?, money = ?, EXP = ?, id_type = ? WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, npc.getName());
                preparedStatement.setInt(2, npc.getMoney());
                preparedStatement.setInt(3, npc.getEXP());
                preparedStatement.setLong(4, idType);
                preparedStatement.setLong(5, npc.getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Npc> findAll() {
        List<Npc> npcs = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "SELECT * FROM NPCs JOIN types_of_NPCs ON types_of_NPCs.id = NPCs.id_type";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        npcs.add(createNpcFromResultSet(resultSet));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return npcs;
    }

    public Optional<Npc> findById(long id) {
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "SELECT * FROM NPCs JOIN types_of_NPCs ON types_of_NPCs.id = npcs.id_type WHERE NPCs.id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setLong(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return Optional.of(createNpcFromResultSet(resultSet));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    private Npc createNpcFromResultSet(ResultSet resultSet) throws SQLException {
        return new Npc(
                resultSet.getLong(NPCS_TABLE_NAME + ".id"),
                resultSet.getString(NPCS_TABLE_NAME + ".name"),
                resultSet.getInt(NPCS_TABLE_NAME + ".money"),
                resultSet.getInt(NPCS_TABLE_NAME + ".EXP"),
                TypeOfNpcDao.createTypeOfNpcFromResultSet(resultSet));
    }
}
