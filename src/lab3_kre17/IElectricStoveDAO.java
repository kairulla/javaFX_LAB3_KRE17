package lab3_kre17;

import java.util.List;
import javax.sql.DataSource;

interface IElectricStoveDAO {
    void setDataSource(DataSource dataSource);    
    void insert(ElectricStove electricStove);    
    void append(String model, double price, String color);    
    void deleteByModel(String model);    
    void deleteByPrice(String price);    
    void deleteByColor(String color);    
    void delete(final String model, final String color);    
    void deleteAll();    
    void updateModel(String oldModel, String newModel);    
    void updatePrice(String oldPrice, String newPrice);
    void updateColor(String oldColor, String newColor);    
    List<ElectricStove> findByModel(String model);    
    ElectricStove findByPrice(double price);    
    List<ElectricStove> findByColor(String color);    
    List<ElectricStove> select(String model, double price, String color);
    List<ElectricStove> selectAll();
}
