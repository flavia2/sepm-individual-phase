import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SportIndividualComponent } from './sport-individual.component';

describe('SportIndividualComponent', () => {
  let component: SportIndividualComponent;
  let fixture: ComponentFixture<SportIndividualComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SportIndividualComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SportIndividualComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
