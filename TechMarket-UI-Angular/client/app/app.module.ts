import { NgModule }       from '@angular/core';
import { BrowserModule }  from '@angular/platform-browser';
import { HttpModule }     from '@angular/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';

import { AppComponent }  from './app.component';

import { SharedModule } from '../shared/shared.module';

// ROUTING
import { AppRoutingModule } from './app-routing.module';

// SERVICES
import { HttpService }    from '../services/http.service';
import { DataService }    from '../services/data.service';
import { ConfigService }  from '../services/config.service';
import { AuthService }    from "../services/auth.service";
import { RouteGuardService } from "../services/route-gaurd.service"

//Dashboard
import { ScriptLoaderService } from "../services/script-loader.service";
import { GlobalErrorHandler } from "../services/error-handler.service";

import { NgSpinningPreloader } from 'ng2-spinning-preloader';

@NgModule({
  imports: [
    BrowserModule,
    HttpModule,
    AppRoutingModule,
    SharedModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot()
  ],
  declarations: [
    AppComponent
  ],
  providers: [
    HttpService,
    DataService,
    ConfigService,
    AuthService,
    NgSpinningPreloader,
    ScriptLoaderService,
    GlobalErrorHandler,
    RouteGuardService
  ],
  bootstrap: [
    AppComponent
  ]
})
export class AppModule { }
