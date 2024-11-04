package com.hotel.back.utils;

import com.aliyun.tea.TeaException;

import java.util.Random;
import java.util.UUID;

public class SMS {

    public com.aliyun.dysmsapi20170525.Client createClient() throws Exception {
        // 工程代码泄露可能会导致 AccessKey 泄露，并威胁账号下所有资源的安全性。以下代码示例仅供参考。
        // 建议使用更安全的 STS 方式，更多鉴权访问方式请参见：https://help.aliyun.com/document_detail/378657.html。
        com.aliyun.teaopenapi.models.Config config = new com.aliyun.teaopenapi.models.Config()
                // 必填，请确保代码运行环境设置了环境变量 ALIBABA_CLOUD_ACCESS_KEY_ID。
                .setAccessKeyId(System.getenv("ALIBABA_CLOUD_ACCESS_KEY_ID"))
                // 必填，请确保代码运行环境设置了环境变量 ALIBABA_CLOUD_ACCESS_KEY_SECRET。
                .setAccessKeySecret(System.getenv("ALIBABA_CLOUD_ACCESS_KEY_SECRET"));
        // Endpoint 请参考 https://api.aliyun.com/product/Dysmsapi
        config.endpoint = "dysmsapi.aliyuncs.com";
        return new com.aliyun.dysmsapi20170525.Client(config);
    }

    public String sendCode(String phone, String condition) throws Exception {
        com.aliyun.dysmsapi20170525.Client client = createClient();
        System.out.println(phone);

        Random random = new Random();
        int number = random.nextInt(1000000); // 生成0到999999之间的随机数
        String code = String.format("%06d", number); // 格式化为6位，不足的前面补零
        com.aliyun.dysmsapi20170525.models.SendSmsRequest sendSmsRequest;
        if(condition.equals("register")){
            sendSmsRequest = new com.aliyun.dysmsapi20170525.models.SendSmsRequest()
                    .setSignName("前进的小卒")
                    .setTemplateCode("SMS_474840621")
                    .setPhoneNumbers(phone)
                    .setTemplateParam("{\"code\":\"" + code + "\"}");
        }
        else if(condition.equals("forget")) {
            sendSmsRequest = new com.aliyun.dysmsapi20170525.models.SendSmsRequest()
                    .setSignName("前进的小卒")
                    .setTemplateCode("SMS_475015946")
                    .setPhoneNumbers(phone)
                    .setTemplateParam("{\"code\":\"" + code + "\"}");
        }
        else{
            sendSmsRequest = new com.aliyun.dysmsapi20170525.models.SendSmsRequest()
                    .setSignName("前进的小卒")
                    .setTemplateCode("SMS_474806104")
                    .setPhoneNumbers(phone)
                    .setTemplateParam("{\"code\":\"" + code + "\"}");
        }
        com.aliyun.teautil.models.RuntimeOptions runtime = new com.aliyun.teautil.models.RuntimeOptions();
        System.out.println("{\"code\":\"" + code + "\"}");
        String result = null;
        try {
            // 复制代码运行请自行打印 API 的返回值
            result = client.sendSmsWithOptions(sendSmsRequest, runtime).getBody().getCode();
        } catch (TeaException error) {
            // 此处仅做打印展示，请谨慎对待异常处理，在工程项目中切勿直接忽略异常。
            // 错误 message
            System.out.println(error.getMessage());
            // 诊断地址
            System.out.println(error.getData().get("Recommend"));
            com.aliyun.teautil.Common.assertAsString(error.message);
        } catch (Exception _error) {
            TeaException error = new TeaException(_error.getMessage(), _error);
            // 此处仅做打印展示，请谨慎对待异常处理，在工程项目中切勿直接忽略异常。
            // 错误 message
            System.out.println(error.getMessage());
            // 诊断地址
            System.out.println(error.getData().get("Recommend"));
            com.aliyun.teautil.Common.assertAsString(error.message);
        }
        System.out.println(result);
        if(result.equals("OK")){
            RedisUtil redisUtil = new RedisUtil();
            redisUtil.setValue(phone, 300, code);
        }
        return result;
    }
}