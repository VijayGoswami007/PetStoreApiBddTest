package petStoreApiBdd;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class GetPetSteps {
     private String petId;
     private Response response;

    private  ScenarioContext scenarioContext;

    public  GetPetSteps()
    {
        this.scenarioContext = new ScenarioContext();
    }
    @Given("We have pet id saved")
    public void weHavePetIsAs() {
        this.petId = this.scenarioContext.getData("PET_ID").toString();
    }

    @When("the pet is requested from pet API")
    public void thePetIsRequestedFromPetAPI() throws Exception {

        String apiUrl = PropertiesReader.getValue("url");
        RequestSpecification request = given()
                .header("Content-Type", "application/json");

        response = given().spec(request)
                .get(apiUrl + "/" + this.petId);

    }

    @Then("the pet should be returned")
    public void thePetShouldBeReturned() throws JSONException {
        Assertions.assertEquals(200, response.statusCode());
        JSONObject objPet = new JSONObject(response.body().print());
    }
}
