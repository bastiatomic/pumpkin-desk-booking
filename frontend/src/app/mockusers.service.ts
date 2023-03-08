import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { user } from './user';

@Injectable({
  providedIn: 'root'
})
export class MockusersService {

  mock_users : user[] = [
    {id: 0, username: "Bastian", password: "root", colorPassword:"", isAdmin:1, isNewUser: 0},
    {id: 1, username: "1", password: "1", colorPassword:"", isAdmin:1, isNewUser: 0},
    {id: 2, username: "Daniel", password: "root", colorPassword:"", isAdmin:1, isNewUser: 0}
  ]

  local_user : user =  {id: -1, username: "", password: "", colorPassword:"", isAdmin:1, isNewUser: 0}

  getMockUsers(){
    return this.mock_users
  }

  setGlobalUserByName(name : String){

    const local_user = this.mock_users.find(a => a.username === name)

    if(local_user){
      this.local_user = local_user
    } else {
      //TODO: throw invalid error
    }

  }

  functionOnWhichRedirectShouldHappen(){
  }
  getLoggedInUser(){
    return this.local_user
  }

}
