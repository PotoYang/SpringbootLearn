public class BeanUtil {
    private BeanUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static void copyProperties(Object source, Object target) {
        org.springframework.beans.BeanUtils.copyProperties(source, target);
    }
}
