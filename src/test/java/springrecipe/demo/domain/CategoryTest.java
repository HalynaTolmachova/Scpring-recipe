package springrecipe.demo.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    Category category;

    @BeforeEach
    public void setUp(){
        category =new Category();
    }
    @Test
    void getId() {
        Long idValue = 4L;
        category.setId(idValue);
        assertEquals( idValue, category.getId());
    }

    @Test
    void getDescription() {
        String descrValue = "description value";
        category.setDescription(descrValue);
        assertEquals(descrValue, category.getDescription());
    }

}