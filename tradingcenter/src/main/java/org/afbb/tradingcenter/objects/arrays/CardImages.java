package org.afbb.tradingcenter.objects.arrays;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Table(name = "card_images")
@Entity
public class CardImages extends CardDataArray {
    @Id
    private final int id;
    @Column(name = "image_url")
    private String imageUrl;
    @Column(name = "image_url_small")
    private String imageUrlSmall;
    @Column(name = "image_url_cropped")
    private String imageUrlCropped;

    public CardImages(int id, String imageUrl, String imageUrlSmall, String imageUrlCropped) {
        super();
        this.id = id;
        this.imageUrl = imageUrl;
        this.imageUrlSmall = imageUrlSmall;
        this.imageUrlCropped = imageUrlCropped;
    }
}
