import { Component, OnInit } from '@angular/core';
import { finalize } from 'rxjs/operators';
import {
  FormBuilder,
  FormGroup,
  Validators,
  AbstractControl,
  ValidationErrors,
} from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/core/services/auth.service';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss'],
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterLink],
})
export class SignupComponent implements OnInit {
  signupForm!: FormGroup;
  isLoading = false;
  error: string | null = null;

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private router: Router,
  ) {}

  ngOnInit(): void {
    this.initializeForm();
  }

  initializeForm(): void {
    this.signupForm = this.formBuilder.group(
      {
        firstName: ['', [Validators.required, Validators.minLength(2)]],
        lastName: ['', [Validators.required, Validators.minLength(2)]],
        email: ['', [Validators.required, Validators.email]],
        phone: ['', [Validators.required, Validators.pattern(/^(\+373|0)\d{8}$/)]],
        userType: ['', Validators.required],
        password: ['', [Validators.required, Validators.minLength(8)]],
        confirmPassword: ['', Validators.required],
        agreeTerms: [false, Validators.requiredTrue],
      },
      { validators: this.passwordMatchValidator },
    );
  }

  passwordMatchValidator(control: AbstractControl): ValidationErrors | null {
    const password = control.get('password');
    const confirmPassword = control.get('confirmPassword');

    if (!password || !confirmPassword) {
      return null;
    }

    if (confirmPassword.errors && !confirmPassword.errors['passwordMismatch']) {
      return null;
    }

    if (password.value !== confirmPassword.value) {
      confirmPassword.setErrors({ passwordMismatch: true });
      return { passwordMismatch: true };
    } else {
      confirmPassword.setErrors(null);
      return null;
    }
  }

  onSubmit(): void {
    if (this.signupForm.invalid) return;

    this.isLoading = true;
    this.error = null;

    const signupRequest = {
      firstName: this.signupForm.get('firstName')?.value,
      lastName: this.signupForm.get('lastName')?.value,
      email: this.signupForm.get('email')?.value,
      phone: this.signupForm.get('phone')?.value,
      password: this.signupForm.get('password')?.value,
      role: this.signupForm.get('userType')?.value,
    };

    this.authService
      .signup(signupRequest)
      .pipe(
        finalize(() => {
          this.isLoading = false;
        }),
      )
      .subscribe({
        next: () => {
          this.router.navigate([
            signupRequest.role === 'DRIVER' ? '/driver/main' : '/passenger/main',
          ]);
        },
        error: (error) => {
          this.error =
            typeof error?.error === 'string'
              ? error.error
              : error.error?.message || 'Signup failed. Please try again.';
        },
      });
  }

  isFieldInvalid(fieldName: string): boolean {
    const field = this.signupForm.get(fieldName);
    return !!(field && field.invalid && (field.dirty || field.touched));
  }

  getFieldError(fieldName: string): string {
    const field = this.signupForm.get(fieldName);
    if (field?.hasError('required')) {
      return `${fieldName.charAt(0).toUpperCase() + fieldName.slice(1)} is required`;
    }
    if (field?.hasError('email')) {
      return 'Please enter a valid email address';
    }
    if (field?.hasError('minlength')) {
      const minLength = field.getError('minlength')?.requiredLength;
      return `${fieldName} must be at least ${minLength} characters`;
    }
    if (field?.hasError('pattern')) {
      return 'Please enter a valid phone number';
    }
    if (field?.hasError('passwordMismatch')) {
      return 'Passwords do not match';
    }
    if (field?.hasError('requiredTrue')) {
      return 'You must agree to the terms';
    }
    return '';
  }
}
