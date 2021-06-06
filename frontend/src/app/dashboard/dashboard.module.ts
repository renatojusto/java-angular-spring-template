import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {DashboardComponent} from './dashboard.component';
import {DashboardRoutingModule} from './dashboard-routing.module';
import {
  PoContainerModule,
  PoDividerModule,
  PoInfoModule,
  PoMenuModule,
  PoNavbarModule,
  PoPageModule,
  PoTableModule,
  PoTagModule,
  PoToolbarModule
} from '@po-ui/ng-components';

@NgModule({
  declarations: [
    DashboardComponent
  ],
  imports: [
    CommonModule,
    DashboardRoutingModule,
    PoToolbarModule,
    PoMenuModule,
    PoPageModule,
    PoContainerModule,
    PoTableModule,
    PoDividerModule,
    PoInfoModule,
    PoTagModule,
    PoNavbarModule
  ]
})
export class DashboardModule { }
