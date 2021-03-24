import {Component, Input, OnInit} from '@angular/core';
import {Sport} from '../../dto/sport';

@Component({
  selector: 'app-sport-individual',
  templateUrl: './sport-individual.component.html',
  styleUrls: ['./sport-individual.component.scss']
})
export class SportIndividualComponent implements OnInit {
  @Input() sport: Sport;

  constructor() { }

  ngOnInit(): void {
  }
}
