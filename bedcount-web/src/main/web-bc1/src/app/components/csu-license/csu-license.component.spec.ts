import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CsuLicenseComponent } from './csu-license.component';

describe('CsuLicenseComponent', () => {
  let component: CsuLicenseComponent;
  let fixture: ComponentFixture<CsuLicenseComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CsuLicenseComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CsuLicenseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
