import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminRoutingModule } from './admin-routing.module';
import { AdminDashboardComponent } from './components/admin-dashboard/admin-dashboard.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { UpdateCourseComponent } from './components/update-course/update-course.component';

@NgModule({
  declarations: [AdminDashboardComponent, UpdateCourseComponent],
  imports: [CommonModule, AdminRoutingModule, ReactiveFormsModule, FormsModule],
})
export class AdminModule {}
