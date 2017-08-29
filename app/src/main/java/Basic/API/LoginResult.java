package Basic.API;

public interface LoginResult {
    void onSuccess(String StudentName,boolean isLeader,String session);
    void onFailed(String ErrReason);
}
