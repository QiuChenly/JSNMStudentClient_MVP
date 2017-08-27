package Basic.API;

/**
 * Author : QiuChenLy
 * Date    : ${DATA}
 * Func    : null
 * LastEdit: ${DATA}
 */

public interface LoginResult {
    void onSuccess(String StudentName,boolean isLeader,String session);
    void onFailed(String ErrReason);
}
