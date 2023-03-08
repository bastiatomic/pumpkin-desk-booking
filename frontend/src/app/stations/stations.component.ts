import { Component } from '@angular/core';
import { StationServiceService } from '../station-service.service';
import { station } from '../station';
import { MockusersService } from '../mockusers.service';
import { user } from '../user';
import { reservation } from '../reservation';
import { ReservationsService } from '../reservations.service';

@Component({
  selector: 'app-stations',
  templateUrl: './stations.component.html',
  styleUrls: ['./stations.component.css']
})
export class StationsComponent {

  img_size = 500; //real-world-size
  img_scaling = 1.8
  openModal = false

  current_floor : String = "1"
  current_date = new Date()

  booking_requested = true;
  selectedStation! : station;

  modalUser!: station;

  tmp_reservation: reservation = {id:-1,
    timeStart: new Date(),
    timeEnd: new Date(),
    bookingByUserId: -1}

  local_user!: user;



  stations: station[] = []
  constructor(
    private users: MockusersService,
    private sS: StationServiceService,
    private reservationS : ReservationsService) { }

  ngOnInit() {
    this.stations = this.sS.getStations()
    this.local_user = this.users.getLoggedInUser()

  }
  toDEstring(date: Date) {
    var dateStr =
      ("00" + date.getDate()).slice(-2) + "." +
      ("00" + (date.getMonth() + 1)).slice(-2) + "." +
      date.getFullYear() + " " +
      " " +
      ("00" + date.getHours()).slice(-2) + ":" +
      ("00" + date.getMinutes()).slice(-2) + ":" +
      ("00" + date.getSeconds()).slice(-2);
    return dateStr;

  }

  openStationModal(station: station) {
    this.openModal = true
    this.modalUser = station;
  }

  select_floor(id: string) {
    this.current_floor = id
  }

  register_request(station: station){
    this.tmp_reservation.bookingByUserId = this.users.getLoggedInUser().id
    this.tmp_reservation.id =station.id
    this.tmp_reservation.timeEnd = new Date(Date.parse(this.tmp_reservation.timeEnd.toString()))
    this.tmp_reservation.timeStart = new Date(Date.parse(this.tmp_reservation.timeStart.toString()))
    console.log(this.tmp_reservation)
    this.reservationS.addReservation(this.tmp_reservation)


  }
  lookupTableByDateAndFloor(){
    console.log(this.current_date)
    console.log(this.current_floor)

    //send to backend and request all tables by ID and date

    this.stations = this.sS.getStationsByDateAndFloor(new Date(this.current_date), this.current_floor);

  }
}
