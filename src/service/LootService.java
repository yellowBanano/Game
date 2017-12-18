package service;

import dao.LootDao;
import entity.Item;

import java.util.List;

public class LootService {
    private static LootService INSTANCE = null;

    private LootService() {}

    public static LootService getInstance() {
        if (INSTANCE == null) {
            synchronized (LootService.class) {
                if (INSTANCE == null) {
                    INSTANCE = new LootService();
                }
            }
        }
        return INSTANCE;
    }

    public List<Item> getAllItems(long idNpc) {
        return LootDao.getInstance().findAllById(idNpc);
    }
}
