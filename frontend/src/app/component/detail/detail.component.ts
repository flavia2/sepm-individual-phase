import { Component, OnInit } from '@angular/core';
import {HorseService} from '../../service/horse.service';
import {ActivatedRoute, Router} from '@angular/router';
import {SportService} from '../../service/sport.service';
import {Horse} from '../../dto/horse';
import {Sport} from '../../dto/sport';

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.scss']
})
export class DetailComponent implements OnInit {
  error = false;
  errorMessage = '';
  editSuccess = false;
  deleteSuccess = false;
  deletedHorseName: string;

  horse: Horse;
  horses: Horse[];
  sports: Sport[];

  constructor(private horseService: HorseService,
              private sportService: SportService,
              private route: ActivatedRoute,
              public router: Router) { }

  ngOnInit(): void {
    this.route.params.subscribe(
      params => {
        const id = +params.id;
        this.getHorse(id);
      }
    );

    this.horseService.getAllHorses().subscribe(
      horses => {
        this.horses = horses;
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
  public getHorse(id) {
    this.horseService.getHorseById(id).subscribe(
      horse => {
        this.horse = horse;
      },
      error => {
        this.defaultServiceErrorHandling(error);
      }
    );
  }

  public editHorse(horse: Horse) {
    this.horse = horse;
    this.horseService.editHorse(horse).subscribe(
      (nextHorse: Horse) => {
        this.editSuccess = true;
        setTimeout(() => {
          this.editSuccess = false;
        }, 3000);
        this.horse = nextHorse;
      },
      error => this.defaultServiceErrorHandling(error)
    );
  }

  public deleteHorse(horse: Horse) {
    this.horses = this.horses.filter(h => h.id !== horse.id);
    this.horseService.deleteHorse(horse).subscribe();

    this.deletedHorseName = horse.name;
    this.deleteSuccess = true;
    setTimeout(() => {
      this.deleteSuccess = false;
      this.router.navigate(['/horses']);
    }, 5000);
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
