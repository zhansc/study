package cn.com.zhanss.creationalpatterns;
import org.junit.Test;

/**
 * 工厂模式
 *
 * @author zhanss
 * @since 2022-04-27
 */
public class FactoryPattern {

    public static abstract class Pizza {
        public String name;
        public void bake(){}
        public void box(){}
        public void cut(){}
        public void prepare(){}
    }

    /**
     * 简单工厂模式
     * 问题：客户端严重依赖简单工厂类，若需要新增Pizza类型，需要做工厂类做修改
     */
    public class SimplePizzaFactory {
        public Pizza create(String orderType) {
            Pizza pizza = null;
            if ("milk".equals(orderType)) {
                pizza = new MilkPizza();
            } else if ("fruit".equals(orderType)) {
                pizza = new FruitPizza();
            }
            return pizza;
        }
    }
    public class MilkPizza extends Pizza{
        @Override
        public void bake() {
            System.out.println("baking milk pizza");
        }
    }
    public class FruitPizza extends Pizza{
        @Override
        public void bake() {
            System.out.println("baking fruit pizza");
        }
    }
    public class SuanLaPizza extends Pizza{
        @Override
        public void bake() {
            System.out.println("baking suanlaPizza pizza");
        }
    }

    /**
     * 工方法模式
     * 抽象一个工厂方法，让子类去完成对象实例化，通过不同的工厂会得到不同的对象
     * 问题：在客户端需要感知不同的工厂，且如果对应的工厂发生变化，客户端也需要一起变
     */
    public abstract class OrderPizza {
        abstract Pizza create(String orderType);
    }
    public class BJOrderPizza extends OrderPizza {
        @Override
        Pizza create(String orderType) {
            Pizza pizza = null;
            if ("milk".equals(orderType)) {
                pizza = new MilkPizza();
            } else if ("fruit".equals(orderType)) {
                pizza = new FruitPizza();
            }
            return pizza;
        }
    }
    public class SHOrderPizza extends OrderPizza {
        @Override
        Pizza create(String orderType) {
            Pizza pizza = null;
            if ("milk".equals(orderType)) {
                pizza = new MilkPizza();
            } else if ("fruit".equals(orderType)) {
                pizza = new FruitPizza();
            } else if ("suanla".equals(orderType)) {
                pizza = new SuanLaPizza();
            }
            return pizza;
        }
    }

    /**
     * 抽象工厂模式
     *
     */
    public interface AbsFactory {
        Pizza create(String orderType);
    }
    public class BJFactory implements AbsFactory {
        @Override
        public Pizza create(String orderType) {
            Pizza pizza = null;
            if ("milk".equals(orderType)) {
                pizza = new MilkPizza();
            } else if ("fruit".equals(orderType)) {
                pizza = new FruitPizza();
            }
            return pizza;
        }
    }
    public class SHFactory implements AbsFactory {
        @Override
        public Pizza create(String orderType) {
            Pizza pizza = null;
            if ("milk".equals(orderType)) {
                pizza = new MilkPizza();
            } else if ("suanla".equals(orderType)) {
                pizza = new SuanLaPizza();
            }
            return pizza;
        }
    }

    @Test
    public void main() {

    }

}
