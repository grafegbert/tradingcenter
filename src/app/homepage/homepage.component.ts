import { Component, OnInit } from '@angular/core';
import { MonsterCard } from '../models/models';
import { ApiClientService } from '../api-client.service';
import { EMPTY, catchError, debounceTime, map, tap } from 'rxjs';
import { SearchServiceService } from '../search-service.service';
import { PageEvent } from '@angular/material/paginator';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrl: './homepage.component.scss'
})
export class HomepageComponent implements OnInit {
  allCards: MonsterCard[] = [];
  loadingAnimationTrigger: boolean = true;
  noMatchingCardsFound: boolean = false;
  currentSearchValue: string = "";
  pagesize: number = 25;
  pageindex: number = 0;
  numberOfAllCards = 0;

  constructor(private service: ApiClientService, private searchService: SearchServiceService) {}

  ngOnInit(): void {
    this.searchService.fetchSearchValue().subscribe(searchValue => {
      this.currentSearchValue = searchValue;
      this.loadCards(); 
    });

    this.loadCards();
  }

  loadCards(){
    this.loadingAnimationTrigger = true;

    this.service.getAllCards(this.pagesize, this.pageindex, this.currentSearchValue)
      .pipe(
        tap(() => this.loadingAnimationTrigger = true),  // Animation erneut starten
        catchError(() => {
          this.loadingAnimationTrigger = false;  // Animation beenden bei Fehler
          return EMPTY;
        })
      )
      .subscribe(cards => {
        this.allCards = cards.MonsterCards;
        this.numberOfAllCards = cards.totalamount;
        this.loadingAnimationTrigger = false; // Ladeanimation beenden, wenn Daten da sind
        this.noMatchingCardsFound = this.allCards.length === 0;
      });
  }
  
  onPageChange(event: PageEvent) {
    console.log(this.pagesize);
    this.pageindex = event.pageIndex;
    this.pagesize = event.pageSize;
    this.loadCards();
  }
}