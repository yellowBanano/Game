package service;

import dao.AvatarDao;
import dao.InventoryDao;
import entity.Avatar;
import entity.Inventory;
import entity.Item;

import java.util.List;

public class InventoryService {
    private static InventoryService INSTANCE = null;

    private InventoryService() {}

    public static InventoryService getInstance() {
        if (INSTANCE == null) {
            synchronized (InventoryService.class) {
                if (INSTANCE == null) {
                    INSTANCE = new InventoryService();
                }
            }
        }
        return INSTANCE;
    }

    public List<Item> getAllItems(long idAvatar) {
        return InventoryDao.getInstance().findAllById(idAvatar);
    }
    
    public void sellItem(Avatar avatar, Item item) {
        avatar.setMoney(avatar.getMoney() + item.getCost());
        AvatarDao.getInstance().update(avatar, avatar.getArmor().getId(), avatar.getWeapon().getId());
        InventoryDao.getInstance().delete(new Inventory(avatar, item));
    }
}
