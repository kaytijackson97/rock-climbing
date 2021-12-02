package learn.rockClimbing.domain;

import java.util.ArrayList;

public class Result<T> {
    private final ArrayList<String> messages = new ArrayList<>();
    private ResultType type = ResultType.SUCCESS;
    private T payload;

    public ResultType getType() {
        return type;
    }

    public boolean isSuccess() {
        return type == ResultType.SUCCESS;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

    public ArrayList<String> getMessages() {
        return messages;
    }

    public void addMessage(String message, ResultType type){
        messages.add(message);
        this.type = type;
    }
}
