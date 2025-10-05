import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HospitalService } from '../../services/hospital.service';

@Component({
  selector: 'app-appointments',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './appointments.component.html',
  styleUrl: './appointments.component.css'
})
export class AppointmentsComponent {
  appointments: any[] = [];
  loading = false;
  error: string | null = null;

  constructor(private hospitalService: HospitalService) {}

  ngOnInit() {
    this.loading = true;
    this.hospitalService.getAppointments().subscribe({
      next: (data) => { this.appointments = data; this.loading = false; },
      error: (err) => { this.error = 'Failed to load appointments'; this.loading = false; console.error(err); }
    });
  }
}
