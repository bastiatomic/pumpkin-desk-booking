import { Injectable } from '@angular/core';
import { reservation } from './reservation';
import { user } from './user';

@Injectable({
  providedIn: 'root'
})
export class ReservationsService {

  constructor() { }

  MOCK_RESERVATIONS : reservation[] = [
    {id: 0, timeStart: this.randomDate(), timeEnd: this.randomDate(), bookingByUserId: 0},
    {id: 1, timeStart: this.randomDate(), timeEnd: this.randomDate(), bookingByUserId: 2},
    {id: 2, timeStart: this.randomDate(), timeEnd: this.randomDate(), bookingByUserId: 3},
    {id: 3, timeStart: this.randomDate(), timeEnd: this.randomDate(), bookingByUserId: 4},
    {id: 4, timeStart: this.randomDate(), timeEnd: this.randomDate(), bookingByUserId: 0},
  ]

  getReservationsByUser(user: user){
    return this.MOCK_RESERVATIONS.filter((a) => a.bookingByUserId === user.id)
  }

  getReservations(){
    return this.MOCK_RESERVATIONS
  }

  addReservation(res : reservation){
    this.MOCK_RESERVATIONS.push(res)
  }


  randomDate() {
    var start = new Date(2012, 0, 1)
    var end = new Date()
    return new Date(start.getTime() + Math.random() * (end.getTime() - start.getTime()));
  }

  getRandomStatus(){
    const status = ["blocked", "free"]

    return status[Math.floor(Math.random() * status.length)];
  }

  getRandomRange(min : number, max: number){

    var x = Math.floor(Math.random() * (max - min + 1) + min)

    return x
  }

}
