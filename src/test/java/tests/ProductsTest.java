package tests;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;
import static user.UserFactory.withAdminPermission;

public class ProductsTest extends BaseTest {
    List<String> goodsList = new ArrayList<>(
            List.of("Test.allTheThings() T-Shirt (Red)",
                    "Sauce Labs Fleece Jacket", "Sauce Labs Onesie"));

    @Test
    public void checkGoodsAdded() {
        loginPage.open();
        loginPage.login(withAdminPermission());
        assertEquals(productsPage.checkTitleName(), "Products");
        assertTrue(productsPage.isTitleIsDisplayed());

        for (String goods : goodsList) {
            productsPage.addGoodsToCart(goods);
        }

        // productsPage.addGoodsToCart(2);

        assertEquals(productsPage.checkCounterValue(), "3");
        assertEquals(productsPage.checkCounterColor(), "rgba(226, 35, 26, 1)");
    }
}
