import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { StorageService } from 'src/app/auth/services/storage/storage.service';

const BASE_URL = 'http://localhost:8080';

@Injectable({
  providedIn: 'root',
})
export class AdminService {
  constructor(private http: HttpClient) {}

  postCourse(course: any): Observable<any> {
    return this.http.post(BASE_URL + '/courses/addCourse', course, {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      }),
    });
  }

  getAllCourses(): Observable<any> {
    return this.http.get(BASE_URL + '/courses/findAll', {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      }),
    });
  }

  getCourseByTitle(title: string): Observable<any> {
    // Correct endpoint and use template literals to include ID
    return this.http.get(`${BASE_URL}/courses/courseByTitle?id=${title}`, {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      }),
    });
  }

  updateCourse(title: string, courseDto: any): Observable<any> {
    return this.http.put(
      `${BASE_URL}/courses/updateAll?title=${title}`,
      courseDto,
      {
        headers: new HttpHeaders({
          'Content-Type': 'application/json',
        }),
      }
    );
  }

  deleteCourse(title: string): Observable<any> {
    return this.http.delete(`${BASE_URL}/courses/delete?id=${title}`, {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      }),
    });
  }
}
