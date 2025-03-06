export interface Card {
    id: number;
    name: string;
    type: string;
    humanReadableCardType: string;
    frameType: string;
    description: string;
    race: string;
    archetype: string;
    ygoprodeckUrl: string;
    sets: CardSet[];
    imageLinks: CardImages;
    prices: CardPrices;
  }
  
  export interface MonsterCard extends Card {
    attack: number;
    defense: number;
    level: number;
    attribute: string;
  }
  
  export interface CardImages {
    imageUrl: string;
    imageUrlSmall: string;
    imageUrlCropped: string;
  }
  
  export interface CardPrices {
    cardMarketPrice: number;
    tcgPlayerPrice: number;
    ebayPrice: number;
    amazonPrice: number;
    coolStuffIncPrice: number;
  }
  
  export interface CardSet {
    name: string;
    code: string;
    rarity: string;
    rarityCode: string;
    price: number;
  }
  
  export interface FilteredCards{
    MonsterCards: Array<MonsterCard>;
    totalamount: number;
  }