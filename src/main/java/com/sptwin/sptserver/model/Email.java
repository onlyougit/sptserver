package com.sptwin.sptserver.model;

import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class Email {
    private String title;

    private String content;

    private List<String> address;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getAddress() {
        return address;
    }

    public void setAddress(List<String> address) {
        this.address = address;
    }
}
