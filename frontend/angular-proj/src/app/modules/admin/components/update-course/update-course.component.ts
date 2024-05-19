import { Component } from '@angular/core';
import { AdminService } from '../../services/admin.service';
import { ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NzMessageService } from 'ng-zorro-antd/message';
import { Router } from '@angular/router';

@Component({
  selector: 'app-update-course',
  templateUrl: './update-course.component.html',
  styleUrls: ['./update-course.component.scss'],
})
export class UpdateCourseComponent {
  courseTitle: string = this.activatedRoute.snapshot.params['title'];
  updateForm!: FormGroup;

  constructor(
    private adminService: AdminService,
    private activatedRoute: ActivatedRoute,
    private fb: FormBuilder,
    private message: NzMessageService,
    private router: Router
  ) {}

  ngOnInit() {
    this.updateForm = this.fb.group({
      title: ['', Validators.required],
      description: ['', Validators.required],
      rating: [
        '',
        [Validators.required, Validators.min(1), Validators.max(10)],
      ],
      assignments: ['', Validators.required],
      level: ['', Validators.required],
    });
    this.getCourseByTitle();
  }

  updateCourse() {
    // const formData: FormData = new FormData();
    // formData.append('title', this.updateForm.get('title')?.value);
    // formData.append('description', this.updateForm.get('description')?.value);
    // formData.append('level', this.updateForm.get('level')?.value);
    // formData.append('assignments', this.updateForm.get('assignments')?.value);
    // formData.append('rating', this.updateForm.get('rating')?.value);
    // console.log(formData);

    const courseData = {
      // title: this.updateForm.get('title')?.value,
      description: this.updateForm.get('description')?.value,
      rating: this.updateForm.get('rating')?.value,
      assignments: this.updateForm.get('assignments')?.value,
      level: this.updateForm.get('level')?.value,
    };
    console.log(courseData);

    this.adminService.updateCourse(this.courseTitle, courseData).subscribe(
      (res) => {
        this.message.success('Course updated successfully', {
          nzDuration: 5000,
        });
        this.router.navigateByUrl('/admin/dashboard');
        console.log(res);
      },
      (error) => {
        this.message.error('Error while updating course', { nzDuration: 5000 });
        console.error('Error:', error);
      }
    );
  }

  getCourseByTitle() {
    this.adminService.getCourseByTitle(this.courseTitle).subscribe(
      (res) => {
        // console.log(res);
        const courseDto = res;
        console.log(courseDto);
        this.updateForm.patchValue(courseDto);
      },
      (error) => {
        console.error('Error fetching course:', error);
      }
    );
  }
}
