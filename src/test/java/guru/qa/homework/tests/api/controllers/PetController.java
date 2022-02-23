package guru.qa.homework.tests.api.controllers;

import static guru.qa.homework.tests.config.EndPoints.CREATE_PET;
import static guru.qa.homework.tests.config.EndPoints.DELETE_PET;
import static guru.qa.homework.tests.config.EndPoints.SEARCH_PET;
import static io.restassured.RestAssured.given;

import guru.qa.homework.tests.api.models.PetDto;
import guru.qa.homework.tests.utils.Specifications;
import io.restassured.response.Response;

public class PetController {

  public Response postMethodPet(PetDto body) {
    return given()
        .spec(Specifications.requestSpec())
        .body(body)
        .post(CREATE_PET);
  }

  public Response getMethodPet(int petId) {
    return given()
        .spec(Specifications.requestSpec())
        .get(SEARCH_PET, petId);
  }

  public Response deleteMethodPet(int petId) {
    return given()
        .spec(Specifications.requestSpec())
        .delete(DELETE_PET, petId);
  }

}
