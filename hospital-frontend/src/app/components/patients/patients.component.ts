import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HospitalService } from '../../services/hospital.service';

@Component({
  selector: 'app-patients',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './patients.component.html',
  styleUrl: './patients.component.css'
})
export class PatientsComponent {
  patients: any[] = [];
  loading = false;
  error: string | null = null;

  constructor(private hospitalService: HospitalService) {}

  ngOnInit() {
    this.loading = true;
    this.hospitalService.getPatients().subscribe({
      next: (data) => { this.patients = data; this.loading = false; },
      error: (err) => { this.error = 'Failed to load patients'; this.loading = false; console.error(err); }
    });
  }
}
