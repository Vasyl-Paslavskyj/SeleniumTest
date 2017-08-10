package com.epam.models;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "Messages")
public class Messages {

    private List<MessageModel> messageList;

    public List<MessageModel> getMessageList() {
        return messageList;
    }

    @XmlElement(name = "Message")
    public void setMessageList(List<MessageModel> messageList) {
        this.messageList = messageList;
    }
}
