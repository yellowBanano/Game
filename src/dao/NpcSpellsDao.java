package dao;

import connection.ConnectionManager;
import entity.NpcSpell;
import entity.Spell;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NpcSpellsDao {
    private static NpcSpellsDao INSTANCE = null;

    public static NpcSpellsDao getInstance() {
        if (INSTANCE == null) {
            synchronized (NpcSpellsDao.class) {
                if (INSTANCE == null) {
                    INSTANCE = new NpcSpellsDao();
                }
            }
        }
        return INSTANCE;
    }

    public void create(NpcSpell spell) {
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "INSERT INTO npc_spells (id_NPC, id_spell) VALUES (?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setLong(1, spell.getNpc().getId());
                preparedStatement.setLong(2, spell.getSpell().getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(NpcSpell spell) {
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "DELETE FROM npc_spells WHERE id_NPC = ? AND id_spell = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setLong(1, spell.getNpc().getId());
                preparedStatement.setLong(2, spell.getSpell().getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Spell> findAllById(long idNpc) {
        List<Spell> spells = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection()) {
            String sql = "SELECT * FROM spells JOIN npc_spells ON spells.id = npc_spells.id_spell WHERE id_NPC = " + idNpc;
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        spells.add(SpellDao.createSpellFromResultSet(resultSet));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return spells;
    }
}
