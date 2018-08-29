import { Injectable } from '@angular/core';
import { Http, Headers, Response, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { BehaviorSubject }    from 'rxjs/BehaviorSubject';

import 'rxjs/add/operator/map'
import { ConfigService } from './config.service';
import { NgSpinningPreloader } from 'ng2-spinning-preloader';


@Injectable()
export class AuthService {
  baseURL;
  private messageEvent = {};
  private newOrderEvent = new BehaviorSubject<Object>(this.messageEvent);
  newOrderEventAsObservable = this.newOrderEvent.asObservable();

  constructor(
    private http: Http,
    private config : ConfigService,
    private ngSpinningPreloader : NgSpinningPreloader) {
    this.ngSpinningPreloader.stop();
  }
  login(user) {
    // let loginUrl: string = this.config.getConfig('signInURL');
    let loginUrl: string = "http://localhost:8082/api/sign-in";
    let headers = new Headers();
    let options = new RequestOptions({ headers: headers, withCredentials: true });
    return this.http.post(loginUrl,user,options)
      .catch(this.handleError)
      .map((response: Response) => {
        // console.log(response.json());
        let responseData = response.json();

        if (responseData && responseData.status) {
            // store user details and jwt token in session storage to keep user logged in between page refreshes
            localStorage.setItem('userId', responseData.responseObject.userId);
            localStorage.setItem('userName', responseData.responseObject.userName);
            let role : string = responseData.responseObject.role;
            localStorage.setItem('role', role.toLowerCase());
            localStorage.setItem('token', responseData.responseObject.jwt);
            localStorage.setItem('loggedIn', "true");
        }
        return responseData;
      });
  }

  // remove user from local storage to log user out
  logout() {
    const options = this.getOptions();
    let logoutUrl: string = "http://localhost:8082/api/logout";
    let headers = new Headers();
    return this.http.get(logoutUrl,options)
      .catch(this.handleError)
      .map((response: Response) => {
        localStorage.clear();
        let responseData = response.json();
        return responseData;
      });
    
  }

  // Send Subscription information to server
  sendSubscription(subInfo){
    let urls :any = {}; //this.config.getConfig('urls');
    let sendSubUrl: string = this.baseURL + urls.sendSubUrl;
    return this.http.post(sendSubUrl,subInfo)
      .catch(this.handleError)
      .map((response: Response) => {
        //console.log('subscription info sent successfully. Response from sendSub '+response);
      });
  }


  handleError(error: any){
    return Observable.throw(error.json().error || 'Server Error');
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
