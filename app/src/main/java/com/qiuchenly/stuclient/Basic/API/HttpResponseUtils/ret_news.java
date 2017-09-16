package com.qiuchenly.stuclient.Basic.API.HttpResponseUtils;

/**
 * Created by QiuChen on 2017/9/16.
 */

public class ret_news {
    public String acceptSecure,curSchoolId,limit,msg,msgState,offset,secure,time,totalPages;
    public in[] newsList;
    public class in{
        public long createTime,updateTime;
        public int isRead,newsId,newsType,parentId,parm,schoolId,seq,sftop,siteId,userId;
        public String content,defaultImageUrl,from,name,realName,source,summary,title;
        public String[] imgNameList;
    }
}
