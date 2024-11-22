package todolist.model;

/**
 *
 * @author ghiyatsa_
 */
public class ModelStatus {

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public ModelStatus(int statusId, String statusName) {
        this.statusId = statusId;
        this.statusName = statusName;
    }

    public ModelStatus() {
    }

    private int statusId;
    private String statusName;

    @Override
    public String toString() {
        return statusName;
    }
}
