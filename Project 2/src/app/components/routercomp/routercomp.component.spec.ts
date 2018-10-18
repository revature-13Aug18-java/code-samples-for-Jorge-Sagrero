import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RoutercompComponent } from './routercomp.component';

describe('RoutercompComponent', () => {
  let component: RoutercompComponent;
  let fixture: ComponentFixture<RoutercompComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RoutercompComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RoutercompComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
