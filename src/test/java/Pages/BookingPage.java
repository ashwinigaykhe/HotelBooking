package Pages;

import TestSteps.BrowserFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BookingPage {
    By firstNameSelector = By.id("firstname");
    By lastNameSelector = By.id("lastname");
    By totalPriceSelector = By.id("totalprice");
    By depositPaidSelector = By.id("depositpaid");
    By checkinDateSelector = By.id("checkin");
    By checkoutDateSelector = By.id("checkout");
    By saveSelector = By.cssSelector("input[value=' Save ']");
    By bookingSelector = By.cssSelector("#bookings div.row");
    By deleteSelector = By.cssSelector("input[value=\"Delete\"]");

    WebDriver driver;

    public BookingPage() {
        driver = BrowserFactory.getDriver();
    }

    public void gotoUrl() {
        driver.get("http://hotel-test.equalexperts.io/");
        driver.manage().window().maximize();
    }

    public void setFirstName(String firstName) {
        driver.findElement(firstNameSelector).sendKeys(firstName);
    }

    public void setLastName(String lastName) {
        driver.findElement(lastNameSelector).sendKeys(lastName);
    }

    public void setTotalPrice(String price) {
        driver.findElement(totalPriceSelector).sendKeys(price);
    }

    public void setDepositPaid(String deposit) {
        WebElement element = driver.findElement(depositPaidSelector);
        Select select = new Select(element);
        select.selectByVisibleText(deposit);
    }

    public void setCheckinDate(String check_in_Date) {
        driver.findElement(checkinDateSelector).sendKeys(check_in_Date);
    }

    public void setCheckoutDate(String check_out_date) {
        driver.findElement(checkoutDateSelector).sendKeys(check_out_date);
    }

    public void clickOnSave(int numberOfBookings) {
        driver.findElement(saveSelector).click();
        waitUntilBookingsAreUpdated(++numberOfBookings);
    }

    private void waitUntilBookingsAreUpdated(int numberOfBookings) {
        WebDriverWait waiter = new WebDriverWait(driver, 5);
        try {
            waiter.until(ExpectedConditions.numberOfElementsToBe(bookingSelector, numberOfBookings));
        } catch (Exception e) {
            //ignore exception
        }
    }

    public String getfirstName() {
        return (driver.findElement(firstNameSelector).getText());
    }

    public String getLastName() {
        return (driver.findElement(lastNameSelector).getText());
    }

    public boolean verifyBookingExists(String expectedRow) {
        for (WebElement currentRow : driver.findElements(bookingSelector)) {
            if (currentRow.getText().equalsIgnoreCase(expectedRow)) {
                return true;
            }
        }
        return false;
    }

    public int getNumberOfBookings() {
        return driver.findElements(bookingSelector).size();
    }

    public void deleteBooking(String bookingToBeDeleted) {
        for (WebElement currentRow : driver.findElements(bookingSelector)) {
            if (currentRow.getText().equalsIgnoreCase(bookingToBeDeleted)) {
                int numberOfBookings = getNumberOfBookings();
                currentRow.findElement(deleteSelector).click();
                waitUntilBookingsAreUpdated(--numberOfBookings);
                break;
            }
        }
    }
}
