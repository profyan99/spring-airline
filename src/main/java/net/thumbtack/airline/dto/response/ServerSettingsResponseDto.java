package net.thumbtack.airline.dto.response;

public class ServerSettingsResponseDto {
    private int maxNameLength;
    private int minPasswordLength;

    public ServerSettingsResponseDto(int maxNameLength, int minPasswordLength) {
        this.maxNameLength = maxNameLength;
        this.minPasswordLength = minPasswordLength;
    }

    public ServerSettingsResponseDto() {

    }

    public int getMaxNameLength() {
        return maxNameLength;
    }

    public void setMaxNameLength(int maxNameLength) {
        this.maxNameLength = maxNameLength;
    }

    public int getMinPasswordLength() {
        return minPasswordLength;
    }

    public void setMinPasswordLength(int minPasswordLength) {
        this.minPasswordLength = minPasswordLength;
    }
}
