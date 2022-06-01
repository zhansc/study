package cn.com.zhanss.wework.javaapi.annotation;
import org.apache.dubbo.common.utils.StringUtils;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;
/**
 * 自定义验证类型的注解
 * 这个自定义注解逻辑处理类由于实现了ConstraintValidator接口，
 * 所以默认被spring管理成bean,所以可以在这个逻辑处理类里面用@Autowiredu或者@Resources注入别的服务，
 * 而且不用在类上面用@Compent注解成spring的bean.
 *
 * @author zhanss
 * @since 2022-05-21
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {SameTenant.StringChecker.class, SameTenant.LongChecker.class})
@Documented
public @interface SameTenant {

    int max() default 0;

    String message() default "用户不存在或者不属于当前组织";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * 校验逻辑
     * 两个泛型：第一个是自定义注解类；第二个是支持的数据类型（可以用Object支持所有的类型）
     */
    class StringChecker implements ConstraintValidator<SameTenant, String> {

        private int max;

        @Override
        public void initialize(SameTenant arg0) {

            System.out.println("validator init1");
            this.max = arg0.max();
        }

        @Override
        public boolean isValid(String uid, ConstraintValidatorContext context) {
            return !StringUtils.isBlank(uid) || max < uid.length();
        }
    }

    /**
     * 校验逻辑
     * 支持Long类型的数据
     */
    class LongChecker implements ConstraintValidator<SameTenant, Long> {

        @Override
        public void initialize(SameTenant arg0) {
            System.out.println("validator init2");
        }

        @Override
        public boolean isValid(Long uid, ConstraintValidatorContext context) {
            return null == uid;
        }
    }
}
