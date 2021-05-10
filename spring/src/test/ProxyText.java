import cn.com.zhanss.annotation.proxy.CalculatorProxy;
import cn.com.zhanss.annotation.service.Calculator;
import cn.com.zhanss.annotation.service.MyCalculator;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @desc 动态代理
 * @author zhanshuchan
 * @date 2021/3/10
 */
public class ProxyText {

    @Test
    public void test() {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        Calculator calculator = CalculatorProxy.getProxy(new MyCalculator());
        System.out.println("calculator->>"+ calculator);
        Integer add = calculator.add(1, 3);
        System.out.println("add->>"+ add);
    }

    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        Calculator calculator = CalculatorProxy.getProxy(new MyCalculator());
        Integer add = calculator.sub(5, 3);
        System.out.println("add->>"+ add);
    }

    private final static String STORE_CODE_REGEX = "^[A-Za-z0-9]+$";
    @Test
    public void testRegex() {
        String phone = "1daa234A!";
        boolean validatePhone = this.validatePhone(phone, STORE_CODE_REGEX);
        System.out.println("validatePhone->>"+ validatePhone);

        System.out.println("regex--->"+ phone.matches(STORE_CODE_REGEX));

    }
    private boolean validatePhone(String phone, String regex) {
        Pattern PATTERN = Pattern.compile(regex);
        Matcher matcher = PATTERN.matcher(phone);
        return matcher.matches();
    }

}
