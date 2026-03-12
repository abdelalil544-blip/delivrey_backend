import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { AuthService } from '../../core/auth/auth.service';

@Component({
  standalone: true,
  imports: [FormsModule, RouterLink],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  email = '';
  password = '';
  errorMessage = '';

  constructor(private auth: AuthService) { }

  submit() {
    this.auth.login({
      email: this.email,
      password: this.password
    }).subscribe({
      next: () => {
        // Redirection handled in AuthService tap
      },
      error: (err) => {
        console.error('Login failed', err);
        this.errorMessage = 'Email ou mot de passe incorrect.';
      }
    });
  }
}
