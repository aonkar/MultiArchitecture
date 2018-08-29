import { NgModule }      from '@angular/core';
import { CommonModule }  from  '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Ng2SmartTableModule } from 'ng2-smart-table';
import { Ng2TableModule } from 'ng2-table/ng2-table';
import { RouterModule } from '@angular/router';



//COMPONENTS
import { WebsiteComponent }         from './website.component';
import { HeaderComponent }          from './header/header.component'
import { FooterComponent }          from './footer/footer.component';
import { HomeComponent }            from './home/home.component';
import { LoginComponent }           from './login/login.component';
import { DashboardComponent }       from './dashboard/dashboard.component';
import { CreateUserComponent }       from './createUser/create-user.component';
import { EditUserComponent }       from './editUser/edit-user.component';



// ROUTING
import { WebsiteRoutingModule } from './website-routing.module';

// SHARED MODULE
import { SharedModule } from '../shared/shared.module';

@NgModule({
    imports: [CommonModule,
      WebsiteRoutingModule,
      SharedModule,
      FormsModule,
       ReactiveFormsModule,
       Ng2SmartTableModule,
       Ng2TableModule,
       RouterModule
    ],
    declarations: [
      WebsiteComponent,
      HeaderComponent,
      FooterComponent,
      HomeComponent,
      LoginComponent,
      DashboardComponent,
      CreateUserComponent,
      EditUserComponent
    ]
})

export class WebsiteModule {}
