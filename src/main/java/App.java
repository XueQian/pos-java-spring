import com.thoughtworks.iamcoach.pos.entity.Calculator;
import com.thoughtworks.iamcoach.pos.entity.CartItem;
import com.thoughtworks.iamcoach.pos.entity.Scanner;
import com.thoughtworks.iamcoach.pos.util.DataTransfer;
import com.thoughtworks.iamcoach.pos.util.FileProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class App {
    public static void main(String[] args) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("**************************LET US GO**************************");
        System.out.println("打印时间 " + dateFormat.format(new Date()));

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-config.xml");

        Scanner scanner = (Scanner) applicationContext.getBean("scanner");

        List<CartItem> cartItems = scanner.getCartItems(FileProcessor.readFile("cart.txt"));
        Set<String> cartCategories = scanner.getCartCategories(cartItems);

        for (String cartCategory : cartCategories) {
            System.out.println(cartCategory);
            for (CartItem cartItem : cartItems) {

                if (cartCategory.equals(cartItem.getItem().getCategory())) {
                    System.out.println(cartItem.getItem().getName() +
                            "  数量：" + cartItem.getCount() + cartItem.getItem().getUnit() +
                            "  单价：" + cartItem.getItem().getPrice() + "元" +
                            "  小计:" + DataTransfer.transfer(Calculator.getSubtotal(cartItem)) + "元");
                }
            }
        }

        System.out.println("总计金额 优惠前：" + Calculator.getTotalMoney(cartItems) +
                "元   优惠后：" + Calculator.getTotalMoneyAfterPromoting(cartItems) +
                "元   优惠差价:" + Calculator.getTotalSavedMoney(cartItems) + "元");
    }
}
