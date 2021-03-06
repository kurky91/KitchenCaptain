package ch.kitchencaptain.repository;

import ch.kitchencaptain.model.Ingredient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;

/**
 * Created by User on 15.01.2016.
 */


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/persistenceContext.xml")
public class IngredientRepositoryIT {
    @Inject
    private IngredientRepository ingredientRepository;

    @Test
    public void test(){
        Ingredient newIngredient = new Ingredient();
        newIngredient.setTitle("Tomato");
        ingredientRepository.save(newIngredient);

        Ingredient readIngredient = ingredientRepository.findOne(newIngredient.getId());
        org.junit.Assert.assertTrue(newIngredient.getTitle().equals(readIngredient.getTitle()));

        readIngredient.setTitle("Banana");
        readIngredient = ingredientRepository.save(readIngredient);
        Ingredient updatedIngredient = ingredientRepository.findOne(readIngredient.getId());
        org.junit.Assert.assertTrue(readIngredient.getTitle().equals(updatedIngredient.getTitle()));

        ingredientRepository.delete(updatedIngredient);
        Ingredient deletedIngredient = ingredientRepository.findOne(readIngredient.getId());
        org.junit.Assert.assertNull(deletedIngredient);
    }
}
