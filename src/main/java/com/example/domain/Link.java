package com.example.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "links")
public class Link {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "link")
    private String link;

    @Column (name = "short_link")
    private String shortLink;

    @Column(name = "counts")
    private Long count;

    public Link() {}

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getShortLink() {
        return shortLink;
    }

    public void setShortLink(String shortLink) {
        this.shortLink = shortLink;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public void increaseCount() {
        setCount(this.count + 1L);
    }

    @Override
    public String toString() {
        return "Link{" +
                "id=" + id +
                ", link='" + link + '\'' +
                ", shortLink='" + shortLink + '\'' +
                ", count=" + count +
                '}';
    }
}
