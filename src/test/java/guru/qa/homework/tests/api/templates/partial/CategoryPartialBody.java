package guru.qa.homework.tests.api.templates.partial;

import guru.qa.homework.tests.api.models.partial.CategoryPartialDto;

public class CategoryPartialBody {

  public CategoryPartialDto body(int id, String name) {
    CategoryPartialDto body = new CategoryPartialDto();
    body.setId(id);
    body.setName(name);

    return body;
  }

}
