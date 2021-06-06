import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {LoginComponent} from './login.component';
import {LoginRoutingModule} from './login-routing.module';
import {PoModule} from '@po-ui/ng-components';
import {PoPageLoginModule, PoTemplatesModule} from '@po-ui/ng-templates';

@NgModule({
  declarations: [
    LoginComponent
  ],
  imports: [
    CommonModule,
    LoginRoutingModule,
    PoModule,
    PoPageLoginModule,
    PoTemplatesModule
  ]
})
export class LoginModule { }
