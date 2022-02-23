package guru.qa.homework.tests.helpers;

import guru.qa.homework.tests.api.controllers.PetController;
import guru.qa.homework.tests.api.templates.PetBody;
import io.qameta.allure.Step;
import io.restassured.response.Response;

public class HelperMethods {

  private final PetController petController = new PetController();
  private final PetBody petBody = new PetBody();

  @Step("Auxiliary step - Create pet")
  public int createPetAndGetId() {
    Response response = petController.postMethodPet(petBody.allFields());
    return response.jsonPath().getInt("id");
  }

}
