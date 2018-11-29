import java.util.List;

public class PageResult<T> {

    private List<T> rows;

    private int page;

    private int pageSize;

    private long total;

    public PageResult(List<T> rows, int page, int pageSize, long total) {
        super();
        this.rows = rows;
        this.page = page;
        this.pageSize = pageSize;
        this.total = total;
    }

}
