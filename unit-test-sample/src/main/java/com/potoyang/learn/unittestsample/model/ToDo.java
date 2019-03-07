package com.potoyang.learn.unittestsample.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2019/3/7 09:02
 * Modified:
 * Description:
 */
@Entity
@Data
public class ToDo {
    @Id
    @GeneratedValue
    private long id;

    private String text;

    private boolean completed;

    public ToDo() {
        super();
    }

    public ToDo(String text, boolean completed) {
        super();
        this.text = text;
        this.completed = completed;
    }

    public ToDo(long id, String text, boolean completed) {
        super();
        this.id = id;
        this.text = text;
        this.completed = completed;
    }
}
