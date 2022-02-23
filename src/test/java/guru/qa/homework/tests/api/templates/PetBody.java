package guru.qa.homework.tests.api.templates;

import guru.qa.homework.tests.api.models.PetDto;
import guru.qa.homework.tests.api.templates.partial.CategoryPartialBody;
import guru.qa.homework.tests.api.templates.partial.TagsItemPartialBody;
import java.util.Collections;

public class PetBody {

  private final CategoryPartialBody categoryPartialBody = new CategoryPartialBody();
  private final TagsItemPartialBody tagsItemPartialBody = new TagsItemPartialBody();

  public PetDto allFields() {
    PetDto body = new PetDto();
    body.setId(35);
    body.setCategory(categoryPartialBody.body(1, "Dog"));
    body.setTags(Collections.singletonList(tagsItemPartialBody.body(1, "Dog")));
    body.setName("King");
    body.setPhotoUrls(Collections.singletonList("url"));
    body.setStatus("available");

    return body;
  }

  public PetDto allFieldsForCheck() {
    PetDto body = allFields();
    body.setId(2);
    body.setName("Ryder");
    body.setCategory(categoryPartialBody.body(1, "Cat"));
    body.setStatus("pending");

    return body;
  }

}
