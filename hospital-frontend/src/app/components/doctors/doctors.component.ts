import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HospitalService } from '../../services/hospital.service';

@Component({
  selector: 'app-doctors',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './doctors.component.html',
  styleUrl: './doctors.component.css'
})
export class DoctorsComponent {
  doctors: any[] = [];
  loading = false;
  error: string | null = null;

  constructor(private hospitalService: HospitalService) {}

  ngOnInit() {
    this.loading = true;
    this.hospitalService.getDoctors().subscribe({
      next: (data) => { this.doctors = data; this.loading = false; },
      error: (err) => { this.error = 'Failed to load doctors'; this.loading = false; console.error(err); }
    });
  }
}
