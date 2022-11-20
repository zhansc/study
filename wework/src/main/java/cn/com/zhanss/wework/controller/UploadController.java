package cn.com.zhanss.wework.controller;

import cn.com.zhanss.wework.service.MaterialService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

/**
 * 文件上传
 *
 * @author zhanss
 * @since 2022-08-22
 */
@Validated
@RestController
@RequestMapping("/upload")
public class UploadController {

    @Resource
    private MaterialService materialService;

    @RequestMapping("/image")
    public String image(@NotNull MultipartFile file) {
        return materialService.uploadImage(file);
    }
}
