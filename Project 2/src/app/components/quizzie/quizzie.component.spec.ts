import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { QuizzieComponent } from './quizzie.component';

describe('QuizzieComponent', () => {
  let component: QuizzieComponent;
  let fixture: ComponentFixture<QuizzieComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ QuizzieComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(QuizzieComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
