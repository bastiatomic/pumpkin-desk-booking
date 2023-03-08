import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { StationsComponent } from './stations/stations.component';
import { LoginComponent } from './login/login.component';
import { HeaderComponent } from './header/header.component';
import { StationServiceService } from './station-service.service';
import { FormsModule } from '@angular/forms';
import { NavbarComponent } from './navbar/navbar.component';
import { NewsComponent } from './news/news.component';
import { ProfileComponent } from './profile/profile.component';
import { ReservationsComponent } from './reservations/reservations.component';
import { HelpMeComponent } from './help-me/help-me.component';
import { HttpClient } from '@angular/common/http';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    StationsComponent,
    LoginComponent,
    HeaderComponent,
    NavbarComponent,
    NewsComponent,
    ProfileComponent,
    ReservationsComponent,
    HelpMeComponent
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  exports: [LoginComponent],
  providers: [LoginComponent,HttpClient],
  bootstrap: [AppComponent]
})
export class AppModule { }
