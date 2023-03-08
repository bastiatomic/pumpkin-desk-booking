import { Component, Injectable } from '@angular/core';
import { ApiService } from '../api.service';

import { Router } from '@angular/router';
import { MockusersService } from '../mockusers.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

@Injectable()
export class LoginComponent {

  constructor(private router: Router, private api : ApiService, private userBase: MockusersService){}

  tmp_user_name : String = ""
  tmp_user_pw : String = ""
  attempts_used = 0;
  credentials : any
  login_load_modal = false
  login_modal_text = "Lade deine Daten ..."


  login(){

    this.login_load_modal = true
    setTimeout(() => {
      
    this.login_load_modal = false
      this.login2()
    }, 5000);
    
  }

  async login2(){

    const content = await this.api.GET_data_by_value(`user/webauth?userID=3&pwHash=${this.tmp_user_pw}`)
    this.credentials = content
    this.credentials = this.credentials.validation


    if(true){


      
      this.userBase.setGlobalUserByName(this.tmp_user_name)
      this.router.navigate(['/dashboard']);
    } else{
      this.error_counter()
    }
  }

  error_counter(){

    this.attempts_used += 1
    window.alert("Fehler! Passwort oder Username stimmen nicht überein")
    
    if(this.attempts_used == 3){
      window.alert("ERROR")
      //TODO: send message to admin BE
    }
  }

}
