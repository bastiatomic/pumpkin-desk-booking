// THIS IS THE ONLY ALLOWED PLACE TO MAKE API REQUESTS.
// YOUR EXISTENCE WILL BE PURGED
// IF YOU USE ANOTHER SERVICE OR EVEN A TEMPLATE
// I HAVE SPOKEN!

import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { lastValueFrom, Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ApiService {
  constructor(private httpClient: HttpClient) {}

  async GET_data_by_value(api_range: string){
    try {
      const response = await lastValueFrom(this.httpClient.get(`/api/${api_range}`))
      console.log("GET: ");
      console.log(response)
      return response;
    } catch (error) {
        console.log(`Aborted`);
        return error;
    }
  }
}
