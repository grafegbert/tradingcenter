import { Component, OnInit } from '@angular/core';
import { PokemonCard } from '../models/models';
import { ApiClientService } from '../api-client.service';
import { EMPTY, catchError, debounceTime, map, tap } from 'rxjs';
import { SearchServiceService } from '../search-service.service';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrl: './homepage.component.scss'
})
export class HomepageComponent implements OnInit {
  allCards: PokemonCard[] = [];
  filteredCards: PokemonCard[] = [];
  loadingAnimationTrigger: boolean = true;
  noMatchingCardsFound: boolean = false;
  currentSearchValue: string = "";

  constructor(private service: ApiClientService, private searchService: SearchServiceService) {}

  ngOnInit(): void {
    this.service.getAllCards().pipe(
      catchError(() => {
        this.loadingAnimationTrigger = false;
        return EMPTY;
    })).subscribe(cards => {
      //this.loadingAnimationTrigger = false;
      this.allCards = cards;
      this.filteredCards = cards;
    });

      this.searchService.fetchSearchValue()
        .pipe(  
          tap((() => {
            this.loadingAnimationTrigger = true;
          })),
          debounceTime(400),
          map((searchValue) => {
            this.loadingAnimationTrigger = false;
            this.currentSearchValue = searchValue;
            return this.allCards.filter((card) => 
            card.name.toLowerCase().includes(searchValue.toLowerCase())
            );
          }),
        )
        .subscribe((changes) => {
          this.filteredCards = changes;
          if (this.filteredCards.length === 0) {
            this.noMatchingCardsFound = true;
          } else {
            this.noMatchingCardsFound = false;
          }
        });
  }
}