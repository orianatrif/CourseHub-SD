import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PostCourseComponent } from './post-course.component';

describe('PostCourseComponent', () => {
  let component: PostCourseComponent;
  let fixture: ComponentFixture<PostCourseComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PostCourseComponent]
    });
    fixture = TestBed.createComponent(PostCourseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
