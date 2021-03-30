import {Component, Input, OnInit} from '@angular/core';
import { HorseFamily } from 'src/app/dto/horseFamily';

@Component({
  selector: 'app-family-node',
  templateUrl: './family-node.component.html',
  styleUrls: ['./family-node.component.scss']
})
export class FamilyNodeComponent implements OnInit {
  @Input() family: HorseFamily[];
  show: boolean;

  constructor() { }

  ngOnInit(): void {
  }

  public toggleParents(){
    this.show = !this.show;
  }
}
