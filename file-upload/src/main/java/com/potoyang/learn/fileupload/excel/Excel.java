package com.potoyang.learn.fileupload.excel;

import java.lang.annotation.*;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * Create: 2018/8/31 15:01
 * Modified By:
 * Description:
 * @see ExcelAnnotationBeanPostProcessor
 */
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Excel {
    String title() default "";
}
