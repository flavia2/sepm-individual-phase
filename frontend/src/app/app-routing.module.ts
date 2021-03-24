import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {SportComponent} from './component/sport/sport.component';
import {HorseComponent} from './component/horse/horse.component';

const routes: Routes = [
  {path: '', redirectTo: 'horses', pathMatch: 'full'},
  {
    path: 'sports', component: SportComponent,
    children: [
      {path: 'add', component: SportComponent}
    ]
  },
  {
    path: 'horses', component: HorseComponent,
    children: [
      {path: 'add', component: HorseComponent}
    ]
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
