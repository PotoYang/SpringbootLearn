package com.potoyang.learn.springbootfirstapplication.index;

import com.potoyang.learn.springbootfirstapplication.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2018/10/10 16:58
 * Modified By:
 * Description:
 */
@RestController
@CrossOrigin
public class IndexController {

    @Autowired
    private IndexManager indexManager;

    @GetMapping("carousel")
    public RestResult<List<Carousel>> getCarousel() {
        return new RestResult<>(indexManager.getCarousel());
    }

    @DeleteMapping("carousel")
    public RestResult<String> deleteCarousel(String path){
        return new RestResult<>(indexManager.deleteCarousel(path));
    }

    @PostMapping("image")
    public RestResult<String> addImage(MultipartFile multipartFile){
        return new RestResult<>(indexManager.addImage(multipartFile));
    }
}
