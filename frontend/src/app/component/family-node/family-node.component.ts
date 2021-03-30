import {Component, Input, OnInit} from '@angular/core';
import { HorseFamily } from 'src/app/dto/horseFamily';
import {HorseService} from '../../service/horse.service';

@Component({
  selector: 'app-family-node',
  templateUrl: './family-node.component.html',
  styleUrls: ['./family-node.component.scss']
})
export class FamilyNodeComponent implements OnInit {
  @Input() family: HorseFamily[];
  show: boolean;

  constructor(private horseService: HorseService) { }

  ngOnInit(): void {
    this.show = true;
  }

  public toggleParents(){
    this.show = !this.show;
  }
  public onDelete(horse) {
    this.horseService.deleteHorse(horse).subscribe();
  }
}
