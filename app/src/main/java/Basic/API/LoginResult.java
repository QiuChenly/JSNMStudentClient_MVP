package Basic.API;

public interface LoginResult {
    void onSuccess(String StudentName,String session,int code);
    void onFailed(String ErrReason);
}
