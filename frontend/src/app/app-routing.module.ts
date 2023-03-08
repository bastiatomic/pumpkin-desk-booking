import { Component, NgModule } from '@angular/core';
import { Router, RouterModule, Routes } from '@angular/router';
import { HelpMeComponent } from './help-me/help-me.component';
import { LoginComponent } from './login/login.component';
import { ProfileComponent } from './profile/profile.component';
import { ReservationsComponent } from './reservations/reservations.component';
import { StationsComponent } from './stations/stations.component';

const routes: Routes = [
 {path: "dashboard", component: StationsComponent},
 {path: '',   redirectTo: 'login', pathMatch: 'full' },
 { path: "login", component: LoginComponent },
 { path: "profile", component: ProfileComponent },
 { path: "reservations", component: ReservationsComponent },
 { path: "helpme", component: HelpMeComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { 
  constructor(private router: Router){}
  
}

