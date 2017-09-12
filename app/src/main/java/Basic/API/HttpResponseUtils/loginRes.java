package Basic.API.HttpResponseUtils;

/**
 * Created by QiuChenly on 2017/9/10.
 */

public class loginRes {

    public String acceptSecure, campusTvUserInfo, mobile, msg,  tvAppInfoList, tvToken,   uuId, uuidIv, uuidKey;
    public divisionInfo divisionInfo;
    public int curSchoolId, infoId, isBindCampus, isConfirm, mail, msgState, secure, tel, time;
    public boolean ownForumFlag;
    public String[] msgReadTags, msgPublishTags, token;
    public schoolInfo schoolInfo;
    public userBaseInfo userBaseInfo;
    public userLoginInfo userLoginInfo;

    class divisionInfo {
        float latitude, longitude;
    }

    class schoolInfo {
        int schoolId;
        String schoolName, shortName;
    }

    public class userBaseInfo {
        public  String admitNumber, brithday, campus, childName, cityId, classId, classStr, college, collegeName, deleted, depart, divisionId, domainId, domainName, dqztm, endValue, exp, fullImage, grade, headImage, inviteCode, jg, majorId, mobile, mobileSecret, mzmc, nickName, officeId, officeName, parentName, publishDomain, publishOffice, qq, realName, rfid, role, roleLevel, schoolId, sex, startValue, summary, sznj, tel, toNextLevel, userId, vipLevel, vipLevelName, xqah, zymc;
        public  String[] mobileList;
    }

    public class userLoginInfo{
        public String mail,parentUserName,phone,pid,regFrom,schoolCard,securityPassword,sex,tokenValue,userId,userName,wxUserId;

    }

}
