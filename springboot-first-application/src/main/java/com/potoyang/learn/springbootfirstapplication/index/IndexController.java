package com.potoyang.learn.springbootfirstapplication.index;

import com.potoyang.learn.springbootfirstapplication.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
