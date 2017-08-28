package Basic.API;

import android.graphics.Bitmap;

import java.io.IOException;

import Basic.httpClient.HttpResult;
import Basic.httpClient.ResponseData;
import Basic.httpClient.httpClient;

import static Basic.API.utils.*;

/**
 * Author : QiuChenLy
 * Date    : ${DATA}
 * Func    : null
 * LastEdit: ${DATA}
 */

public class LoginAPI {
    /**
     * 更新必须参数
     * <p>
     * // * @param ResponseBody 返回的网页数据
     */
//    public void UpdataViewState(String ResponseBody) {
//        _viewstate = GetSubText(ResponseBody, "id=\"__VIEWSTATE\" value=\"", "\"", 0);
//        _viewStategenerator =
//                GetSubText(ResponseBody, "id=\"__VIEWSTATEGENERATOR\" value=\"", "\"", 0);
//    }
    public void login(String userName, String passWord, String vCode, LoginResult loginResult)
            throws IOException {
        boolean isLeader = false;
        String name = "";
        String session;

        String url = "http://alst.jsahvc.edu.cn/login.aspx";
        String data = "__VIEWSTATE=&__VIEWSTATEGENERATOR=&userbh=" + userName +
                "&vcode="+vCode+"&cw=&xzbz=1&pas2s=" + utils.md5(passWord);
        ResponseData responseData = httpClient.Request(url, data, httpClient.cookies);
        if (responseData.responseCode == 302) {
            //302跳转表示登陆成功
            responseData = httpClient.Request("http://alst.jsahvc.edu.cn/default.aspx",
                    httpClient.cookies);
            //是否为班干部
            if (responseData.responseText.contains("班干部")) {
                isLeader = true;
            }

            if (responseData.responseText.contains("><b>欢迎你:")) {
                name = GetSubText(responseData.responseText, "><b>欢迎你:", "\n", 0).trim();
            }
            session = httpClient.cookies;
            if (session != "" || name != "") {
                loginResult.onSuccess(name, isLeader, session);
            } else {
                loginResult.onFailed("账号或密码错误!");
            }
        } else {
            loginResult.onFailed(GetSubText(responseData.responseText,"<input name=\"cw\" type=\"hidden\" id=\"cw\" value=\"","\" />",0));
        }
    }

    public void getImage(final getImage image){
        new Thread(){
            @Override
            public void run() {
                String url = "http://alst.jsahvc.edu.cn/VCode.aspx";
                try {
                    image.onSuccess(httpClient.Request_Image(url, httpClient.cookies));
                } catch (IOException e) {
                    e.printStackTrace();
                    image.onFailed("获取验证码出现异常!");
                }
            }
        }.start();

    }
}
