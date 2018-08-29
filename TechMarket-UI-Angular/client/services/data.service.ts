
import { Observable } from 'rxjs/Rx';
import { HttpService } from './http.service';
import { Injectable } from '@angular/core';
import { ConfigService } from './config.service';
import { NgSpinningPreloader } from 'ng2-spinning-preloader';
import { Http, Headers, Response, RequestOptions } from '@angular/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';


@Injectable()
export class DataService {
  private baseURL: string;
  static counter: number = 0;

  constructor(
    private http : HttpService,
    private configService: ConfigService,
    private ngSpinningPreloader: NgSpinningPreloader
  ){
    //this.baseURL = this.configService.getConfig('baseURL');
    this.baseURL = "http://localhost:8082/api"
  }

  get(url) {
    const requestOption = this.getOptions();
    //console.log("requestOption:::::::", requestOption);
    this.ngSpinningPreloader.start();
    DataService.counter += 1;
    let getURL = this.baseURL+ url;
    //console.log("getURL::::", getURL);
    let observable = new Observable(observer => {
      this.http.get(getURL,requestOption).subscribe((data: any) => {
          //console.log("success ::::: ");
          let response = JSON.parse(data._body);
          //console.log("response :::::", response);
          DataService.counter -= 1;
          if(DataService.counter === 0) {
            this.ngSpinningPreloader.stop();
          }
          observer.next(response);
        },
        (e) =>{
          //console.log("Erorr:::::");
          DataService.counter -= 1;
          this.ngSpinningPreloader.stop();
          DataService.handleError(e);
        });
    });
    return observable;
  }

  post(url,data){
    const options = this.getOptions();
    this.ngSpinningPreloader.start();
    DataService.counter += 1;
    let postURL = this.baseURL+ url;
    let observable = new Observable(observer => {
      this.http.post(postURL,data,options).subscribe((data: any) => {
          let response = JSON.parse(data._body);
          DataService.counter -= 1;
          if(DataService.counter === 0) {
            this.ngSpinningPreloader.stop();
          }
          observer.next(response);
        },
        (e) =>{
          DataService.counter -= 1;
          this.ngSpinningPreloader.stop();
          DataService.handleError(e);
        })
    });
    return observable;
  }

   delete(url){
    const options = this.getOptions();
    this.ngSpinningPreloader.start();
    DataService.counter += 1;
    let deleteURL = this.baseURL+ url;
    console.log("Delete url::::", deleteURL);
    let observable = new Observable(observer => {
      this.http.delete(deleteURL,options).subscribe((data: any) => {
          let response = JSON.parse(data._body);
          DataService.counter -= 1;
          if(DataService.counter === 0) {
            this.ngSpinningPreloader.stop();
          }
          observer.next(response);
        },
        (e) =>{
          DataService.counter -= 1;
          this.ngSpinningPreloader.stop();
          DataService.handleError(e);
        });
    });
    return observable;
  }

  put(url,data){
    const options = this.getOptions();
    this.ngSpinningPreloader.start();
    DataService.counter += 1;
    let putURL = this.baseURL+ url;
    console.log("data::::::", data);
    let observable = new Observable(observer => {
      this.http.put(putURL,data,options).subscribe((data: any) => {
          let response = JSON.parse(data._body);
          DataService.counter -= 1;
          if(DataService.counter === 0) {
            this.ngSpinningPreloader.stop();
          }
          observer.next(response);
        },
        (e) =>{
          DataService.counter -= 1;
          this.ngSpinningPreloader.stop();
          DataService.handleError(e);
        })
    });
    return observable;
  }

  static handleError(error: any){
    return Observable.throw(error.json().error || 'Server error');
  }

  getOptions(){
    let headers = new Headers();
    headers.append('Content-Type','application/json');
    headers.append('Access-Control-Allow-Origin','*');
    //console.log("token:::::", localStorage.getItem('token'));
    headers.append('Authorization',localStorage.getItem('token'));
    let options = new RequestOptions({ headers: headers, withCredentials: true }); 
    return options;
  }

}
