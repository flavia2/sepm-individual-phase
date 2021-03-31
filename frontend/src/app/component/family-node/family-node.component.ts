import {Component, Input, OnInit} from '@angular/core';
import { HorseFamily } from 'src/app/dto/horseFamily';
import {HorseService} from '../../service/horse.service';
import {ActivatedRoute, Router} from '@angular/router';

@Component({
  selector: 'app-family-node',
  templateUrl: './family-node.component.html',
  styleUrls: ['./family-node.component.scss']
})
export class FamilyNodeComponent implements OnInit {
  @Input() family: HorseFamily[];
  show: boolean;
  deleteSuccess = false;
  delete = false;
  deletedHorseName: string;

  constructor(private horseService: HorseService, public router: Router,
              private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.show = true;
  }

  public toggleParents(){
    this.show = !this.show;
  }
  public onDelete(horse) {
    this.horseService.deleteHorse(horse).subscribe();

    this.deletedHorseName = horse.name;
    this.deleteSuccess = true;
    this.delete = true;
    setTimeout(() => {
      this.deleteSuccess = false;
      if (this.deletedRoot(horse.id)){
        this.router.navigate(['/horses']);
      }
    }, 3000);
  }

  private deletedRoot(id){
    let rootID;
    this.route.params.subscribe(
      params => {
         rootID = +params.id;
      }
    );
    return rootID === id;
  }
}
