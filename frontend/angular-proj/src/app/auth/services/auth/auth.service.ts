import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const BASE_URL = 'http://localhost:8080'; // Correct this line

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private headers = new HttpHeaders({
    'Content-Type': 'application/json',
    //Authorization:
    //  'Bearer eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJPcmlhbmEiLCJpYXQiOjE3MTU4ODUzMzMsImV4cCI6MTcxNTk3MTczM30.-dVz3VJelxYGuse5H7zgbVshhPkxutYld-hooFr_mxwIaVSxyP0y0db2t8V-1s_O', // Uncomment and replace if needed
  });

  constructor(private http: HttpClient) {}

  login(loginRequest: any): Observable<any> {
    return this.http.post(BASE_URL + '/users/login', loginRequest);
  }

  register(signupRequest: any): Observable<any> {
    return this.http.post(BASE_URL + '/users/signup', signupRequest);
  }

  // logIn(userEmail: string, userPassword: string):Observable<any>{
  //   const body = {
  //     userEmail: userEmail,
  //     userPassword: userPassword,
  //   };
  //   return this.http.post<any>(`${BASE_URL}/login`, body).pipe(
  //     catchError((error) => {
  //       console.error('Error logging in:', error);
  //       throw error;
  //     })
  //   );
  // }
}
