package todolist.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import todolist.connection.DatabaseConnection;
import todolist.model.ModelStatus;

/**
 *
 * @author ghiyatsa_
 */
public class ServiceStatus {

    public List<ModelStatus> getAll() throws SQLException {
        Connection con = null;
        PreparedStatement p = null;
        ResultSet r = null;
        try {
            con = DatabaseConnection.getInstance().createConnection();
            p = con.prepareStatement("select * from status");
            r = p.executeQuery();
            List<ModelStatus> list = new ArrayList<>();
            while (r.next()) {
                int statusId = r.getInt("status_id");
                String statusName = r.getString("status_name");
                list.add(new ModelStatus(statusId, statusName));
            }
            return list;
        } finally {
            DatabaseConnection.getInstance().close(r, p, con);
        }
    }
}
