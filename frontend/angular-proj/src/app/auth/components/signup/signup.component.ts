import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  Validators,
  AbstractControl,
  ValidationErrors,
} from '@angular/forms';
import { AuthService } from '../../services/auth/auth.service';
import { Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss'],
})
export class SignupComponent implements OnInit {
  signupForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
    private message: NzMessageService,
    private router: Router // Make sure to inject the Router
  ) {}

  ngOnInit() {
    this.signupForm = this.fb.group(
      {
        firstName: ['', Validators.required],
        lastName: ['', Validators.required],
        email: ['', [Validators.email, Validators.required]],
        password: ['', Validators.required],
        checkpassword: ['', Validators.required],
      },
      { validators: this.passwordMatchValidator }
    );
  }

  passwordMatchValidator(form: AbstractControl): ValidationErrors | null {
    const password = form.get('password');
    const checkpassword = form.get('checkpassword');
    if (password && checkpassword && password.value !== checkpassword.value) {
      return { passwordMismatch: true };
    }
    return null;
  }

  register() {
    console.log(this.signupForm.value);
    this.authService.register(this.signupForm.value).subscribe((res) => {
      console.log('Res is', res);
      if (res.id !== null) {
        this.message.success('Sign up successful', { nzDuration: 5000 });
        this.router.navigateByUrl('/login'); // Correct method name here
      } else {
        this.message.error('Sign up failed', { nzDuration: 5000 });
      }
    });
  }
}
