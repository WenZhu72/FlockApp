
package com.sparta.FlockApp.steps;

import com.sparta.FlockApp.pages.SignupPage;
import com.sparta.FlockApp.pages.OnboardingPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class OnboardingStepdefs {

    SignupPage signupPage;
    OnboardingPage onboardingPage;

    @Given("I am on the FlockApp signup page")
    public void iAmOnTheSignupPage() {
        signupPage.open();
    }

    @When("I register a new account with first name {string} and last name {string}")
    public void iRegisterANewAccount(String firstName, String lastName) {

        String username = "testuser" + System.currentTimeMillis();

        signupPage.enterAndSubmitFields(
                firstName,
                lastName,
                username,
                "Password123",
                "Password123"
        );
    }

    @Then("I should be redirected to the onboarding page")
    public void iShouldBeRedirectedToOnboarding() {

        onboardingPage.waitForCondition().until(
                driver -> driver.getCurrentUrl()
                        .contains("onboarding.html")
        );

        assertThat(
                onboardingPage.getDriver().getCurrentUrl(),
                containsString("onboarding.html")
        );
    }

    @Then("my first name should be {string}")
    public void myFirstNameShouldBe(String firstName) {

        assertThat(
                onboardingPage.getFirstName(),
                is(firstName)
        );
    }

    @Then("my last name should be {string}")
    public void myLastNameShouldBe(String lastName) {

        assertThat(
                onboardingPage.getLastName(),
                is(lastName)
        );
    }

    @When("I enter {string} as my date of birth")
    public void iEnterDateOfBirth(String dateOfBirth) {
        onboardingPage.enterDateOfBirth(dateOfBirth);
    }

    @When("I select an avatar")
    public void iSelectAnAvatar() {
        onboardingPage.selectAvatar("😎");
    }

    @When("I clear the first name field")
    public void iClearTheFirstNameField() {
        onboardingPage.clearFirstName();
    }

    @When("I clear the last name field")
    public void iClearTheLastNameField() {
        onboardingPage.clearLastName();
    }

    @When("I clear the date of birth field")
    public void iClearTheDateOfBirthField() {
        onboardingPage.clearDateOfBirth();
    }

    @When("I click the Continue button")
    public void iClickContinueButton() {
        onboardingPage.clickContinueButton();
    }

    @When("I click the Skip button")
    public void iClickSkipButton() {
        onboardingPage.clickSkipButton();
    }

    @Then("I should be on onboarding step {int}")
    public void iShouldBeOnOnboardingStep(int step) {

        assertThat(
                onboardingPage.getCurrentStep(),
                is("STEP " + step + " OF 4")
        );
    }

    @Then("I should remain on onboarding step {int}")
    public void iShouldRemainOnOnboardingStep(int step) {

        assertThat(
                onboardingPage.getCurrentStep(),
                is("STEP " + step + " OF 4")
        );
    }

    @When("I select {string} as an interest")
    public void iSelectAnInterest(String interest) {
        onboardingPage.selectInterest(interest);
    }

    @When("I deselect {string} as an interest")
    public void iDeselectAnInterest(String interest) {
        onboardingPage.deselectInterest(interest);
    }

    @When("I select {string} as my city")
    public void iSelectCity(String city) {
        onboardingPage.selectCity(city);
    }

    @When("I select {string} as my distance")
    public void iSelectDistance(String distance) {
        onboardingPage.selectDistance(distance);
    }

    @Then("I should see an alert {string}")
    public void iShouldSeeAnAlert(String expectedMessage) {

        WebDriverWait wait = new WebDriverWait(
                onboardingPage.getDriver(),
                Duration.ofSeconds(10)
        );

        Alert alert = wait.until(
                ExpectedConditions.alertIsPresent()
        );

        assertThat(
                alert.getText(),
                is(expectedMessage)
        );

        alert.accept();
    }

    @Given("I have completed onboarding step 1")
    public void iHaveCompletedOnboardingStep1() {

        onboardingPage.enterDateOfBirth("2000-01-15");
        onboardingPage.clickContinueButton();

        assertThat(
                onboardingPage.getCurrentStep(),
                is("STEP 2 OF 4")
        );
    }

    @Given("I have completed onboarding steps 1 and 2")
    public void iHaveCompletedOnboardingSteps1And2() {

        iHaveCompletedOnboardingStep1();

        onboardingPage.selectInterest("Rugby");
        onboardingPage.clickContinueButton();

        assertThat(
                onboardingPage.getCurrentStep(),
                is("STEP 3 OF 4")
        );
    }

    @Given("I have completed onboarding steps 1 to 3")
    public void iHaveCompletedOnboardingSteps1To3() {

        iHaveCompletedOnboardingSteps1And2();

        onboardingPage.clickSkipButton();

        assertThat(
                onboardingPage.getCurrentStep(),
                onboardingPage.getCurrentStep(),
                is("STEP 4 OF 4")
        );
    }
}
