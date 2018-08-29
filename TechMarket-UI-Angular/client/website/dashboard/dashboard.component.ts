import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { DataService } from '../../services/data.service';
import { Response } from '@angular/http/src/static_response';
import { ToastrService } from 'ngx-toastr';


@Component({
  selector: 'dashboard',
  styleUrls: ['dashboard.component.scss'],
  templateUrl: 'dashboard.component.html'
})

export class DashboardComponent implements OnInit{
   data;

  constructor(
    private _router : Router,
    private _route :  ActivatedRoute,
    private _dataService :  DataService,
    private _toasterService : ToastrService,
    private router : Router
  ){

  }
  
  ngOnInit(){
    //console.log("Inside ngoninit");
    this._dataService.get("/users").subscribe((response:any)=>{
      if(response.status){
        this.data = response.responseObject;
      }
      // console.log("response:::::::::::::", response);
    });
  }


  settings = {
    // addFilter : true,
    actions:{
      position : 'right'
    },
    pager : {
      perPage: 10
    },
    hideSubHeader : false,
    mode : 'external',
    edit : '<img src="../../../public/img/edit-icon.png" alt="Edit" onclick="editUser() id="editUser"/>',
    delete : '<img src="../../../public/img/delete.png" alt="Delete" onclick="deleteUser()" id="deleteUser"/>',
    noDataMessage : 'No Users Found',
    columns: {
      userId:{
        title: 'userId',
        width : '10%'
      },
      userName: {
        title: 'Username',
        width : '10%'
      },
      firstname: {
        title: 'First Name',
        width : '10%'
      },
      lastname: {
        title: 'Last Name',
        width : '10%'
      },
      email: {
        title: 'Email',
        width : '10%'
      },
      mobile: {
        title: 'Mobile',
        width : '10%'
      },
      role: {
        title: 'Role',
        width : '10%'
      },
      category: {
        title: 'Category',
        width : '10%',
      }
    }
  };

onEdit(event){
  let userData = event.data;
  this._router.navigate(['/edit-user', userData], {skipLocationChange: true});
}

onDelete(event){
  this._dataService.delete("/user/" + event.data.userId).subscribe((response:any)=>{
    this._toasterService.success("User Deleted!!")
    this.router.navigate(['/dashboard']);
  }),(error)=>{
    this._toasterService.error("Error in User Deletion!!");
  }   ;
}
}
