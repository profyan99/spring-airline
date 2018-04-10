package net.thumbtack.airline.dto.request;

import net.thumbtack.airline.model.UserRole;

public class OrderGetParamsRequestDto extends FlightGetParamsRequestDto {
    private int clientId;
    private int userId;

    public OrderGetParamsRequestDto(String fromTown, String toTown, String flightName, String planeName, String fromDate,
                                    String toDate, UserRole userType, int clientId, int userId) {
        super(fromTown, toTown, flightName, planeName, fromDate, toDate, userType);
        this.clientId = clientId;
        this.userId = userId;
    }

    public OrderGetParamsRequestDto() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

}
