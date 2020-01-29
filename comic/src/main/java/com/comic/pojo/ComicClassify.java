package com.comic.pojo;

import javax.persistence.*;

@Table(name = "comic_classify")
public class ComicClassify {
    @Id
    private Integer id;

    @Column(name = "classify_id")
    private Integer classifyId;

    @Column(name = "comic_id")
    private Integer comicId;

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
     * @return classify_id
     */
    public Integer getClassifyId() {
        return classifyId;
    }

    /**
     * @param classifyId
     */
    public void setClassifyId(Integer classifyId) {
        this.classifyId = classifyId;
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
}