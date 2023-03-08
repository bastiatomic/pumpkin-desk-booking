import { Injectable } from '@angular/core';
import { station } from './station';

@Injectable({
  providedIn: 'root'
})
export class StationServiceService {

  STATIONS : station[] = []

  constructor() { }

  firstPull = true

  FIXED_LOCATIONS = [
    //erdgeschoss
    {id: 1, top: 313, left: 118},
    {id: 2, top: 347, left: 118},
    {id: 3, top: 417, left: 189},
    {id: 4, top: 448, left: 189},
    {id: 5, top: 344, left: 258},
    {id: 6, top: 373, left: 258},
    {id: 7, top: 227, left: 184},
    {id: 8, top: 255, left: 164},
    {id: 9, top: 280, left: 277},
    {id: 10, top: 125, left: 206},
    {id: 11, top: 53, left: 192},
    {id: 12, top: 152, left: 337},
    {id: 13, top: 250, left: 337},
  //1. etage
    {id: 14, top: 120, left: 97},
    {id: 15, top: 154, left: 97},
    {id: 16, top: 210, left: 171},
    {id: 17, top: 238, left: 152},
    {id: 18, top: 328, left: 123},
    {id: 19, top: 360, left: 123},
    {id: 20, top: 428, left: 159},
    {id: 21, top: 155, left: 337},
    {id: 22, top: 245, left: 340},
    {id: 23, top: 460, left: 159},
    {id: 24, top: 322, left: 275},
    {id: 25, top: 383, left: 275},
]

  BACKEND_STATION_DATA = [
    { id: 0,
      name: "",
      floor: "",
      blocked: true,
      equipment: "",
      bookedByUserId: 0,
      bookedByName: "",
      bookingStartTime: new Date(),
      bookingEndTime: new Date()}
  ]

  construct_station_list(){

    //this.BACKEND_STATION_DATA.forEach((e) => {
      for (let i = 1; i<=25; i++){
      this.STATIONS.push(
        {
        id: i,
        name: this.randomString(4),
        floor: this.getFloor(i),
        status: this.getRandomStatus(),
        equipment: this.randomString(20),
        bookedByUserId: i,
        bookedByName: this.randomString(10),
        bookingStartTime: this.randomDate(),
        bookingEndTime: this.randomDate(),
        location: {left: this.FIXED_LOCATIONS[i-1].top-10,top: this.FIXED_LOCATIONS[i-1].left-10},
      }
      )
    }
    this.STATIONS[0].bookingStartTime = new Date("2022-01-01")
    this.STATIONS[0].bookingEndTime = new Date("2024-01-01")
    this.STATIONS[0].status = "blocked";
    return this.STATIONS

    
  }

  getStationsByDateAndFloor(targetDate : Date, floor : String){
    // API call
    //floor is deprecated

    for (let i = 0; i< this.STATIONS.length; i++){
      if( this.STATIONS[i].bookingStartTime <= targetDate && this.STATIONS[i].bookingEndTime >= targetDate){
       this.STATIONS[i].status = "blocked"
      } else {
        this.STATIONS[i].status = "free"
      }

    }

    return this.STATIONS
  }

  getFloor(id: number){
    if(id <= 13){
      return "1"
    }
    return "2"
  }

  getStations(){

    if(this.firstPull){
      this.STATIONS = this.construct_station_list()
      this.firstPull = false
    }
    return this.STATIONS
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
  randomString(length : number){

    let result = '';
    const characters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
    const charactersLength = characters.length;
    let counter = 0;
    while (counter < length) {
      result += characters.charAt(Math.floor(Math.random() * charactersLength));
      counter += 1;
    }
    return result

  }
}
