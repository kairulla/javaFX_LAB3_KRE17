package lab3_kre17;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import java.sql.SQLException;
import java.sql.ResultSet;

public class ElectricStoveRowMapper  implements RowMapper {
    
    @Override
    public Object mapRow(ResultSet resultSet, int line) throws SQLException {
        ElectricStoveResultSetExtractor extractor = new ElectricStoveResultSetExtractor();
        return extractor.extractData(resultSet);
    }

    class ElectricStoveResultSetExtractor implements ResultSetExtractor {

        @Override
        public Object extractData(ResultSet resultSet) throws SQLException {
            ElectricStove electricStove = new ElectricStove();
            electricStove.setId(resultSet.getInt("id"));
            electricStove.setModel(resultSet.getString("model"));
            electricStove.setPrice(resultSet.getDouble("price"));
            electricStove.setColor(resultSet.getString("color"));
            return electricStove;
        }
    }
    
}
