package com.potoyang.learn.fileupload.excel;

import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.MethodIntrospector;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * Create: 2018/8/31 14:58
 * Modified By:
 * Description:
 */
public class ExcelAnnotationBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class<?> targetClass = AopProxyUtils.ultimateTargetClass(bean);
        Map<Method, Set<Excel>> annotatedMethods = MethodIntrospector.selectMethods(targetClass,
                (MethodIntrospector.MetadataLookup<Set<Excel>>) method -> AnnotatedElementUtils.getMergedRepeatableAnnotations(method, Excel.class));
        annotatedMethods.forEach(((method, excelMethods) -> excelMethods.forEach(excel -> processExcelData(excel, method, bean))));
        return bean;
    }

    protected void processExcelData(Excel excel, Method method, Object bean) {
        String title = excel.title();
        System.out.println(StringUtils.tokenizeToStringArray(title, " ")[0]);
//        new ExcelConfigureInsert(title);
    }
}
