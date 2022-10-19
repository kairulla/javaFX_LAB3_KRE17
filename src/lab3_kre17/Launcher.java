package lab3_kre17;

import java.util.Arrays;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.ApplicationContext;
import java.util.List;
import org.springframework.test.context.ContextConfiguration;

public class Launcher {
    
    public static void main(String[] args) {
        try {
            
            ApplicationContext applicationContext = new ClassPathXmlApplicationContext("lab3_kre17/electricStoveContext.xml");
            
            ElectricStoveSenderDAO electricStoveSender = (ElectricStoveSenderDAO) applicationContext.getBean("electricStoveSender");
            
            electricStoveSender.deleteAll(); // работает
            
            ElectricStove electricStove = new ElectricStove("lg", 128000.00, "white"); // работает
            electricStoveSender.insert(electricStove); // работает
            
            electricStoveSender.insert(new ElectricStove("samsung", 127800.00, "grey")); // работает
            electricStoveSender.insert(new ElectricStove("bosch", 131900.00, "black")); // работает
            
            List<ElectricStove> electricStoveByModel = electricStoveSender.findByModel("lg"); // работает
            System.out.println((electricStoveByModel != null) ? electricStoveByModel : "Нет данных"); // работает

            ElectricStove electricStoveByPrice = electricStoveSender.findByPrice(131900); // работает
            System.out.println(electricStoveByPrice.toString()); // работает
            
            List<ElectricStove> electricStoveByColor = electricStoveSender.findByColor("white"); // работает
            System.out.println((electricStoveByColor != null) ? electricStoveByColor : "Нет данных"); // работает
            
            electricStoveSender.deleteByModel("samsung"); // работает
            electricStoveSender.deleteByPrice("127800"); // работает
            electricStoveSender.deleteByColor("black"); // работает
            electricStoveSender.delete("samsung", "grey"); // работает
            
            electricStoveSender.append("astra", 99000, "yellow"); // работает
            electricStoveSender.append("nagasaki", 77000, "blue"); // работает
            electricStoveSender.append("everest", 66000, "red"); // работает
            electricStoveSender.append("rebel", 54000, "purple"); // работает
            electricStoveSender.append("refresh", 98000, "brown"); // работает
            
            electricStoveSender.updateModel("refresh", "refreshDelux"); // работает
            electricStoveSender.updatePrice("66000", "99999"); // работает
            electricStoveSender.updateColor("yellow", "orange"); // работает
            
            System.out.println("Данные в таблице БД:*************************************"); // работает
            
            List<ElectricStove> list = electricStoveSender.selectAll(); // работает
            for (ElectricStove myElectricStove : list) { // работает
                System.out.println(myElectricStove.getModel() + " " + myElectricStove.getPrice() + " " + myElectricStove.getColor());
            } // работает
            
            System.out.println("Вывод записей с моделью nagasaki, ценой 77000 и цветом blue:"); // работает
            for (ElectricStove myElectricStove : list) { // работает
                System.out.println(myElectricStove.getModel() + " " + myElectricStove.getPrice() + " " + myElectricStove.getColor());
               
            list = electricStoveSender.select("nagasaki", 77000, "blue"); // работает
         } // работает
            
            
            
            
        } catch(Exception e) {
            e.printStackTrace();
            System.err.println("+++++Error!+++++");
        }
    }
    
}
