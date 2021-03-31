import {Component, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
import {Horse} from '../../dto/horse';
import {Sport} from '../../dto/sport';

@Component({
  selector: 'app-individual',
  templateUrl: './individual.component.html',
  styleUrls: ['./individual.component.scss']
})
export class IndividualComponent implements OnInit {
  @Input() horse: Horse;
  @Input() horses: Horse[];
  @Input() sports: Sport[];
  @Output() editHorse: EventEmitter<Horse> = new EventEmitter();
  @Output() deleteHorse: EventEmitter<Horse> = new EventEmitter();
  @ViewChild('myModalClose') modalClose;

  horseCopy: Horse;
  female: Horse[];
  male: Horse[];

  constructor() {
  }

  ngOnInit(): void {
    this.female = this.horses.filter(f => f.gender === 'female' &&
      f.id !== this.horse.id);
    this.male = this.horses.filter(m => m.gender === 'male' &&
      m.id !== this.horse.id);
  }

  public onEdit() {
    const horse = {
      id: this.horseCopy.id,
      name: this.horseCopy.name,
      description: this.horseCopy.description,
      birthday: this.horseCopy.birthday,
      gender: this.horseCopy.gender,
      sport: this.horseCopy.sport,
      mother: this.horseCopy.mother,
      father: this.horseCopy.father
    };
    this.editHorse.emit(horse);
    this.modalClose.nativeElement.click();
  }
  public copyHorse(){
    this.horseCopy = JSON.parse(JSON.stringify(this.horse));
  }
  public getDataTarget(){
    return '#editHorse' + this.horse.id;
  }
  public getId(){
    return 'editHorse' + this.horse.id;
  }

  public onDelete(horse) {
    this.deleteHorse.emit(horse);
  }
}
