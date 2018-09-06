package com.potoyang.learn.fileupload.excel;

import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * Create: 2018/8/31 15:22
 * Modified By:
 * Description:
 */
public class TitleSequenceGenerator {
    private String expression;

    private final Set<String> titles = new LinkedHashSet<>();

    public TitleSequenceGenerator(String expression) {
        this.expression = expression;
        parse(expression);
    }

    private void parse(String expression) {
        String[] fields = StringUtils.tokenizeToStringArray(expression, " ");
        doParse(fields);
    }

    private void doParse(String[] fields) {
        setTitles(this.titles, fields);
    }

    private void setTitles(Set<String> sets, String[] fields) {
        sets.addAll(Arrays.asList(fields));
    }
}
