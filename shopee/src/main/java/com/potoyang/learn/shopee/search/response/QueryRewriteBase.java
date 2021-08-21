package com.potoyang.learn.shopee.search.response;

import lombok.Data;

import java.io.Serializable;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * @since 2020/1/3 11:04
 * Modified:
 * Description:
 */
@Data
public class QueryRewriteBase implements Serializable {
    private static final long serialVersionUID = -4717089316967397398L;

    private Integer FEQueryWriteStatus;
    private String ori_keyword;
    private Integer ori_totalCount;
}
