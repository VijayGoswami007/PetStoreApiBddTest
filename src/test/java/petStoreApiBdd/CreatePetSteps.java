package petStoreApiBdd;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;

import static io.restassured.RestAssured.given;

public class CreatePetSteps {

    private  DataTable petData;
    private  DataTable tagData;
    private  Response response;
    private  ScenarioContext scenarioContext;

public  CreatePetSteps()
{
    this.scenarioContext = new ScenarioContext();
}

    @Given("We have pet data as below")
    public void weHavePetDataAsBelow(DataTable table) {
        this.petData = table;
    }

    @And("we have following tags as below")
    public void weHaveFollowingTags(DataTable table) {
        this.tagData = table;
    }

    @When("the pet is submitted to pet API with status {string}")
    public void thePetIsSubmittedToPetAPIWithStatusAvailable(String status) throws Exception {
        JSONObject category=new JSONObject();
        category.put("name",this.petData.row(0).get(1));

        String[] urls = new String[1];
        urls[0] = this.petData.row(0).get(3);

        JSONArray tags=new JSONArray();
        JSONObject tag=new JSONObject();
        tag.put("name", this.tagData.row(0).get(1));
        tags.put(0, tag);

        JSONObject obj=new JSONObject();
        obj.put("category",category);
        obj.put("name",this.petData.row(0).get(2));
        obj.put("photoUrls", urls);

        obj.put("tags", tags);

        obj.put("status", status);

        String apiUrl = PropertiesReader.getValue("url");
        RequestSpecification request = given()
                .header("Content-Type", "application/json")
                .body(obj.toString());

        response = given().spec(request)
                .post(apiUrl);
    }

    @Then("the pet should be saved")
    public void thePetShouldBeAvailableForSearching() throws JSONException {
        //Assert 200 OK
        Assertions.assertEquals(200, response.statusCode());

        // Assert Id > 0
        JSONObject objPet = new JSONObject(response.body().asString());
        long petId = objPet.getLong("id");

        Assertions.assertTrue(petId > 0);
        this.scenarioContext.save("PET_ID", petId);
    }
}
