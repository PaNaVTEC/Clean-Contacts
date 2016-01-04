package me.panavtec.cleancontacts.domain.mappers;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class) public class ListMapperShould {

  @Mock private Mapper<String, String> mapper;

  @Test public void map_a_list_of_objects() {
    ListMapper<String, String> listMapper = new ListMapper<>(mapper);
    when(mapper.map("")).thenReturn("");

    List<String> objectsToMap = Arrays.asList("", "");
    List<String> mapped = listMapper.map(objectsToMap);

    assertThat(objectsToMap.size(), equalTo(mapped.size()));
  }
}