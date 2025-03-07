import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SearchServiceService {
  private searchControl$: BehaviorSubject<string> = new BehaviorSubject<string>("");

  pushUserInput(searchValue: string): void {
    console.log("SEARCH VALUE: ", searchValue);
    this.searchControl$.next(searchValue);
  }

  fetchSearchValue(): Observable<string> {
    return this.searchControl$.asObservable();
  }
}
