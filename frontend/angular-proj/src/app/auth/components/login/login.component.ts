import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth/auth.service';
import { StorageService } from '../../services/storage/storage.service';
import { Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit {
  isSpinning: boolean = false;
  loginForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private router: Router,
    private message: NzMessageService
  ) {}

  ngOnInit() {
    this.loginForm = this.fb.group({
      email: [null, [Validators.email, Validators.required]],
      password: [null, [Validators.required]],
    });
  }

  onSubmit() {
    if (this.loginForm.valid) {
      console.log('Form Submitted!', this.loginForm.value);
      this.authService.login(this.loginForm.value).subscribe((res: any) => {
        console.log(res);
        if (res.userEmail !== null) {
          const user = {
            email: res.userEmail,
            userRole: res.userRole,
          };
          StorageService.saveUser(user);
          StorageService.saveToken(res.token);
          if (StorageService.isAdminLoggedIn()) {
            this.router.navigateByUrl('admin/dashboard');
          } else if (StorageService.isUserLoggedIn()) {
            this.router.navigateByUrl('customer/dashboard');
          } else {
            this.message.error('Bad credentials', { nzDuration: 5000 });
          }
        }
      });
    }
  }
}
