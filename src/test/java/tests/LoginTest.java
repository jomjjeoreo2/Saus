package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static user.UserFactory.withAdminPermission;

public class LoginTest extends BaseTest {
    @Test
    public void correctLogin() {
        loginPage.open();
        loginPage.login(withAdminPermission());
        assertTrue(productsPage.isTitleIsDisplayed(), "Заголовок не виден");
        //assertEquals(productsPage.getTitle(), "Products", "Неверный заголовок");
    }

    @DataProvider
    public Object[][] loginData() {
        return new Object[][]{
                {"locked_out_user", password, "Epic sadface: Sorry, this user has been locked out."},
                {"", password, "Epic sadface: Username is required"},
                {"locked_out_user", "", "Epic sadface: Password is required"},
                {"Standard_user", password, "Epic sadface: Username and password do not match any user in this service"}
        };
    }

    @Test(dataProvider = "loginData", description = "тест проверяет авторизацию заблокированного пользователя")
    public void incorrectLogin(String user, String password, String errorMsg) {
        loginPage.open();
        loginPage.login(user, password);

        assertTrue(loginPage.isErrorDisplayed(), "Нет сообщения об ошибке");
        assertEquals(loginPage.getErrorText(), errorMsg,
                "Неверный текст сообщения об ошибке");
    }
}