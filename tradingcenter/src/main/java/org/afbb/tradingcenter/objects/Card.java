package org.afbb.tradingcenter.objects;

import lombok.NoArgsConstructor;
import org.afbb.tradingcenter.objects.arrays.CardImages;
import org.afbb.tradingcenter.objects.arrays.CardPrices;
import org.afbb.tradingcenter.objects.arrays.CardSet;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

//TODO: overwrite NoArgsConstructor and refactor
@Getter
@Table(name = "cards")
@Entity
@NoArgsConstructor(force = true)
@Inheritance(strategy = InheritanceType.JOINED)
public class Card {
    @Id
    private final Integer id;
    private final String name;
    private final String type;
    @Column(name = "human_readable_card_type")
    private final String humanReadableCardType;
    @Column(name = "frame_type")
    private final String frameType;
    private final String description;
    private final String race;
    @JoinColumn(name = "archetype_id")
    private final String archetype;
    @Column(name = "ygoprodeck_url")
    private final String ygoprodeckUrl;
    @Transient
    private final List<CardSet> sets;
    @ManyToOne
    @JoinColumn(name = "image_card_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private final CardImages imageLinks;
    @OneToOne
    @JoinColumn(name = "id")
    private final CardPrices prices;

    public Card(       Integer id,
                       String name,
                       String type,
                       String humanReadableCardType,
                       String frameType,
                       String description,
                       String race,
                       String archetype,
                       String ygoprodeckUrl,
                       List<CardSet> sets,
                       CardImages imageLinks,
                       CardPrices prices) {

        this.id = id;
        this.name = name;
        this.type = type;
        this.humanReadableCardType = humanReadableCardType;
        this.frameType = frameType;
        this.description = description;
        this.race = race;
        this.archetype = archetype;
        this.ygoprodeckUrl = ygoprodeckUrl;
        this.sets = sets;
        this.imageLinks = imageLinks;
        this.prices = prices;
    }
}