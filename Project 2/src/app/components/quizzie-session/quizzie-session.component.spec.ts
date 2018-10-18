import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { QuizzieSessionComponent } from './quizzie-session.component';

describe('QuizzieSessionComponent', () => {
  let component: QuizzieSessionComponent;
  let fixture: ComponentFixture<QuizzieSessionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ QuizzieSessionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(QuizzieSessionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
