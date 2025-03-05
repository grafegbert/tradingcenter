package org.afbb.tradingcenter.objects.arrays;

import lombok.Getter;

@Getter
public class CardImages extends CardDataArray {
    private String imageUrl;
    private String imageUrlSmall;
    private String imageUrlCropped;

    public CardImages(String imageUrl, String imageUrlSmall, String imageUrlCropped) {
        super();
        this.imageUrl = imageUrl;
        this.imageUrlSmall = imageUrlSmall;
        this.imageUrlCropped = imageUrlCropped;
    }
}
