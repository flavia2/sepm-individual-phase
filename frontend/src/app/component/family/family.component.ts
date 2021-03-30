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
  horse: Horse;
  family: HorseFamily[];
  gen: number;

  constructor(private horseService: HorseService, private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.getFamily();
  }
  public getFamilyWithGens(): void {
    this.horseService.getFamilyTreeWithGens(this.horse.id, this.gen).subscribe(
      family => this.family = family,
    );
  }

  private getFamily(): void{
    const id = +this.route.snapshot.paramMap.get('id');

    this.horseService.getHorseById(id).subscribe(
      horse => this.horse = horse
    );
    this.horseService.getFamilyTree(id).subscribe(
      family => this.family = family
    );
  }
}
