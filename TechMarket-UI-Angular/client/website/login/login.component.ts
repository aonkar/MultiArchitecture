import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
    selector: 'login',
    styleUrls: [ 'login.component.scss'],
    templateUrl: 'login.component.html',
})

export class LoginComponent{
    private alerts;
    private returnURL;
    constructor(
        private router : Router,
        private route :  ActivatedRoute,
        private authService :  AuthService
    ) {

        if(localStorage.getItem('loggedIn') === "true"){
            this.returnURL = "/dashboard";      
            this.router.navigate([this.returnURL]);
          }
     }

      signInUser(userForm){
      this.authService.login(userForm).subscribe((response : any)=> {
        if(response.status){
          this.returnURL = "/dashboard";
        }
        this.router.navigate([this.returnURL]);
  }, function(error) {
        this.alerts.push({
          type: "danger",
          msg: this.loginMessages['error-message-5'],
          timeout: 2000
        })
      }.bind(this));
    
    }
}
