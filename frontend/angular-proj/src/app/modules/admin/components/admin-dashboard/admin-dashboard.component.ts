import { Component, OnInit } from '@angular/core';
import { AdminService } from '../../services/admin.service';
import { NzMessageService } from 'ng-zorro-antd/message';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.scss'],
})
export class AdminDashboardComponent implements OnInit {
  courses: any[] = []; // Changed from 'course' to 'courses' and initialize as an array

  constructor(
    private adminService: AdminService,
    private message: NzMessageService
  ) {}

  ngOnInit() {
    this.getAllCourses();
  }

  getAllCourses() {
    this.adminService.getAllCourses().subscribe((res) => {
      this.courses = res; // Directly assign the response to 'courses'
    });
  }

  deleteCourse(title: string) {
    console.log(title);
    this.adminService.deleteCourse(title).subscribe((res) => {
      this.getAllCourses();
      this.message.success('Course delete successfully', { nzDuration: 5000 });
    });
  }
}
