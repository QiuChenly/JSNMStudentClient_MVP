package Basic.httpClient;

/**
 * Author : QiuChenLy
 * Date    : ${DATA}
 * Func    : null
 * LastEdit: ${DATA}
 */

public interface HttpResult {
    void onSuccess(String text);
    void onFailed();
}
