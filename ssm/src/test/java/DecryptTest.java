import cn.com.zhanss.entity.User;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @desc 测试类
 * @author zhanshuchan
 * @date 2021/2/18
 */
public class DecryptTest {
    /**
     * 数字签名，密钥算法
     */
    private static final String RSA_KEY_ALGORITHM = "RSA";

    @Test
    public void test1() {

        System.out.println("test2");

        Long longTime = Long.MAX_VALUE - System.currentTimeMillis();
        System.out.println("longTime-->"+ longTime);
    }

    @Test
    public void test2() throws Exception {
        String data = "你好";
        //加密
//        String encodeResult = encryptByPubKey(data, pubKey1);
        String encodeResult = "LolbkL3pfTLaOV+QH9E7EmgGzafySwldJrLvnCykQQtgZh23CHFtphQhwXJCxUddTmTooUvuLofkU8m+lKoGL8Lq78RVtzs4HFjDs1rZgsfkiTeNo1whULOMRtohEADpmj7azMtf5JraQk3905xMYfOi4iGOy65TobXByWoq0NHI8GG6TQVLcXsT7OLzQtWonRXQpLNsFxmFgYqaL+VGXxrIPwoMuFBD5hMZd0yelaCYtwb/ovL9dDCa0q1GcmQt3tqFEIq7+eBRVShrKPA+CGiyOtWgjGNkmOmc9Fl8F3oN3KA/KZZr93uZ3h+/9rtYiASc44w1tVNBaNIXDycNKA==";
        System.out.println("encodeResult2-->"+ encodeResult);
//        encodeResult = "ftJ+uz3n/z1DsxlkwxNgE+mL38H42/KCvN8T60gbbtPD+Rta1hKTuQPzUzO6Hzne97MgKs7FfdDxDck/v8cDT6gUVjA2tZ/M7euSD0L66opJ/IUeBtpAtvgVSD5qhlaQjvfKJc/zPMGNK2xCLFYqwmQBZXbNT7uA69Fflm512nZKW/piK2RKdYJhRyvQnA1ISxK097sp9WlEgDg250fM5tgwMjujdzr7ehK6gtVBUFldNSJS7ndtIf6aSBfaLktZgwHZ57ONewWq8GJe7WwQf1hwcDbCh7YMG8nsweEwhDfUz+u8rz9an+0lgrYMZFRHnmzjgmLwrR7B/32Qxqd79A==";

        //解密
        User[] users = new User[3];
        for (int i = 0; i < 3;) {
            User user = new User(i, "name"+i, i / 2 == 0 ? 2 : 1, "123456"+ i);
            try {
                System.out.println("data2 before");
                String decodeResult = decryptByPriKey(encodeResult, priKey);
                System.out.println("data2-->"+ decodeResult);
                user.setUserName("解密成功"+i);
            } catch (Exception e) {
                encodeResult = encryptByPubKey(data, pubKey);
                String decodeResult = decryptByPriKey(encodeResult, priKey);
                System.out.println("data22-->"+ decodeResult);
                user.setUserName("解密失败"+i);
//            throw e;
            }
            users[i] = user;
            i ++;
        }

        for (int i = 0; i < users.length; i ++) {
            System.out.println("user----->"+ users[i]);
        }
    }

    /**
     * 公钥加密
     *
     * @param data      加密前的字符串
     * @param publicKey 公钥
     * @return 加密后的字符串
     */
    public static String encryptByPubKey(String data, String publicKey) throws Exception {
        byte[] pubKey = Base64.decodeBase64(publicKey);
        byte[] enSign = encryptByPubKey(data.getBytes(), pubKey);
        return Base64.encodeBase64String(enSign);
    }

    /**
     * 公钥加密
     *
     * @param data   待加密数据
     * @param pubKey 公钥
     */
    public static byte[] encryptByPubKey(byte[] data, byte[] pubKey) throws Exception {
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(pubKey);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_ALGORITHM);
        PublicKey publicKey = keyFactory.generatePublic(x509KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(data);
    }

    /**
     * 私钥解密
     *
     * @param data   待解密的数据
     * @param priKey 私钥
     */
    public static byte[] decryptByPriKey(byte[] data, byte[] priKey) throws Exception {
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(priKey);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_KEY_ALGORITHM);
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(data);
    }

    /**
     * 私钥解密
     *
     * @param data       解密前的字符串
     * @param privateKey 私钥
     * @return 解密后的字符串
     */
    public static String decryptByPriKey(String data, String privateKey) throws Exception {
        byte[] priKey = Base64.decodeBase64(privateKey);
//        System.out.println("priKey.length>>>"+ priKey.length);
        byte[] decrypt = Base64.decodeBase64(data);
//        System.out.println("decrypt.length>>>"+ decrypt.length);
        byte[] design = decryptByPriKey(decrypt, priKey);
        return new String(design);
    }

    String pubKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAuOpKaVj+teqndw9xrzQb7eNnE3Ewy5PWqR8GSFW9M80yB3gLbIPtHEJNTrOs3i4XKClh7UQJw/6sjU5ukYEwlBx65dSBPvyyjuFzkDQ67bOzoTJmo+9z3B8ZNztqS28aEJUCt3ooven1XWxGcC9om1C/pHNGtmv/Cu9ghKk7epyy15rkNS+ehAWBWFWEzZ8bdf6vC3zB7yQ3N+cMX4mfpgyw4qPXVFl8doViD/UNsmjvFfQcXt6PCo3V08MRO3hH+8SjZmbgiFyNFXMvT8O10UpASkdjJEMXdIUENey2oTkpU7uhE5urBBl7Nx7lPU3M8WaAbtL/F0ODbM6I9EoFUwIDAQAB";
    String pubKey1 = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAwq2tVhMoHl/rOJecWnLz8FIlSKsnXN8hh3ism+B/hAStkcwySieyOOu0JNdN8Y2TQKJgb4LdKCj3y5EUQQVxi3Jxr706N1Y62SNAHSZ8/uVQDaljwmIYr4eye09KMWW17Ys5z80On4ab8qE80A2wkRU1QbIi4RC5XOm1eaf/Rd1y9be5VLwJr+erRVveLLw0qQkmsBQZjFNlNICs4wvIFyx/1Dd0g5PZuYL0UFmoOgyz4MY0Ux5efQH0u73nnmOqsRb18DWJpxw3rglbRM1XRqlTgIeCcF9yoNo6QPJMbQxZyO9UiV+6BrEUs5bOWRkJ2hZS5nBGsZd1ux+NJyRzXQIDAQAB";

    String priKey = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC46kppWP616qd3D3GvNBvt42cTcTDLk9apHwZIVb0zzTIHeAtsg+0cQk1Os6zeLhcoKWHtRAnD/qyNTm6RgTCUHHrl1IE+/LKO4XOQNDrts7OhMmaj73PcHxk3O2pLbxoQlQK3eii96fVdbEZwL2ibUL+kc0a2a/8K72CEqTt6nLLXmuQ1L56EBYFYVYTNnxt1/q8LfMHvJDc35wxfiZ+mDLDio9dUWXx2hWIP9Q2yaO8V9Bxe3o8KjdXTwxE7eEf7xKNmZuCIXI0Vcy9Pw7XRSkBKR2MkQxd0hQQ17LahOSlTu6ETm6sEGXs3HuU9TczxZoBu0v8XQ4Nszoj0SgVTAgMBAAECggEAVbuCjn9NcKh/xiqp6bI+NLgXBSOYnPpJLxYMQqNF2muLg3z7xu8PkueGkjOptUUnRV6SYYAHCxDbjZRMnTeJf1IJBN/WoI5mduo+OyCMJcMISHdmsSEG1YqxlTL94EV9jyyXGtwPYaXENfFKYx8H3+K7R5KOuWsuzzIWzMrtobWUWN/wXQUa+Jj7jEtAlX6pT7U+80LmB2QDiwMcu9HOdrCII1GFCH1Mis1lobFD3vVKSKndfuqk9zSj/2LGYTKx+s6i2hy8OfgsGSrqVCl40Ny5yu4Zbd22JKG3R/4ytV6papByHRNs7qc/Hl9+SWdBaxVPh1NEWB/iI2Ww7YqxGQKBgQDv+zzjTgi32nLlODajk6Fomb0VWZJ8P80sMQ98yXKo7AEtTreuRk3PXnIQkVOww4fzKDIsiJ/+MmCJK1yGMLO+D6v2rR6txnDdftj5xhaxq8SlGtKCIwDu2UCFzCQAO4R1OELPA6Q39QP/O7vpRJODwC9rOVgbXSVDW1bopb4P5QKBgQDFQhc8+ZuOemz01QluI+o2edQIjLPGh3rgRMDdkhMi54QQNnKbXjeUY/Y7GYUvMv7MgxWwxqo9sFqzgQ6G5advxCOLzNclXSkDLiv6WZyYfIZEKg6RjjPcZ/E+usEzILNF51aB5GDh6KNgDBSMZbylfvlFNdxjjQ7n3urfNR081wKBgQCXVWfgL6Vvy2IJCpnqLX7r54dFn2aw08sewxFxL9gOjkVvvOQ4Ej2fN3/W0s5j+eoRfJenXcEHG92koqOGO9AdWuSmeiWOkEU8aHGLkU9eW2N2eqOlTU2ZUYdO2J0bSG0PPpti0HkOkIQkwBaP057yxo8gntjg6KxG/aKgs2RXwQKBgQC9FuC906x5RQRDIiCbExSGSfymgXIn4mE4sM+M1mU/tVzUSc1NAGEY/0XfW1RfbWvw0cuuHJK+9dnMbu9lAqqhqgxFDARFTettoAoKP3KsfdobZ5F1mDJAmucYMd/XygXwAZXch/cTN3W9MXdVKoVOw8vhu4LbbzGVtgvCEeFIGwKBgCSSpd18L6gAu9axYv/2xX9jQOvSmjs7tsTQ5IeOxQ+Hkr43RhKc9+sd6ujyxhDlIFQ9vgUU9h3EEtX9wgL5BM2dQ5kd9ZPojGVSxPTUcuSnoi3V8CxQ1geL4SNnLgJLLpvVxHqBf+nHMclVsHkmI/qsFbzMRcboYA7LtCs4t/7I";

}
