package com.hym.eshoplib.event;

import io.rong.imlib.model.Message;

public class ShowToastEvent {
    public Message message;

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
