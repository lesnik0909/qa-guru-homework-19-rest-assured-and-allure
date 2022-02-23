package guru.qa.homework.tests.api.models;

import guru.qa.homework.tests.api.models.partial.CategoryPartialDto;
import guru.qa.homework.tests.api.models.partial.TagsItemPartialDto;
import java.util.List;
import lombok.Data;

@Data
public class PetDto {

  private List<String> photoUrls;
  private String name;
  private int id;
  private CategoryPartialDto category;
  private List<TagsItemPartialDto> tags;
  private String status;
}