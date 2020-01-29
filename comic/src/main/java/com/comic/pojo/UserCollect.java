package com.comic.pojo;

import javax.persistence.*;

@Table(name = "user_collect")
public class UserCollect {
    @Id
    private Integer id;

    @Column(name = "comic_id")
    private Integer comicId;

    @Column(name = "comic_name")
    private String comicName;

    @Column(name = "comic_picture")
    private String comicPicture;

    @Column(name = "user_id")
    private Long userId;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return comic_id
     */
    public Integer getComicId() {
        return comicId;
    }

    /**
     * @param comicId
     */
    public void setComicId(Integer comicId) {
        this.comicId = comicId;
    }

    /**
     * @return comic_name
     */
    public String getComicName() {
        return comicName;
    }

    /**
     * @param comicName
     */
    public void setComicName(String comicName) {
        this.comicName = comicName;
    }

    /**
     * @return comic_picture
     */
    public String getComicPicture() {
        return comicPicture;
    }

    /**
     * @param comicPicture
     */
    public void setComicPicture(String comicPicture) {
        this.comicPicture = comicPicture;
    }

    /**
     * @return user_id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }
}