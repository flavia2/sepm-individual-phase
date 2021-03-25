import {Component, OnInit} from '@angular/core';
import {SportService} from '../../service/sport.service';
import {Sport} from '../../dto/sport';
import {Router} from '@angular/router';

@Component({
  selector: 'app-sport',
  templateUrl: './sport.component.html',
  styleUrls: ['./sport.component.scss']
})
export class SportComponent implements OnInit {

  error = false;
  errorMessage = '';
  success = false;
  sport: Sport;
  sports: Sport[];
  name: string;
  description: string;


  constructor(private sportService: SportService, public router: Router) {
  }

  ngOnInit() {
    this.sportService.getAllSports().subscribe(
      sports => {
        this.sports = sports;
      },
      error => {
        this.defaultServiceErrorHandling(error);
      }
    );
  }

  private onAdd(){
    const newSport = new Sport(0, this.name, this.description);
    this.sportService.createSport(newSport).subscribe(
      (sport: Sport) => {
        this.sports.push(sport);
        this.resetForm();
        this.router.navigate(['/sports']);
        this.success = true;
        setTimeout(() => {
          this.success = false;
        }, 3000);
        this.sport = sport;
      },
      error => {
        this.defaultServiceErrorHandling(error);
      }
    );
  }
  private resetForm(){
    this.name = null;
    this.description = null;
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
