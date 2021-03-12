package jw.hospital.oss.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConstantOssPropertiesUtils implements InitializingBean {

    @Value("${aliyun.oss.endpoint}")
    private String endpoint;

    @Value("${aliyun.oss.accessKeyId}")
    private String accessKeyId;

    @Value("${aliyun.oss.secret}")
    private String secret;

    @Value("${aliyun.oss.bucket}")
    private String bucket;

    public static String EDNPOINT;
    public static String ACCESS_KEY_ID;
    public static String SECRECT;
    public static String BUCKET;

    @Override
    public void afterPropertiesSet() throws Exception {
        EDNPOINT=endpoint;
        ACCESS_KEY_ID=accessKeyId;
        SECRECT=secret;
        BUCKET=bucket;
    }
}
