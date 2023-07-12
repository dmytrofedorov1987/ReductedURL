package com.example.reductedurl;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class UrlStatDTO extends UrllinkResultDTO {
    private Long redirects;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Europe/Kiev")
    private Date lastEnter;


    public Long getRedirects() {
        return redirects;
    }

    public void setRedirects(Long redirects) {
        this.redirects = redirects;
    }

    public Date getLastEnter() {
        return lastEnter;
    }

    public void setLastEnter(Date lastEnter) {
        this.lastEnter = lastEnter;
    }
}
