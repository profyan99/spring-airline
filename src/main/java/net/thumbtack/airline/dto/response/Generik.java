package net.thumbtack.airline.dto.response;

public class Generik<T> {
    private T response;
    public Generik(T response) {
        this.response = response;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }
}
