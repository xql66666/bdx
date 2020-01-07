package com.bdx.manager.util;


import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import constants.Qiniu;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.UUID;

/**
 * @author: 磊大大
 * @date: 2019/11/23 19:43
 */
@Component
public class QiNiuUtil {

    private static String accessKey;
    private static String secretKey;
    private static String bucket;

    @Value("${qiniuyun.accessKey}")
    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    @Value("${qiniuyun.secretKey}")
    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    @Value("${qiniuyun.bucket}")
    public void setBucket(String bucket) {
        this.bucket = bucket;
    }


    public static String qiniuUploadHeadImg(InputStream inputStream) {
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.region0());
        UploadManager uploadManager = new UploadManager(cfg);


        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = "bdxinfoimg" + UUID.randomUUID();

            //InputStream inputStream = multipartFile.getInputStream();
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);
            try {
                Response response = uploadManager.put(inputStream, key, upToken, null, null);
                //解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
                String infoImg = Qiniu.QINIU_IMGURL + putRet.key;
                System.out.println(putRet.key);
                System.out.println(putRet.hash);
                return infoImg;
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore啊啊
                }
                return null;
            }

    }
}
