package TestSteps;

import Pages.BookingPage;
import cucumber.api.java.en.*;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.*;

public class BookingPageSteps {
    BookingPage bookingPage;
    WebDriver driver = BrowserFactory.getDriver();

    @Given("^I am on Hotel booking Page$")
    public void iAmOnBookingPage() {
        bookingPage = new BookingPage();
        assertTrue(driver.getCurrentUrl().equalsIgnoreCase("http://hotel-test.equalexperts.io/"));
    }

    @When("^I enter Firstname as (.*)$")
    public void iGiveInputInFirstName(String firstName) {
        bookingPage.setFirstName(firstName);
    }

    @And("^I enter Surname as (.*)$")
    public void iGiveInputInLastNameAsLast_name(String surname) {
        bookingPage.setLastName(surname);
    }

    @And("^I enter Price as (.*)$")
    public void iGiveInputTotalPrice(String price) {
        bookingPage.setTotalPrice(price);
    }

    @And("^I enter Deposit as (.*)$")
    public void iGiveInputInDeposit(String deposit) {
        bookingPage.setDepositPaid(deposit);
    }

    @And("^I enter Check in date as (.*)$")
    public void iGiveInputInCheckIn(String check_in_Date) {
        bookingPage.setCheckinDate(check_in_Date);
    }

    @And("^I enter Check out date as (.*)$")
    public void iGiveInputInCheckOut(String check_out_date) {
        bookingPage.setCheckoutDate(check_out_date);
    }

    @And("^I click on Save button$")
    public void iClickOnSaveButton() {
        int numberOfBookings = bookingPage.getNumberOfBookings();
        bookingPage.clickOnSave(numberOfBookings);
    }

    @And("^New Booking should be created with (.*) and (.*) and (.*) and (.*) and (.*) and (.*)$")
    public void newBookingShouldBeCreated(String firstName, String surname, String price,
                                          String deposit, String checkInDate, String checkOutDate) {
        String expectedRow = firstName + "\n" + surname + "\n" + price + "\n" + deposit + "\n" + checkInDate + "\n" + checkOutDate;
        assertTrue(bookingPage.verifyBookingExists(expectedRow));
    }

    @And("^New Booking should not be created with (.*) and (.*) and (.*) and (.*) and (.*) and (.*)$")
    public void newBookingShouldNotBeCreated(String firstName, String surname, String price,
                                             String deposit, String checkInDate, String checkOutDate) {
        String booking = firstName + "\n" + surname + "\n" + price + "\n" + deposit + "\n" + checkInDate + "\n" + checkOutDate;
        assertFalse(bookingPage.verifyBookingExists(booking));
    }

    @Then("^I delete created booking having details as (.*) and (.*) and (.*) and (.*) and (.*) and (.*)$")
    public void iDeleteCreatedBookingHavingDetails(String firstName, String surname, String price,
                                                   String deposit, String checkInDate, String checkOutDate) {
        String bookingToBeDeleted = firstName + "\n" + surname + "\n" + price + "\n" + deposit + "\n" + checkInDate + "\n" + checkOutDate;
        bookingPage.deleteBooking(bookingToBeDeleted);
        assertFalse(bookingPage.verifyBookingExists(bookingToBeDeleted));
    }
}
