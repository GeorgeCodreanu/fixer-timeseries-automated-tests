package iceo.assignment.stepdefs;

import iceo.assignment.api.RestAssuredRequest;
import iceo.assignment.steps.GeneralSteps;
import iceo.assignment.steps.TimeseriesSteps;
import iceo.assignment.utils.SessionVars;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.core.Serenity;
import org.junit.Assert;

public class GeneralStepDefs {
    @Steps
    TimeseriesSteps timeseriesSteps;

    @Steps
    GeneralSteps generalSteps;

    @Given("the user creates a {string} request")
    public void theUserCreatesARequest(String requestType) {
        RestAssuredRequest request = null;
        switch (requestType) {
            case "TIMESERIES":
                request = timeseriesSteps.createTimeseriesRequest();
                break;
            default:
                Assert.fail("Incorrect request type!");
        }
        Serenity.setSessionVariable(SessionVars.REQUEST).to(request);
    }

    @And("a {string} is added as a apikey to the request")
    public void aIsAddedAsAApikeyToTheRequest(String apikey) {
        if(apikey.isEmpty()) return;
        generalSteps.addApiKeyToRequest(apikey);
    }

    @And("the query {string} with value {string} is added to the request")
    public void theHeaderWithValueIsAddedToTheRequest(String query, String value) {
        if(value.isEmpty()) return;
        generalSteps.addQuery(query,value);
    }

    @When("the user executes {string} on the request")
    public void theUserExecutesOnTheRequest(String method) {
        Response response = generalSteps.executeRequest(method);
        Serenity.setSessionVariable(SessionVars.RESPONSE).to(response);

    }

    @Then("then request will have {int} status code")
    public void thenRequestWillHaveStatusCode(int statusCode) {
        Response response = Serenity.sessionVariableCalled(SessionVars.RESPONSE);
        Assert.assertEquals(response.statusCode(), statusCode);
    }
}
