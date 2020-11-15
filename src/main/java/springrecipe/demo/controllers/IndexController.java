package springrecipe.demo.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import springrecipe.demo.services.RecipeService;
@Slf4j
@Controller
public class IndexController {
    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }


    @RequestMapping({"","/","/index"})
    public String index(Model model){
        model.addAttribute("recipe",recipeService.getRecipes());
        log.debug("Getting index Page");
        return "index";
    }
}
