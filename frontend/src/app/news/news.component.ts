import { Component } from '@angular/core';
import { news } from '../news';
import { NewsService } from '../news.service';
import { MockusersService } from '../mockusers.service';
import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, } from '@angular/common/http';
import { sha256 } from 'hash.js';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

import { Router } from '@angular/router';

@Component({
  selector: 'app-news',
  templateUrl: './news.component.html',
  styleUrls: ['./news.component.css']
})
export class NewsComponent {

  news : news[] = []

  constructor(private newsService: NewsService, private http: HttpClient) { }

  ngOnInit(){
    this.news = this.newsService.news
    //TODO: get from backend
  }

  GET_news(){
    

  }

}
