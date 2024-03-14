package iceo.assignment.steps;

import iceo.assignment.api.RestAssuredRequest;
import iceo.assignment.utils.PropertyHelper;
import iceo.assignment.utils.SessionVars;
import io.restassured.response.Response;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.core.Serenity;
import org.junit.Assert;

public class GeneralSteps {

    @Step
    public void addApiKeyToRequest(String apiKeyType){
        RestAssuredRequest request = Serenity.sessionVariableCalled(SessionVars.REQUEST);
        request.addHeader("apikey", PropertyHelper.getProperty(apiKeyType));
    }

    @Step
    public void addQuery(String query, String value) {
        RestAssuredRequest request = Serenity.sessionVariableCalled(SessionVars.REQUEST);
        request.addQueryParam(query,value);
    }

    @Step
    public Response executeRequest(String method) {
        RestAssuredRequest request = Serenity.sessionVariableCalled(SessionVars.REQUEST);

        switch (method.toLowerCase()){
            case "post":
                return request.post();
            case "get":
                return request.get();
            default:
                Assert.fail("Incorrect HTTP method!");
        }
        return null;
    }
}
