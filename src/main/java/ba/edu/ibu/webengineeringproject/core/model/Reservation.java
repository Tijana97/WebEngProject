package ba.edu.ibu.webengineeringproject.core.model;

import java.util.Date;

public class Reservation {

    private final int id;

    private final int roomId;

    private final int userId;

    private Date dateFrom;

    private Date dateTo;

    public Reservation(int id, int roomId, int userId, Date dateFrom, Date dateTo) {
        this.id = id;
        this.roomId = roomId;
        this.userId = userId;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public int getId() {
        return id;
    }

    public int getRoomId() {
        return roomId;
    }

    public int getUserId() {
        return userId;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }
}
