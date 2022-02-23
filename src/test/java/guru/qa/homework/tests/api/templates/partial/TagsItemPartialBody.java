package guru.qa.homework.tests.api.templates.partial;

import guru.qa.homework.tests.api.models.partial.TagsItemPartialDto;

public class TagsItemPartialBody {

  public TagsItemPartialDto body(int id, String name) {
    TagsItemPartialDto body = new TagsItemPartialDto();
    body.setId(id);
    body.setName(name);

    return body;
  }

}
