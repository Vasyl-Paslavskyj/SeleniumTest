package com.epam.models;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "Message")
public class MessageModel {

    private String from;

    private String subject;

    private String date;

    public MessageModel(String from, String subject, String date) {
        this.from = from;
        this.subject = subject;
        this.date = date;
    }

    public MessageModel(){

    }

    public String getFrom() {
        return from;
    }

    @XmlElement(name = "from")
    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    @XmlElement(name = "subject")
    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDate() {
        return date;
    }

    @XmlElement(name = "date")
    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MessageModel that = (MessageModel) o;

        if (from != null ? !from.equals(that.from) : that.from != null) return false;
        if (subject != null ? !subject.equals(that.subject) : that.subject != null) return false;
        return !(date != null ? !date.equals(that.date) : that.date != null);

    }

    @Override
    public int hashCode() {
        int result = from != null ? from.hashCode() : 0;
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MessageModel{" +
                "from='" + from + '\'' +
                ", subject='" + subject + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
