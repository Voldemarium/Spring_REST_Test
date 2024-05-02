package ru.synergy.articles;

import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import ru.synergy.articles.models.Article;
import ru.synergy.articles.repository.ArticleRepository;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ArticlesRestControllerTest {
    private static final Logger log = LoggerFactory.getLogger(ArticlesRestControllerTest.class);
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ArticleRepository repository;

    @BeforeEach
    public void setRepository() {
        repository.deleteAll();

        Article article1 = new Article("Sport", "Box1", "Denis1", 7);
        Article article2 = new Article("Sport", "Box2", "Denis2", 11);
        Article article3 = new Article("Sport", "Box3", "Denis3", 0);
        Article article4 = new Article("Sport", "Box4", "Denis4", -1);
        Article article5 = new Article("Politic", "USA", "Tramp", 100);

        repository.save(article1);
        repository.save(article2);
        repository.save(article3);
        repository.save(article4);
        repository.save(article5);
    }


    @Test
    public void getByTitleTest() {
        //when
        //Тест с сортировкой с повышением рейтинга
        try {
            ResponseEntity<List<Article>> responseEntity1 = restTemplate.exchange(
                    new RequestEntity<>(HttpMethod.GET, new URI("/articles/title/rating/up?title=Sport")),
                    new ParameterizedTypeReference<>(){});
            List<Article> response = responseEntity1.getBody();

            //then
            Assertions.assertEquals(200, responseEntity1.getStatusCode().value());

            assert response != null;
            Assertions.assertEquals(2, response.size()); //check size

            Assertions.assertEquals("Box1", response.get(0).getDescription()); //check element 1
            Assertions.assertEquals("Box2", response.get(1).getDescription()); //check element 2
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

        //Тест с сортировкой с понижением рейтинга
        try {
            ResponseEntity<List<Article>> responseEntity2 = restTemplate.exchange(
                    new RequestEntity<>(HttpMethod.GET, new URI("/articles/title/rating/down?title=Sport")),
                    new ParameterizedTypeReference<>(){});
            List<Article> response = responseEntity2.getBody();

            //then
            Assertions.assertEquals(200, responseEntity2.getStatusCode().value());

            assert response != null;
            Assertions.assertEquals(2, response.size()); //check size

            Assertions.assertEquals("Box2", response.get(0).getDescription()); //check element 1
            Assertions.assertEquals("Box1", response.get(1).getDescription()); //check element 2
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
