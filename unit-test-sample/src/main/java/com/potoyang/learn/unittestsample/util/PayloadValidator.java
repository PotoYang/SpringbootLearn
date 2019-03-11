package com.potoyang.learn.unittestsample.util;

import com.potoyang.learn.unittestsample.model.ToDo;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2019/3/7 09:27
 * Modified:
 * Description:
 */
public class PayloadValidator {
    public static boolean validateCreatePayload(ToDo toDo) {
        return toDo.getId() != 0;
    }
}
