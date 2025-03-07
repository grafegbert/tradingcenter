import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomepageComponent } from './homepage/homepage.component';
import { LoginMaskComponent } from './login-mask/login-mask.component';

const routes: Routes = [
  {path: "homepage", component: HomepageComponent},
  {path: "login-and-add-account", component: LoginMaskComponent},
  {path: '**', redirectTo: "homepage"}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
