package com.potoyang.learn.fileupload.excel;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * Create: 2018/8/31 15:20
 * Modified By:
 * Description:
 */
public class ExcelConfigureInsert {

    private final TitleSequenceGenerator titleSequenceGenerator;

    public ExcelConfigureInsert(String expression) {
        this.titleSequenceGenerator = new TitleSequenceGenerator(expression);
    }
}
