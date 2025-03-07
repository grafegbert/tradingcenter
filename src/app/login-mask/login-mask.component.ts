import { Component } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { MatDialogModule } from '@angular/material/dialog';
import { LoginRegistrationService } from '../login-registration.service';

@Component({
  selector: 'login-mask',
  templateUrl: './login-mask.component.html',
  styleUrl: './login-mask.component.scss'
})
export class LoginMaskComponent {
  loginGroup: FormGroup = new FormGroup({
    username: new FormControl(""),
    password: new FormControl("")
  });

  createAccGroup: FormGroup = new FormGroup({
    name: new FormControl(""),
    username: new FormControl(""),
    mail: new FormControl(""),
    password: new FormControl(""),
    confirmPassword: new FormControl(""),
  });

  constructor(private dialog: MatDialogModule, private service: LoginRegistrationService) {}

  emitLogin(): void {
    this.service.pushLoginData(
      {
        username: this.loginGroup.controls["username"].value,
        password: this.loginGroup.controls["password"].value
      }
    );
  }

  emitAccountCreation(): void {
    this.service.pushRegistrationData(
      {
        username: this.loginGroup.controls["username"].value,
        password: this.loginGroup.controls["password"].value,
        mail: this.loginGroup.controls["mail"].value
        
      }
    );
  }
}
