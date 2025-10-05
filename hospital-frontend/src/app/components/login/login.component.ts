import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { SupabaseService } from '../../services/supabase.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  loginData = {
    email: '',
    password: ''
  };
  
  isLoading = false;
  error = '';
  showRegister = false;
  showForgotPassword = false;
  
  registerData = {
    email: '',
    password: '',
    confirmPassword: '',
    fullName: '',
    role: 'DOCTOR'
  };

  forgotPasswordEmail = '';

  constructor(private supabaseService: SupabaseService, private router: Router) {}

  async onLogin() {
    if (!this.loginData.email || !this.loginData.password) {
      this.error = 'Please fill in all fields';
      return;
    }

    this.isLoading = true;
    this.error = '';

    try {
      const { data, error } = await this.supabaseService.signIn(this.loginData.email, this.loginData.password);
      
      this.isLoading = false;
      
      if (error) {
        this.error = error.message;
      } else if (data.user) {
        this.router.navigate(['/']);
      }
    } catch (err) {
      this.isLoading = false;
      this.error = 'Login failed. Please try again.';
      console.error('Login error:', err);
    }
  }

  async onRegister() {
    if (!this.registerData.email || !this.registerData.password || !this.registerData.fullName) {
      this.error = 'Please fill in all fields';
      return;
    }

    if (this.registerData.password !== this.registerData.confirmPassword) {
      this.error = 'Passwords do not match';
      return;
    }

    if (this.registerData.password.length < 6) {
      this.error = 'Password must be at least 6 characters long';
      return;
    }

    this.isLoading = true;
    this.error = '';

    try {
      const { data, error } = await this.supabaseService.signUp(
        this.registerData.email, 
        this.registerData.password,
        {
          full_name: this.registerData.fullName,
          role: this.registerData.role
        }
      );
      
      this.isLoading = false;
      
      if (error) {
        this.error = error.message;
      } else {
        this.showRegister = false;
        this.error = '';
        // Show success message
        alert('Registration successful! Please check your email to verify your account.');
        // Auto-login after registration
        this.loginData.email = this.registerData.email;
        this.loginData.password = this.registerData.password;
      }
    } catch (err) {
      this.isLoading = false;
      this.error = 'Registration failed. Please try again.';
      console.error('Registration error:', err);
    }
  }

  toggleForm() {
    this.showRegister = !this.showRegister;
    this.showForgotPassword = false;
    this.error = '';
  }

  toggleForgotPassword() {
    this.showForgotPassword = !this.showForgotPassword;
    this.showRegister = false;
    this.error = '';
  }

  async onForgotPassword() {
    if (!this.forgotPasswordEmail) {
      this.error = 'Please enter your email address';
      return;
    }

    this.isLoading = true;
    this.error = '';

    try {
      const { error } = await this.supabaseService.resetPassword(this.forgotPasswordEmail);
      
      this.isLoading = false;
      
      if (error) {
        this.error = error.message;
      } else {
        alert('Password reset email sent! Please check your inbox.');
        this.showForgotPassword = false;
      }
    } catch (err) {
      this.isLoading = false;
      this.error = 'Failed to send reset email. Please try again.';
      console.error('Password reset error:', err);
    }
  }

  // Demo login methods (for testing)
  loginAsDemo() {
    this.loginData = { email: 'demo@hospital.com', password: 'demo123' };
    this.onLogin();
  }
}