import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

const BASE_URL = environment.apiBase;

@Injectable({ providedIn: 'root' })
export class HospitalService {
  constructor(private http: HttpClient) {}

  getPatients(): Observable<any[]> {
    return this.http.get<any[]>(`${BASE_URL}/hospital/patients`);
  }

  getDoctors(): Observable<any[]> {
    return this.http.get<any[]>(`${BASE_URL}/hospital/doctors`);
  }

  getAppointments(): Observable<any[]> {
    return this.http.get<any[]>(`${BASE_URL}/hospital/appointments`);
  }

  getDashboardData(): Observable<any[]> {
    return this.http.get<any[]>(`${BASE_URL}/hospital/dashboard-data`);
  }
}


