package com.potoyang.learn.fileupload.excel;

import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * Create: 2018/8/31 15:40
 * Modified By:
 * Description:
 */
public class ExcelTest {

    @Excel(title = "123 345")
     static void test() {
        parse(new ExcelTest());
    }

    private static void getExcel() {
        ExcelExportUtil<Student> export = new ExcelExportUtil<>(Student.class);
        export.getExcel("123", new String[]{"124", "789"},
                new ArrayList<Student>() {
                    private static final long serialVersionUID = 1703498539325307392L;

                    {
                        add(new Student(123, "123", 123));
                        add(new Student(456, "456", 456));
                    }
                }, null);
    }

    @Excel(title = "123 435")
    public static void main(String[] args) {
        parse(new ExcelTest());
    }

    public static void parse(Object object) {
        Class clazz = object.getClass();
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Excel.class)) {
                Excel excelAnno = method.getAnnotation(Excel.class);
                System.out.println(excelAnno.title());
            }
        }
    }

    public static class Student {
        private Integer id;
        private String name;
        private Integer age;


        public Student(Integer id, String name, Integer age) {
            this.id = id;
            this.name = name;
            this.age = age;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }
}
