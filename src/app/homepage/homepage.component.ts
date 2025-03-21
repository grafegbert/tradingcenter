import { Component, OnInit } from '@angular/core';
import { MonsterCard } from '../models/models';
import { ApiClientService } from '../api-client.service';
import { EMPTY, catchError, debounceTime, distinctUntilChanged, map, tap } from 'rxjs';
import { SearchServiceService } from '../search-service.service';
import { PageEvent } from '@angular/material/paginator';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrl: './homepage.component.scss'
})
export class HomepageComponent implements OnInit {
  allCards: MonsterCard[] = [];
  //  allCards: BehaviorSubject<MonsterCard[]>[] = new BehaviorSubject<MonsterCard[]>([]);
  loadingAnimationTrigger: boolean = true;
  noMatchingCardsFound: boolean = false;
  currentSearchValue: string = "";
  pagesize: number = 50;
  pageindex: number = 0;
  numberOfAllCards = 0;

  constructor(private service: ApiClientService, private searchService: SearchServiceService) {}

  ngOnInit(): void {
    this.searchService.fetchSearchValue()
      .pipe(
        debounceTime(400),
        distinctUntilChanged()
      )
      .subscribe(searchValue => {
        this.currentSearchValue = searchValue;
        this.pageindex = 0;
        this.loadCards();
      });
    this.service.getAllCards(1,1,"");
    this.loadCards();
  }

  loadCards(){
    this.loadingAnimationTrigger = true;

    this.service.getAllCards(this.pagesize, this.pageindex, this.currentSearchValue)
      .pipe(
        tap(() => this.loadingAnimationTrigger = true),
        catchError(() => {
          this.loadingAnimationTrigger = false;
          return EMPTY;
        })
      )
      .subscribe(cards => {
        console.log(cards);
        this.allCards = cards.MonsterCards;
        this.numberOfAllCards = cards.totalamount;
        this.loadingAnimationTrigger = false;
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