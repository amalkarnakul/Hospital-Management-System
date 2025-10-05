import { Routes } from '@angular/router';
import { AuthGuard } from './guards/auth.guard';

export const routes: Routes = [
  { 
    path: 'login', 
    loadComponent: () => import('./components/login/login.component').then(m => m.LoginComponent)
  },
  { 
    path: '', 
    loadComponent: () => import('./components/dashboard/dashboard.component').then(m => m.DashboardComponent),
    canActivate: [AuthGuard] 
  },
  { 
    path: 'patients', 
    loadComponent: () => import('./components/patients/patients.component').then(m => m.PatientsComponent),
    canActivate: [AuthGuard] 
  },
  { 
    path: 'doctors', 
    loadComponent: () => import('./components/doctors/doctors.component').then(m => m.DoctorsComponent),
    canActivate: [AuthGuard] 
  },
  { 
    path: 'appointments', 
    loadComponent: () => import('./components/appointments/appointments.component').then(m => m.AppointmentsComponent),
    canActivate: [AuthGuard] 
  },
  { path: '**', redirectTo: '/login' }
];
