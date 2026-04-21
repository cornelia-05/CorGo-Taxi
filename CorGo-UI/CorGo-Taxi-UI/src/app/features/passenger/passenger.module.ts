import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { PassengerRoutingModule } from './passenger-routing.module';
import { MainComponent } from './main/main.component';

@NgModule({
  imports: [MainComponent, CommonModule, PassengerRoutingModule, FormsModule],
})
export class PassengerModule {}
