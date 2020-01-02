package rtsp;

public class HttpResult {

    // 响应码
    public Integer code;
    // 响应体
    public String body;

    public HttpResult() {
        super();
    }

    public HttpResult(Integer code, String body) {
        super();
        this.code = code;
        this.body = body;
    }
}
