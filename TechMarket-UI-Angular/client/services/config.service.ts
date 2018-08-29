import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { Observable } from 'rxjs/Rx';

@Injectable()
export class ConfigService {

  private config: Object = null;

  constructor(
    private http: Http
  ) {
    
  }
  ngOnInit(){
    // this.load();
  }
  public load() {
    return new Promise((resolve, reject) => {
        this.http.get('config.json').map( res => res.json() ).catch((error: any):any => {
             console.error('Error reading config.json configuration file');
            resolve(error);
            return Observable.throw(error.json().error || 'Server error');
        }).subscribe( (responseData) => {
            this.config = responseData;
            //console.log("response data ::::", this.config);
            
             resolve(true);
        });

    });
}

public getConfig(key: any) {
  let url = this.config['baseURL']+this.config[key]
  return url;
}

}
