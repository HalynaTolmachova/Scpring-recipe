package springrecipe.demo.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import springrecipe.demo.domain.Recipe;
import springrecipe.demo.repositories.RecipeRepository;

import java.io.IOException;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService {
    private final RecipeRepository recipeRepository;

    public ImageServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void saveImage(Long id, MultipartFile file) {
       try{
           Recipe recipe = recipeRepository.findById(Long.valueOf(id)).get();
           Byte [] byteObject = new Byte[file.getBytes().length];
           int i =0;
           log.debug("Error occured="+file.getBytes().length);
           for(byte b:file.getBytes()){
               byteObject[i++]=b;
           }
           recipe.setImage(byteObject);
           recipeRepository.save(recipe);


       }catch (IOException e){
           log.debug("Error occured"+e);
           e.printStackTrace();
       }



    }
}
