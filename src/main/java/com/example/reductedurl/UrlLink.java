package com.example.reductedurl;

import jakarta.persistence.*;

import java.util.Date;


@Entity
public class UrlLink {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String url; //Long Url

    @Column(nullable = false)
    private Long count;

    @Temporal(value = TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date lastEnter;

    public UrlLink() {
        count = 0L;
        lastEnter = new Date();
    }

    public UrlLink(String url) {
        this.url = url;
        count = 0L;
        lastEnter = new Date();

    }

    public static UrlLink of(UrlDTO urlDTO) {
        return new UrlLink(urlDTO.getUrl());
    }

    public UrlStatDTO toStatDTO() {
        var res = new UrlStatDTO();
        res.setUrl(url);
        res.setChortUrl(Long.toString(id));
        res.setRedirects(count);
        res.setLastEnter(lastEnter);
        return res;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Date getLastEnter() {
        return lastEnter;
    }

    public void setLastEnter(Date lastEnter) {
        this.lastEnter = lastEnter;
    }


}
