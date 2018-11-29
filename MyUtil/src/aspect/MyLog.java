import java.lang.annotation.*;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2018/10/17 09:09
 * Modified By:
 * Description:
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyLog {
    String value() default "";
}
