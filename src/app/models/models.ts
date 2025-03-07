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
  imageLinks: string;
  prices: number;
}

export interface MonsterCard extends Card {
  attack: number;
  defense: number;
  level: number;
  attribute: string;
}

export interface FilteredCards {
  MonsterCards: Array<MonsterCard>;
  totalamount: number;
}
