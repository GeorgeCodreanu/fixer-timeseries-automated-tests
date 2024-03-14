package iceo.assignment.api;

import iceo.assignment.utils.PropertyHelper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredRequest {
    private RequestSpecification request;
    private final String fullUrl;
    private boolean restAssuredLogging;

    public RestAssuredRequest(String host, String endpoint) {
        this.restAssuredLogging = Boolean.parseBoolean(PropertyHelper.getSerenityProperty("rest_assured_logging"));

        this.request = RestAssured.given();
        if (this.restAssuredLogging) {
            this.request.log().all();
        }
        this.fullUrl = host + endpoint;
    }

    public RestAssuredRequest addParam(String key, String value) {
        request.param(key, value);
        return this;
    }

    public RestAssuredRequest addQueryParam(String key, String value) {
        request.queryParam(key, value);
        return this;
    }

    public RestAssuredRequest addHeader(String key, String value) {
        request.header(key, value);
        return this;
    }

    public RestAssuredRequest setContentType(ContentType contentType) {
        request.contentType(contentType);
        return this;
    }

    public RestAssuredRequest setBody(Object body) {
        request.body(body);
        return this;
    }

    public Response get() {
        Response response = request.when().get(fullUrl);
        if (this.restAssuredLogging) {
            response.then().log().all();
        }
        return response;
    }

    public Response post() {
        Response response = request.when().post(fullUrl);
        if (this.restAssuredLogging) {
            response.then().log().all();
        }
        return response;
    }
}
