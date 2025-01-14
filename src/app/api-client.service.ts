import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { PokemonCard } from './models/models';

@Injectable({
  providedIn: 'root'
})
export class ApiClientService { 
  cards$: BehaviorSubject<PokemonCard[]> = new BehaviorSubject<PokemonCard[]>([]);
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

  public get(pokemonName: string): Observable<HttpResponse<PokemonCard>> {
    return this.http.post<HttpResponse<PokemonCard>>(
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
          imageSmall: card.images.small,
          imageLarge: card.images.large,
          priceInEuro: 1.12
        };

        this.cards$.next([...this.cards$.value, builtCard]);
      });
    });
  }

  public getAllCards(): Observable<PokemonCard[]> {
    this.fetchAllCards();
    return this.cards$;
  }

  private getDefaultHeaders(): HttpHeaders {
    return new HttpHeaders()
    .set("Content-Type", "application/json")
    .set('Access-Control-Allow-Origin', '*');
  }
}
