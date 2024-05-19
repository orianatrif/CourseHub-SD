import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { NzMessageService } from 'ng-zorro-antd/message';
import { Router } from '@angular/router';
import { AdminService } from 'src/app/modules/admin/services/admin.service';

@Component({
  selector: 'app-post-course',
  templateUrl: './post-course.component.html',
  styleUrls: ['./post-course.component.scss'],
})
export class PostCourseComponent implements OnInit {
  isSpinning: boolean = false;
  postCourseForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private adminService: AdminService,
    private message: NzMessageService,
    private router: Router
  ) {}

  ngOnInit() {
    this.postCourseForm = this.fb.group({
      title: ['', Validators.required],
      description: ['', Validators.required],
      rating: [
        '',
        [Validators.required, Validators.min(1), Validators.max(10)],
      ],
      assignments: ['', Validators.required],
      level: ['', Validators.required],
    });
  }

  postCourse() {
    this.isSpinning = true;
    const courseData = {
      title: this.postCourseForm.get('title')?.value,
      description: this.postCourseForm.get('description')?.value,
      rating: this.postCourseForm.get('rating')?.value,
      assignments: this.postCourseForm.get('assignments')?.value,
      level: this.postCourseForm.get('level')?.value,
    };

    this.adminService.postCourse(courseData).subscribe(
      (res) => {
        this.isSpinning = false;
        this.message.success('Course posted successfully', {
          nzDuration: 5000,
        });
        this.router.navigateByUrl('/admin/dashboard');
        console.log(res);
      },
      (error) => {
        this.isSpinning = false;
        this.message.error('Error while posting course', { nzDuration: 5000 });
        console.error('Error:', error);
      }
    );
  }
}
