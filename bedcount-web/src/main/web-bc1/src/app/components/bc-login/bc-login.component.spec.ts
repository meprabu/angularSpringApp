import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BcLoginComponent } from './bc-login.component';

describe('BcLoginComponent', () => {
  let component: BcLoginComponent;
  let fixture: ComponentFixture<BcLoginComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BcLoginComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BcLoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
