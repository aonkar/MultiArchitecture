import { Injectable } from '@angular/core';
import { Http, XHRBackend, RequestOptions, Request, RequestOptionsArgs, Response, Headers } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { Router} from '@angular/router';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';

@Injectable()
export class HttpService extends Http {

  constructor (
    backend: XHRBackend,
    options: RequestOptions, 
    private _router : Router
  ) {
    super(backend, options);
    const token = localStorage.getItem('token');
    options.headers.set('Authorization', `${token}`);
    options.withCredentials = true;
   
  }

  request(url: string|Request, options?: RequestOptionsArgs): Observable<Response> {
    let token = localStorage.getItem('token');
    if (typeof url === 'string') { // meaning we have to add the token to the options, not in url
      if (!options) {
        options = {headers: new Headers()};
      }
      options.headers.set('Authorization', `${token}`);
    } else {
      url.headers.set('Authorization', `${token}`);
    }
    return super.request(url, options).catch(this.catchAuthError(this));
  }

  private catchAuthError (self: HttpService) {
    return (res: Response) => {
      //console.log(res);
      if (res.status === 401 || res.status === 403) {
        localStorage.clear();
        this._router.navigate(['/']);

      }
      return Observable.throw(res);
    };
  }
}
