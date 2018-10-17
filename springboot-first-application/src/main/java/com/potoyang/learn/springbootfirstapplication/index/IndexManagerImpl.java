package com.potoyang.learn.springbootfirstapplication.index;

import com.mongodb.client.result.DeleteResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.Cleaner;

import java.io.File;
import java.io.RandomAccessFile;
import java.lang.reflect.Method;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2018/10/10 17:00
 * Modified By:
 * Description:
 */
@Service("indexManager")
public class IndexManagerImpl implements IndexManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexManagerImpl.class);
    private static final String BASE_URL = "http://192.168.20.108:10002/";
    //    private final MongoTemplate mongoTemplate;
    private final MongoOperations mongoOperations;

    @Value("${upload.tempDir}")
    private String tempDir;

    @Autowired
    public IndexManagerImpl(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    @Override
    public void insertCarousel(Carousel carousel) {
        mongoOperations.insert(carousel);
    }

    @Override
    public List<Carousel> getCarousel() {
        return mongoOperations.findAll(Carousel.class);
    }

    @Override
    public String deleteCarousel(String path) {
        LOGGER.info("deleteCarousel() => " + path);
        Criteria criteria = Criteria.where("path").is(path);
        DeleteResult deleteResult = mongoOperations.remove(Query.query(criteria), Carousel.class);
        return "" + deleteResult.getDeletedCount();
    }

    @Override
    public List<String> addImages(List<MultipartFile> files) {
        LOGGER.info("addImages() => " + tempDir);
        List<String> result = new ArrayList<>();
        File uploadDir = new File(tempDir);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        files.forEach(file -> {
            try {
                File tmpFile = new File(uploadDir, file.getOriginalFilename());
                RandomAccessFile tempRaf = new RandomAccessFile(tmpFile, "rw");
                FileChannel fileChannel = tempRaf.getChannel();
                byte[] fileData = file.getBytes();
                MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, fileData.length);
                mappedByteBuffer.put(fileData);
                // 释放
                freedMappedByteBuffer(mappedByteBuffer);
                fileChannel.close();
                result.add(BASE_URL + "img/" + file.getOriginalFilename());
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
        return result;
    }

    @Override
    public String addImage(MultipartFile file) {
        LOGGER.info("addImage() => " + tempDir);
        File uploadDir = new File(tempDir);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        try {
            File tmpFile = new File(uploadDir, file.getOriginalFilename());
            RandomAccessFile tempRaf = new RandomAccessFile(tmpFile, "rw");
            FileChannel fileChannel = tempRaf.getChannel();
            byte[] fileData = file.getBytes();
            MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, fileData.length);
            mappedByteBuffer.put(fileData);
            freedMappedByteBuffer(mappedByteBuffer);
            fileChannel.close();
            return BASE_URL + "img/" + file.getOriginalFilename();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @Override
    public String addBlog(Blog blog) {
        mongoOperations.insert(blog);
        return "success";
    }

    @Override
    public List<Blog> getBlog() {
        return mongoOperations.findAll(Blog.class);
    }

    private static void freedMappedByteBuffer(final MappedByteBuffer mappedByteBuffer) {
        try {
            if (mappedByteBuffer == null) {
                return;
            }

            mappedByteBuffer.force();
            AccessController.doPrivileged((PrivilegedAction<Object>) () -> {
                try {
                    Method getCleanerMethod = mappedByteBuffer.getClass().getMethod("cleaner", new Class[0]);
                    getCleanerMethod.setAccessible(true);
                    Cleaner cleaner = (Cleaner) getCleanerMethod.invoke(mappedByteBuffer,
                            new Object[0]);
                    cleaner.clean();
                } catch (Exception e) {
                    LOGGER.error("clean MappedByteBuffer error!!!", e);
                }
                LOGGER.info("clean MappedByteBuffer completed!!!");
                return null;
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
