import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-auth-page',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './auth-page.component.html',
  styleUrl: './auth-page.component.css'
})
export class AuthPageComponent {
  isLogin = true;
  username = '';
  password = '';
  firstname = '';
  lastname = '';
  email = '';
  phoneNumber = '';
  message = '';
  loading = false;

  constructor(private authService: AuthService, private router: Router) {}

  toggleMode(): void {
    this.isLogin = !this.isLogin;
    this.message = '';
  }

  submit(): void {
    this.loading = true;
    this.message = '';

    if (this.isLogin) {
      this.authService.login({ username: this.username, password: this.password }).subscribe({
        next: (success) => {
          this.loading = false;
          if (success) {
            this.message = 'Signed in successfully.';
            this.router.navigateByUrl('/');
          } else {
            this.message = 'Login failed. Check your credentials.';
          }
        },
        error: () => {
          this.loading = false;
          this.message = 'Unable to sign in right now.';
        }
      });
      return;
    }

    this.authService.signup({
      username: this.username,
      password: this.password,
      firstname: this.firstname,
      lastname: this.lastname,
      email: this.email,
      phoneNumber: this.phoneNumber
    }).subscribe({
      next: (success) => {
        this.loading = false;
        this.message = success ? 'Account created. You can sign in now.' : 'Signup failed.';
      },
      error: () => {
        this.loading = false;
        this.message = 'Unable to create account right now.';
      }
    });
  }
}
