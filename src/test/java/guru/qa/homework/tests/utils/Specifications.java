package guru.qa.homework.tests.utils;

import com.github.viclovsky.swagger.coverage.SwaggerCoverageRestAssured;
import guru.qa.homework.tests.config.ProjectConfig;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigFactory;

public class Specifications {

  private static final ProjectConfig cfg = ConfigFactory.create(ProjectConfig.class);
  private static final AllureRestAssured allureRestAssured = new AllureRestAssured()
      .setRequestTemplate("custom-http-request.ftl")
      .setResponseTemplate("custom-http-response.ftl");

  public static RequestSpecification requestSpec() {
    return new RequestSpecBuilder()
        .setContentType(ContentType.JSON)
        .setAccept(ContentType.JSON)
        .setBaseUri(cfg.getBaseUri())
        .addFilter(new SwaggerCoverageRestAssured())
        .addFilter(allureRestAssured)
        .addFilter(new RequestLoggingFilter())
        .addFilter(new ResponseLoggingFilter())
        .build();
  }

}
