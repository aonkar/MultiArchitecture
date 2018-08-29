import { Component } from '@angular/core';
import { OnInit } from '@angular/core/src/metadata/lifecycle_hooks';
import { AuthService } from '../../services/auth.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'header',
  styleUrls: [ 'header.scss'],
  templateUrl: 'header.pug'
})

export class HeaderComponent {

 public hideNavBarDet = !localStorage.getItem('loggedIn');
 public loggedInUser = localStorage.getItem('userName');

 constructor(private _authservice : AuthService,private router : Router){

 }
 logoutUser(){
   this._authservice.logout().subscribe((response:any)=>{
    this.router.navigate(['/']);
   }, (error)=>{
    this.router.navigate(['/']);
   });
 }
}
