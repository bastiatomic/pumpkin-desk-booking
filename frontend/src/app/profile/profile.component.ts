import { Component } from '@angular/core';
import { MockusersService } from '../mockusers.service';
import { user } from '../user';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent {

  constructor(private auth: MockusersService){}

  
  local_user!: user;


  user_options_change = { old_pw : "", new_pw: "", new_pw2 :"", old_mail: "", new_mail: "", controller_pw: 
  [{id: 0, color: "white"},{id: 1, color: "white"},{id: 2, color: "white"},{id: 3, color: "white"}]}

  color_selector= [{color: "red"}, {color: "green"},{color: "blue"},{color: "yellow"}]

  ngOnInit(){

    
    this.local_user = this.auth.getLoggedInUser()
  }
  request_mail_change(){
    console.log(this.user_options_change)
  }

  request_pw_change(){
    console.log(this.user_options_change)
  }
  request_controller_pw_change(){
    console.log(this.user_options_change)
  }

  color_change(item: any,color: any){
   this.user_options_change.controller_pw[item.id].color = color

  }

}
