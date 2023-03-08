import { Injectable } from '@angular/core';
import { news } from './news';

@Injectable({
  providedIn: 'root'
})
export class NewsService {

  constructor() { }

  news : news[] = [
    {id: 0, content: "Ich mag Züge", author: "Maik Baum"},
    {id: 1, content: "Es brannte gestern", author: "Karl Lauterbach"},
    {id: 2, content: "Take a hike", author: "Maria D"},
    {id: 3, content: "James ist OOO until Doomsday", author: "Gustav D"},
  ]

}
