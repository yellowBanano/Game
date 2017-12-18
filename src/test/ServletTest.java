package test;

import entity.Account;
import entity.Item;
import org.junit.Assert;
import org.junit.Test;
import service.AccountService;
import service.InventoryService;

import java.util.List;

public class ServletTest {

    public static final String USERNAME = "admin";
    public static final String PASSWORD = "admin";

    @Test
    public void testLogin() {
        List<Item> allItems = InventoryService.getInstance().getAllItems(2);
        allItems.forEach(System.out::println);
        Assert.assertNotNull(allItems);
    }
}
