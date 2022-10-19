package lab3_kre17;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.TransactionStatus;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import java.util.List;

import javax.sql.DataSource;

public class ElectricStoveSenderDAO implements IElectricStoveDAO {
    private DataSource dataSource;    
    
    public void setDataSource(DataSource dataSource){
        this.dataSource = dataSource;
    }    
    
    public void insert(ElectricStove electricStove){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update("INSERT INTO es_model (model, price, color) VALUES(?,?,?)",
                new Object[]{electricStove.getModel(), electricStove.getPrice(), electricStove.getColor()});
    }    
    
    public void append(String model, double price, String color){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update("INSERT INTO es_model (model, price, color) VALUES(?,?,?)",
                new Object[]{model, price, color});
    }    
    
    public void deleteByModel(String model){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update("DELETE FROM es_model WHERE model LIKE ?",
                new Object[]{'%' + model + '%'});
    }
    
    public void deleteByPrice(String price){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update("DELETE FROM es_model WHERE price LIKE ?",
                new Object[]{'%' + price + '%'});
    }
    
    public void deleteByColor(String color){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update("DELETE FROM es_model WHERE color LIKE ?",
                new Object[]{'%' + color + '%'});
    }
    
    public void delete(final String model, final String color){
        TransactionTemplate transactionTemplate = new TransactionTemplate(new DataSourceTransactionManager(dataSource));
        transactionTemplate.execute(new TransactionCallback() {
            @Override
            public Object doInTransaction(TransactionStatus transactionStatus) {
                try {
                    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
                    jdbcTemplate.update("DELETE FROM es_modelooo WHERE model = ? AND color = ?",
                            new Object[]{model, color});
//                } catch(RuntimeException e) {
//                    transactionStatus.setRollbackOnly();
//                    throw e;
                } catch(Exception e) {
                    transactionStatus.setRollbackOnly();
                    e.printStackTrace();
//                    throw new RuntimeException(e);
                }
                return null;
            }
        });
    }    
    
    public void deleteAll(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update("DELETE FROM es_model");
    }    
    
    public void updateModel(String oldModel, String newModel){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update("UPDATE es_model SET model = ? WHERE model = ?",
                new Object[]{newModel, oldModel});
    }
    
    public void updatePrice(String oldPrice, String newPrice){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update("UPDATE es_model SET price = ? WHERE price = ?",
                new Object[]{newPrice, oldPrice});
    }
    
    public void updateColor(String oldColor, String newColor){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update("UPDATE es_model SET color = ? WHERE color = ?",
                new Object[]{newColor, oldColor});
    }
    
    public List<ElectricStove> findByModel(String model){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "SELECT * FROM es_model WHERE model LIKE ?";
        List<ElectricStove> electricStoves = jdbcTemplate.query(sql, new Object[]{'%' + model + '%' }, new ElectricStoveRowMapper());
        return electricStoves;
    }
    
    public ElectricStove findByPrice(double price){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<ElectricStove> electricStove = jdbcTemplate.query("SELECT * FROM es_model WHERE price = ?",
                new Object[]{ price }, new ElectricStoveRowMapper());
        return electricStove.size() > 0 ? electricStove.get(0) : null;
    }
    
    public List<ElectricStove> findByColor(String color){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "SELECT * FROM es_model WHERE color LIKE ?";
        List<ElectricStove> colors = jdbcTemplate.query(sql, new Object[]{ '%' + color + '%' }, new ElectricStoveRowMapper());
        return colors;
    }      
    
    public List<ElectricStove> select(String model, double price, String color){ // Получение записей с заданной моделью, ценой и цвето
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate.query("SELECT * FROM es_model WHERE model = ? AND price = ? AND color = ?",
                new Object[]{model, price, color}, new ElectricStoveRowMapper());
    }    
    
    public List<ElectricStove> selectAll(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);        
        return jdbcTemplate.query("SELECT * FROM es_model", new ElectricStoveRowMapper());
    }
    
}
