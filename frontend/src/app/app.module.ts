import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HeaderComponent} from './component/header/header.component';
import {SportComponent} from './component/sport/sport.component';
import {HttpClientModule} from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { SportIndividualComponent } from './component/sport-individual/sport-individual.component';
import { HorseComponent } from './component/horse/horse.component';
import { IndividualComponent } from './component/individual/individual.component';
import { DetailComponent } from './component/detail/detail.component';
import { FamilyComponent } from './component/family/family.component';
import { FamilyNodeComponent } from './component/family-node/family-node.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    SportComponent,
    SportIndividualComponent,
    HorseComponent,
    IndividualComponent,
    DetailComponent,
    FamilyComponent,
    FamilyNodeComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
