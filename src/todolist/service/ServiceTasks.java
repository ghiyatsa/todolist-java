package todolist.service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import todolist.connection.DatabaseConnection;
import todolist.model.ModelTasks;
import todolist.model.ModelStatus;

/**
 *
 * @author ghiyatsa_
 */
public class ServiceTasks {

    public List<ModelTasks> getAll() throws SQLException {
        Connection con = null;
        PreparedStatement p = null;
        ResultSet r = null;
        try {
            con = DatabaseConnection.getInstance().createConnection();
            p = con.prepareStatement("select * from tasks join status using (status_id) order by status_id");
            r = p.executeQuery();
            List<ModelTasks> list = new ArrayList<>();
            while (r.next()) {
                int taskId = r.getInt("task_id");
                Date dueDate = r.getDate("due_date");
                String task = r.getString("task");
                String description = r.getString("description");
                int statusId = r.getInt("status_id");
                String statusName = r.getString("status_name");
                list.add(new ModelTasks(taskId, dueDate, task, description, new ModelStatus(statusId, statusName)));
            }
            return list;
        } finally {
            DatabaseConnection.getInstance().close(r, p, con);
        }
    }

    public List<ModelTasks> search(String search) throws SQLException {
        Connection con = null;
        PreparedStatement p = null;
        ResultSet r = null;
        try {
            con = DatabaseConnection.getInstance().createConnection();
            p = con.prepareStatement("select * from tasks join status using (status_id) where (status_name like ? or status_name like ? or description like ?) order by status_id");
            p.setString(1, "%" + search + "%");
            p.setString(2, "%" + search + "%");
            p.setString(3, "%" + search + "%");
            r = p.executeQuery();
            List<ModelTasks> list = new ArrayList<>();
            while (r.next()) {
                int taskId = r.getInt("task_id");
                Date dueDate = r.getDate("due_date");
                String task = r.getString("task");
                String description = r.getString("description");
                int statusId = r.getInt("status_id");
                String statusName = r.getString("status_name");
                list.add(new ModelTasks(taskId, dueDate, task, description, new ModelStatus(statusId, statusName)));
            }
            return list;
        } finally {
            DatabaseConnection.getInstance().close(r, p, con);
        }
    }

    public void create(ModelTasks data) throws SQLException, IOException {
        Connection con = null;
        PreparedStatement p = null;
        try {
            con = DatabaseConnection.getInstance().createConnection();
            p = con.prepareStatement("insert into tasks ( due_date, task, description, status_id) values (?,?,?,?)");
            p.setDate(1, data.getDueDate());
            p.setString(2, data.getTask());
            p.setString(3, data.getDescription());
            p.setInt(4, data.getStatus().getStatusId());
            p.execute();
        } finally {
            DatabaseConnection.getInstance().close(p, con);
        }
    }

    public void edit(ModelTasks data) throws SQLException, IOException {
        Connection con = null;
        PreparedStatement p = null;
        try {
            String sql = "update tasks set due_date=?, task=?, description=?, status_id=? where task_id=? limit 1";
            con = DatabaseConnection.getInstance().createConnection();
            p = con.prepareStatement(sql);
            p.setDate(1, data.getDueDate());
            p.setString(2, data.getTask());
            p.setString(3, data.getDescription());
            p.setInt(4, data.getStatus().getStatusId());
            p.setInt(5, data.getTaskId());
            p.execute();
        } finally {
            DatabaseConnection.getInstance().close(p, con);
        }
    }

    public void delete(int id) throws SQLException {
        Connection con = null;
        PreparedStatement p = null;
        try {
            con = DatabaseConnection.getInstance().createConnection();
            p = con.prepareStatement("delete from tasks where task_id=? limit 1");
            p.setInt(1, id);
            p.execute();
        } finally {
            DatabaseConnection.getInstance().close(p, con);
        }
    }

    public ServiceStatus getServiceStatus() {
        if (serviceStatus == null) {
            serviceStatus = new ServiceStatus();
        }
        return serviceStatus;
    }

    private ServiceStatus serviceStatus;
}
