package service;

import dao.ItemDao;
import entity.Item;

import java.util.List;

public class ItemService {
    private static ItemService INSTANCE = null;

    private ItemService() {}

    public static ItemService getInstance() {
        if (INSTANCE == null) {
            synchronized (ItemService.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ItemService();
                }
            }
        }
        return INSTANCE;
    }

    public List<Item> getAllItem() {
        return ItemDao.getInstance().findAll();
    }
}
