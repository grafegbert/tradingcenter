import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders, HttpParams, HttpResponse } from '@angular/common/http';
import { FilteredCards, MonsterCard } from './models/models';
import { DUMMY_MONSTER_CARDS } from './models/DUMMY_MONSTER_CARDS';

@Injectable({
  providedIn: 'root'
})
export class ApiClientService { 
  cards$: BehaviorSubject<MonsterCard[]> = new BehaviorSubject<MonsterCard[]>([]);
  adress: String = "http://localhost:8080/"

  constructor(private http: HttpClient) {}

  public post(json: string, operation: string): void {
    let context = "";

    if (operation == "persist") {
      context = "users/persist/";
    
    } else if (operation == "login") {
      context = "users/login/";

    } else if (operation == "registration") {
      context = "users/registration/";
    }

    this.http.post<HttpResponse<string>>(this.adress + context, json, { headers: this.getDefaultHeaders() }).subscribe((result) => {

    });
  }

  public get(pokemonName: string): Observable<HttpResponse<MonsterCard>> {
    return this.http.post<HttpResponse<MonsterCard>>(
      this.adress + "cards/", pokemonName,  { headers: this.getDefaultHeaders() }
    ); 
  }

  private fetchAllCards(pagesize: number, pageindex: number, filter: string): void {
    const params = new HttpParams()
      .set("pagesize", pagesize.toString())
      .set("pageindex", pageindex.toString())
      .set("filter", filter);
  
    this.http.get<FilteredCards>(`${this.adress}cards/`, { headers: this.getDefaultHeaders(), params })
      .subscribe((result) => {
        console.log("Empfangene Karten:", result);
  
        
        const mappedCards = result.MonsterCards.map(card => ({
          id: card.id,
          name: card.name,
          type: card.type,
          humanReadableCardType: card.humanReadableCardType,
          frameType: card.frameType,
          description: card.description,
          race: card.race,
          archetype: card.archetype,
          ygoprodeckUrl: card.ygoprodeckUrl,
          imageLinks: card.imageLinks,
          prices: card.prices,
          attack: card.attack,
          defense: card.defense,
          level: card.level,
          attribute: card.attribute,
        }));
  
        this.cards$.next(mappedCards);
      });
  }

  public getAllCards(pagesize: number, pageindex: number, filter: string ): Observable<FilteredCards> {
    const params = new HttpParams()
    .set("pagesize", pagesize.toString())
    .set("pageindex", pageindex.toString())
    .set("filter", filter);

    //console.dir(this.http.get<FilteredCards>(`${this.adress}`, { headers: this.getDefaultHeaders(), params }));

    return this.http.get<FilteredCards>(`${this.adress}`, { headers: this.getDefaultHeaders(), params });
  }

  //Für die mock_data, wie es aus dem Backend ankommen sollte
  /*public getAllCards(pagesize: number, pageindex: number, filter: string): Observable<FilteredCards> {

    const params = new HttpParams()
      .set("pagesize", pagesize.toString())
      .set("pageindex", pageindex.toString())
      .set("filter", filter);

    return this.http.get<FilteredCards>("assets/mock_data.json", { headers: this.getDefaultHeaders(), params });
  }*/

  //Für Dummy Karten aktivieren
  /*public getAllCards(pagesize: number, pageindex: number, filter: string ): Observable<FilteredCards> {
    const filteredCards = DUMMY_MONSTER_CARDS.filter(card => 
      card.name.toLowerCase().includes(filter.toLowerCase())
    );

    const totalamount = filteredCards.length; // Gesamtanzahl der gefilterten Karten

    const startIndex = pageindex * pagesize;
    const endIndex = startIndex + pagesize;
    const paginatedCards = filteredCards.slice(startIndex, endIndex);

    return of({ MonsterCards: paginatedCards, totalamount });
  }*/

  private getDefaultHeaders(): HttpHeaders {
    return new HttpHeaders()
    .set("Content-Type", "application/json")
    .set('Access-Control-Allow-Origin', '*');
  }
}
