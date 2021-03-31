import { Component, OnInit } from '@angular/core';
import {HorseService} from '../../service/horse.service';
import {Horse} from '../../dto/horse';
import {HorseFamily} from '../../dto/horseFamily';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-family',
  templateUrl: './family.component.html',
  styleUrls: ['./family.component.scss']
})
export class FamilyComponent implements OnInit {
  error = false;
  errorMessage = '';

  horse: Horse;
  family: HorseFamily[];
  selectedGen: number;

  constructor(private horseService: HorseService, private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.getFamily();
  }
  public getFamilyWithGens(): void {
    this.horseService.getFamilyTreeWithGens(this.horse.id, this.selectedGen).subscribe(
      family => {
        this.family = family;
      },
      error => {
        this.defaultServiceErrorHandling(error);
      }
    );
  }

  private getFamily(): void{
    const id = +this.route.snapshot.paramMap.get('id');

    this.horseService.getHorseById(id).subscribe(
      horse => {
        this.horse = horse;
      },
      error => {
        this.defaultServiceErrorHandling(error);
      }
    );
    this.horseService.getFamilyTree(id).subscribe(
      family => {
        this.family = family;
      },
      error => {
        this.defaultServiceErrorHandling(error);
      }
    );
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
