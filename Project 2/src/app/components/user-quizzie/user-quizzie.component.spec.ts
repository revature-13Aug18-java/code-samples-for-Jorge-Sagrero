import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UserQuizzieComponent } from './user-quizzie.component';

describe('UserQuizzieComponent', () => {
  let component: UserQuizzieComponent;
  let fixture: ComponentFixture<UserQuizzieComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UserQuizzieComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UserQuizzieComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
