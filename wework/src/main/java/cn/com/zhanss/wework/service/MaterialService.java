package cn.com.zhanss.wework.service;

import org.springframework.web.multipart.MultipartFile;


/**
 * 素材服务
 *
 * @author zhanss
 * @since 2022-08-22
 */
public interface MaterialService {

    String uploadImage(MultipartFile file);
}