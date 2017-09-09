package Basic.API;

/**
 * Created by QiuChenly on 2017/9/9.
 */

public class loginRet {
//            {"acceptSecure":null,"appInfoList":null,
//            "campusTvUserInfo":null,"curSchoolId":0,"divisionInfo":null,
//            "infoId":0,"isBindCampus":0,"isConfirm":0,"mail":0,"mobile":null,
//            "msg":"用户:123不存在","msgPublishTags":null,"msgReadTags":null,
//            "msgState":2,"ownForumFlag":false,"schoolInfo":null,"secure":0,
//            "tel":0,"time":0,"token":null,"tvAppInfoList":null,"tvToken":null,
//            "userBaseInfo":null,"userLoginInfo":null,"uuId":null,"uuidIv":null,
//            "uuidKey":null}

    //{"acceptSecure":null,"appInfoList":null,"campusTvUserInfo":null,"curSchoolId":0,
    // "divisionInfo":null,"infoId":0,"isBindCampus":0,"isConfirm":0,"mail":0,"mobile":null,
    // "msg":"请先激活该设备","msgPublishTags":null,"msgReadTags":null,"msgState":100004,
    // "ownForumFlag":false,"schoolInfo":null,"secure":0,"tel":0,"time":0,"token":null,
    // "tvAppInfoList":null,"tvToken":null,"userBaseInfo":{"admitNumber":null,"brithday":null,
    // "campus":null,"childName":null,"cityId":0,"classId":"2015090211","classStr":null,
    // "college":"0","collegeName":"农业工程系","deleted":null,"depart":null,"divisionId":0,
    // "domainId":0,"domainName":"","dqztm":1,"endValue":null,"exp":0,"fullImage":"",
    // "grade":null,"headImage":"","inviteCode":null,"jg":null,"majorId":"0902","mobile":"",
    // "mobileList":[],"mobileSecret":1,"mzmc":"汉族","nickName":"","officeId":0,"officeName":"",
    // "parentName":null,"publishDomain":1,"publishOffice":1,"qq":"","realName":"陈玉奇","rfid":null,
    // "role":8,"roleLevel":0,"schoolId":194,"sex":1,"startValue":null,"summary":"","sznj":"2015",
    // "tel":"","toNextLevel":0,"userId":19411665,"vipLevel":0,"vipLevelName":null,"xqah":null,
    // "zymc":""},"userLoginInfo":{"mail":"201513043@jsahvc.edu.cn","parentUserName":null,"phone":"",
    // "pid":"32092419970416291X","regFrom":"信息中心","schoolCard":null,"securityPassword":null,
    // "sex":1,"tokenValue":null,"userId":19411665,"userName":"201513043","wxUserId":null},
    // "uuId":null,"uuidIv":null,"uuidKey":null}

    String msg, acceptSecure, uuId, uuidIv, uuidKey, appInfoList, campusTvUserInfo, divisionInfo, schoolInfo, token, tvAppInfoList, tvToken;
    int curSchoolId, infoId, isBindCampus, isConfirm, mail, msgState, secure, tel, time;
    boolean ownForumFlag;
    userBaseInfo userBaseInfo;
    userLoginInfo userLoginInfo;

    class userBaseInfo {
        String[] mobileList;
        String admitNumber, brithday, campus, childName, cityId, classId, classStr, college, collegeName, deleted, depart, divisionId, domainId, domainName, dqztm, endValue, exp, fullImage, grade, headImage, inviteCode, jg, majorId, mobile, mobileSecret, mzmc, nickName, officeId, officeName, parentName, publishDomain, publishOffice, qq, realName, rfid, role, roleLevel, schoolId, sex, startValue, summary, sznj, tel, toNextLevel, userId, vipLevel, vipLevelName, xqah, zymc;
    }

    class userLoginInfo {
        String mail, parentUserName, phone, pid, regFrom, schoolCard, securityPassword, sex, tokenValue, userId, userName, wxUserId;
    }
}
