package cn.com.zhanss.wework.service.impl;

import cn.com.zhanss.common.exception.BusinessException;
import cn.com.zhanss.common.exception.ErrorCode;
import cn.com.zhanss.wework.enums.FileType;
import cn.com.zhanss.wework.service.MaterialService;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * 素材服务
 *
 * @author zhanss
 * @since 2022-08-22
 */
public class MaterialServiceImpl implements MaterialService {
    @Override
    public String uploadImage(MultipartFile file) {
        String name = file.getOriginalFilename();
        if (StringUtils.isEmpty(name) || name.lastIndexOf(".") == -1) {
            throw new BusinessException(ErrorCode.PARAM_ERROR);
        }
        String[] split = name.split(".");
        String fileSuffix = split[split.length - 1];
        if (!FileType.IMAGE.getFormats().contains(fileSuffix)) {
            throw new BusinessException(ErrorCode.FILE_FORMAT_ERROR);
        }
        // todo 待上传
        return System.currentTimeMillis()+"/"+name;
    }
}
