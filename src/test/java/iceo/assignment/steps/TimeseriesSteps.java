package iceo.assignment.steps;

import iceo.assignment.api.RestAssuredRequest;
import iceo.assignment.utils.PropertyHelper;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.core.Serenity;

public class TimeseriesSteps {

    @Step
    public RestAssuredRequest createTimeseriesRequest(){
        return new RestAssuredRequest(PropertyHelper.getProperty("environment"),"/fixer/timeseries");
    }
}
