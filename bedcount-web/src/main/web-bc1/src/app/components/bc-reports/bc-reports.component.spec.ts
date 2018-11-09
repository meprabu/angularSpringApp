import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BcReportsComponent } from './bc-reports.component';

describe('BcReportsComponent', () => {
  let component: BcReportsComponent;
  let fixture: ComponentFixture<BcReportsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BcReportsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BcReportsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
