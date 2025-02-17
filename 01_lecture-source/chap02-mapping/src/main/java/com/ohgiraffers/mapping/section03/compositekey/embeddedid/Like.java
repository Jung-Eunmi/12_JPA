package com.ohgiraffers.mapping.section03.compositekey.embeddedid;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tbl_like")
public class Like {

    @EmbeddedId
    private LikedCompositeKey likeInfo;

    public Like() {}

    public Like(LikedCompositeKey likeInfo) {
        this.likeInfo = likeInfo;
    }
}
