import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BcRootComponent } from './bc-root.component';

describe('BcRootComponent', () => {
  let component: BcRootComponent;
  let fixture: ComponentFixture<BcRootComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BcRootComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BcRootComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
