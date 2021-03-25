import { Component, OnInit } from '@angular/core';
import {Sport} from '../../dto/sport';
import {Horse} from '../../dto/horse';
import {Router} from '@angular/router';
import {SportService} from '../../service/sport.service';
import {HorseService} from '../../service/horse.service';

@Component({
  selector: 'app-horse',
  templateUrl: './horse.component.html',
  styleUrls: ['./horse.component.scss']
})
export class HorseComponent implements OnInit {
  error = false;
  errorMessage = '';
  addSuccess = false;
  editSuccess = false;

  horse: Horse;
  horses: Horse[];
  sports: Sport[];

  name: string;
  description: string;
  birthday: string;
  gender: string;
  sport: number;
  parentId1: number;
  parentId2: number;
  female: Horse[];
  male: Horse[];

  constructor(private horseService: HorseService, private sportService: SportService, private router: Router) { }

  ngOnInit(): void {
    this.horseService.getAllHorses().subscribe(
      horses => {
        this.horses = horses;
        this.loadParents();
      },
      error => {
        this.defaultServiceErrorHandling(error);
      }
    );

    this.sportService.getAllSports().subscribe(
      sports => {
        this.sports = sports;
      },
      error => {
        this.defaultServiceErrorHandling(error);
      }
    );


  }
  loadParents(){
    this.female = this.horses.filter(f => f.gender === 'female');
    this.male = this.horses.filter(m => m.gender === 'male');
  }

  onAdd() {
    const newHorse = new Horse(0, this.name, this.description, this.birthday, this.gender, this.sport, this.parentId1, this.parentId2);
    this.horseService.createHorse(newHorse).subscribe(
      (horse: Horse) => {
        this.horses.push(horse);
        this.resetForm();
        this.router.navigate(['/horses']);
        this.addSuccess = true;
        setTimeout(() => {
          this.addSuccess = false;
        }, 3000);
        this.horse = horse;
      },
      error => {
        this.defaultServiceErrorHandling(error);
      }
    );
  }
  private resetForm(){
    this.name = null;
    this.description = null;
    this.birthday = null;
    this.gender = null;
    this.sport = null;
    this.parentId1 = null;
    this.parentId2 = null;
    this.loadParents();
  }

  private editHorse(horse: Horse) {
    this.horseService.editHorse(horse).subscribe(
      (nextHorse: Horse) => {
        this.editSuccess = true;
        this.editSuccess = true;
        setTimeout(() => {
          this.editSuccess = false;
        }, 3000);
      },
      error => this.defaultServiceErrorHandling(error)
    );
  }

  private deleteHorse(horse: Horse) {
    this.horses = this.horses.filter(h => h.id !== horse.id);
    this.horseService.deleteHorse(horse).subscribe();
  }

  private defaultServiceErrorHandling(error: any) {
    console.log(error);
    this.error = true;
    setTimeout(() => {
      this.error = false;
    }, 4000);
    if (error.status === 0) {
      // If status is 0, the backend is probably down
      this.errorMessage = 'The backend seems not to be reachable';
    } else if (error.error.message === 'No message available') {
      // If no detailed error message is provided, fall back to the simple error name
      this.errorMessage = error.error.error;
    } else {
      this.errorMessage = error.error.message;
    }
  }
}
