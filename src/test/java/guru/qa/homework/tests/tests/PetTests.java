package guru.qa.homework.tests.tests;

import static net.javacrumbs.jsonunit.core.Option.IGNORING_ARRAY_ORDER;
import static net.javacrumbs.jsonunit.core.Option.TREATING_NULL_AS_ABSENT;
import static org.hamcrest.Matchers.equalTo;

import guru.qa.homework.tests.api.controllers.PetController;
import guru.qa.homework.tests.api.templates.PetBody;
import guru.qa.homework.tests.helpers.HelperMethods;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import io.qameta.allure.jsonunit.JsonPatchMatcher;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Feature("Pet controller")
@Owner("lesnik0909")
public class PetTests {

  private final HelperMethods helperMethods = new HelperMethods();
  private final PetController petController = new PetController();
  private final PetBody petBody = new PetBody();

  @Test
  @Story("POST /pet")
  @DisplayName("Create pet - positive test")
  void createPetPositiveTest() {
    Response response = petController.postMethodPet(petBody.allFields());
    response.then().statusCode(200);

    MatcherAssert.assertThat(response.asString(),
        JsonPatchMatcher.jsonEquals(petBody.allFields()));
  }

  @Test
  @Story("POST /pet")
  @DisplayName("Create pet - negative test")
  void createPetNegativeTest() {
    Response response = petController.postMethodPet(petBody.allFields());
    response.then().statusCode(200);

    MatcherAssert.assertThat(response.asString(),
        JsonPatchMatcher.jsonEquals(petBody.allFieldsForCheck())
            .when(TREATING_NULL_AS_ABSENT, IGNORING_ARRAY_ORDER));
  }

  @Test
  @Story("GET /pet")
  @DisplayName("Find pet")
  void getPetTest() {
    int id = helperMethods.createPetAndGetId();

    Response response = petController.getMethodPet(id);
    response.then()
        .statusCode(200)
        .body("id", equalTo(id));
  }

  @Test
  @Story("DELETE /pet")
  @DisplayName("Delete pet")
  void deletePetTest() {
    int id = helperMethods.createPetAndGetId();

    Response response = petController.deleteMethodPet(id);
    response.then()
        .statusCode(200)
        .body("code", equalTo(200));
  }

}
