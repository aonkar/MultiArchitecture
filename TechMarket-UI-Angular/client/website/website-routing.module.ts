// Modules
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

// Components
import { WebsiteComponent }         from './website.component';
import { HomeComponent }            from './home/home.component';
import { DashboardComponent }       from './dashboard/dashboard.component';
import { CreateUserComponent }       from './createUser/create-user.component';
import { EditUserComponent }       from './editUser/edit-user.component';

// Shared
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';

//Services
import { RouteGuardService } from '../services/route-gaurd.service';


// Route Configuration
export const routes: Routes = [{
  path: '',
  component: WebsiteComponent,
  children: [
    {
      path: '',
      children: [
        {
          path: '',
          component: HeaderComponent,
          outlet: 'Header',
        },
        {
        path: '',
        component: HomeComponent
      }]
    },
      {
        path: 'dashboard',
        canActivate: [RouteGuardService],
        children: [
          {
            path: '',
            component: HeaderComponent,
            outlet: 'Header',
          }, {
            path: '',
            component: DashboardComponent
          }, {
            path: '',
            component: FooterComponent,
            outlet: 'Footer',
          }]
      },
      {
        path: 'create-user',
        canActivate: [RouteGuardService],
        children: [
          {
            path: '',
            component: HeaderComponent,
            outlet: 'Header',
          }, {
            path: '',
            component: CreateUserComponent
          }, {
            path: '',
            component: FooterComponent,
            outlet: 'Footer',
          }]
      },
      {
        path: 'edit-user',
        canActivate: [RouteGuardService],
        children: [
          {
            path: '',
            component: HeaderComponent,
            outlet: 'Header',
          }, {
            path: '',
            component: EditUserComponent
          }, {
            path: '',
            component: FooterComponent,
            outlet: 'Footer',
          }]
      }

    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})

export class WebsiteRoutingModule {}
