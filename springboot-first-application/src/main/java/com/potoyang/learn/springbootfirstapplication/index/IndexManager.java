package com.potoyang.learn.springbootfirstapplication.index;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2018/10/10 16:59
 * Modified By:
 * Description:
 */
public interface IndexManager {

    void insertCarousel(Carousel carousel);

    List<Carousel> getCarousel();

    String deleteCarousel(String path);

    List<String> addImages(List<MultipartFile> files);

    String addImage(MultipartFile file);

    String addBlog(Blog blog);

    List<Blog> getBlog();
}
