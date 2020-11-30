package springrecipe.demo.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import springrecipe.demo.services.ImageService;
import springrecipe.demo.services.RecipeService;

@Slf4j
@Controller
public class ImageController {

    private final RecipeService recipeService;
    private final ImageService imageService;
    public ImageController(RecipeService recipeService, ImageService imageService) {
        this.recipeService = recipeService;
        this.imageService = imageService;
    }
    @GetMapping("/recipe/{id}/image")
    public String showUploadForm(@PathVariable Long id, Model model){
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));
      return "recipe/imageuploadform";
    }

    @PostMapping("/recipe/{id}/image")
    public String handleImageUpload(@PathVariable Long id, @RequestParam("imagefile") MultipartFile file){
        imageService.saveImage(Long.valueOf(id), file);
        return "redirect:/recipe/"+ id +"/show";
    }
}
