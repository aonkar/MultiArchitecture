import { Component } from '@angular/core';
import { DataService } from '../../services/data.service';
import {ActivatedRoute} from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'update-user',
  styleUrls: ['edit-user.component.scss'],
  templateUrl: 'edit-user.component.html'
})
export class EditUserComponent {
  userData;
  userForm = new FormGroup({
    userName : new FormControl(this._activatedRoute.snapshot.paramMap.get('userName'),Validators.required),
    password : new FormControl('',Validators.required),
    firstname : new FormControl(this._activatedRoute.snapshot.paramMap.get('firstname'),Validators.required),
    lastname : new FormControl(this._activatedRoute.snapshot.paramMap.get('lastname'),Validators.required),
    email : new FormControl(this._activatedRoute.snapshot.paramMap.get('email'),Validators.required),
    mobile : new FormControl(this._activatedRoute.snapshot.paramMap.get('mobile'),Validators.required),
    age : new FormControl(this._activatedRoute.snapshot.paramMap.get('age'),Validators.required),
    role : new FormControl(this._activatedRoute.snapshot.paramMap.get('role'),Validators.required),
    category : new FormControl(this._activatedRoute.snapshot.paramMap.get('category'),Validators.required),
    userId : new FormControl(this._activatedRoute.snapshot.paramMap.get('userId'),Validators.required)
  });
  
    constructor(private _dataService : DataService,
      private _activatedRoute : ActivatedRoute,private _toastrService : ToastrService){
        // console.log("UserData ::::::::",this._activatedRoute.snapshot.paramMap.get('userData'));
      // this.userData.userName = _activatedRoute.snapshot.paramMap.get('userName');
      // this.userData.firstname = _activatedRoute.snapshot.paramMap.get('firstname');
      // this.userData.lastname = _activatedRoute.snapshot.paramMap.get('lastname');
      // // this.userData.userName = _activatedRoute.snapshot.paramMap.get('password');
      // this.userData.mobile = _activatedRoute.snapshot.paramMap.get('mobile');
      // this.userData.role = _activatedRoute.snapshot.paramMap.get('role');
      // this.userData.category = _activatedRoute.snapshot.paramMap.get('category');
    }

    
  ngOnInit(){
    this._activatedRoute.data.subscribe(data=>{
      // console.log(data);
    })
  }


    updateUser(){
          this._dataService.put("/user",this.userForm.value).subscribe((response:any)=>{
                this._toastrService.success("User updated Successfully!!");
          }), (error)=>{ 
                this._toastrService.error("Error in user updation!!");
          }  ;
    }
}
