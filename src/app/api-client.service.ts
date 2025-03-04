import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { MonsterCard } from './models/models';
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

  private fetchAllCards(): void {
    this.http.get<any[]>(this.adress + "cards/",  { headers: this.getDefaultHeaders() }).subscribe((result) => {
      console.error(result);
      result.forEach(card => {

        let builtCard = {
          id: card.number,
          name: card.name,
          type: card.type,
          humanReadableCardType: card.humanReadableCardType,
          frameType: card.frameType,
          description: card.description,
          race: card.race,
          archetype: card.archetype,
          ygoprodeckUrl: card.ygoprodeckUrl,
          sets: card.sets,
          imageLinks: card.imageLinks,
          prices: card.prices,
          attack: card.attack,
          defense: card.defense,
          level: card.level,
          attribute: card.attribute,
        };

        this.cards$.next([...this.cards$.value, builtCard]);
      });
    });
  }

  /*public getAllCards(): Observable<MonsterCard[]> {
    this.fetchAllCards();
    return this.cards$;
  }*/

  public getAllCards(): Observable<MonsterCard[]> {
    return of(DUMMY_MONSTER_CARDS);
  }

  private getDefaultHeaders(): HttpHeaders {
    return new HttpHeaders()
    .set("Content-Type", "application/json")
    .set('Access-Control-Allow-Origin', '*');
  }
}
