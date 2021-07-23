package cn.com.zhanss.wework.javaapi.lambda;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Created with mass User: lichangning Date: 2016-09-27 DESC: StringCompressUtil
 */
public class GzipUtil {

    // 压缩
    public static String compress(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        String result = "";
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            GZIPOutputStream gzip = new GZIPOutputStream(out);
            gzip.write(str.getBytes());
            gzip.close();
            result = out.toString("ISO-8859-1");
        } catch (Exception e) {
        }

        return result;

    }

    // 解压缩
    public static String uncompress(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        String result = "";
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ByteArrayInputStream in = new ByteArrayInputStream(str.getBytes("ISO-8859-1"));
            GZIPInputStream gunzip = new GZIPInputStream(in);
            byte[] buffer = new byte[256];
            int n;
            while ((n = gunzip.read(buffer)) >= 0) {
                out.write(buffer, 0, n);
            }
            result = out.toString();
        } catch (Exception e) {

        }

        return result;
    }

}
