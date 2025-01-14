import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginRegistrationService {
  private loginControl$: BehaviorSubject<any> = new BehaviorSubject<any>("");
  private registrationControl$: BehaviorSubject<any> = new BehaviorSubject<any>("");
  private isLoggedIn$: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);

  pushLoginData(form: any): void {
    this.loginControl$.next(form);
  }

  pushRegistrationData(form: any): void {
    this.registrationControl$.next(form);
  }

  fetchLoginForm(): Observable<any> {
    return this.loginControl$.asObservable();
  }

  fetchRegistrationForm(): Observable<any> {
    return this.registrationControl$.asObservable();
  }
}
