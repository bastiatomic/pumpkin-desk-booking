import { Component } from '@angular/core';
import { ReservationsService } from '../reservations.service';
import { reservation } from '../reservation';
import { MockusersService } from '../mockusers.service';

@Component({
  selector: 'app-reservations',
  templateUrl: './reservations.component.html',
  styleUrls: ['./reservations.component.css']
})
export class ReservationsComponent {

  constructor(private reservations: ReservationsService, private user: MockusersService){}

  reservations_list : reservation[] = []

  ngOnInit(){
  
    this.reservations_list = this.reservations.getReservationsByUser(this.user.getLoggedInUser())
  } 

  cancelRes(id: number){
    console.log(id)

    this.reservations_list = this.reservations_list.filter(a => a.id !== id);

   console.log(this.reservations_list)

  }

  toDE_datetime(date: Date) {
    return date.getDate() + "." +(date.getMonth()+1) +"." +date.getFullYear()+ "|" +date.getHours()+ "." +date.getMinutes()

  }
  toDE_date(date: Date) {
    return date.getDate() + "." +(date.getMonth()+1) + "." + date.getFullYear()

  }

  toDE_time(date: Date) {
    return date.getHours()+ ":" +(date.getMinutes())

  }

}
