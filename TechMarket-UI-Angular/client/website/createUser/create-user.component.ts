import { Component } from '@angular/core';
import { DataService } from '../../services/data.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'create-user',
  styleUrls: ['create-user.component.scss'],
  templateUrl: 'create-user.component.html'
})
export class CreateUserComponent {
    constructor(private _dataService : DataService,
    private _toastr : ToastrService){
        
    }
    createUser(value : any){
        value.category='';    
        value.isUserActive=true;    
        this._dataService.post("/user",value).subscribe((response : any)=>{
            this._toastr.success("User Successfully Created!!")
        },(e) =>{
            this._toastr.error("Error in User Creation!!");
          });
    }
}
