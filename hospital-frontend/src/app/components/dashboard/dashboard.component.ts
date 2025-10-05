import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HospitalService } from '../../services/hospital.service';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent {
  data: any[] = [];
  loading = false;
  error: string | null = null;

  constructor(private hospitalService: HospitalService) {}

  ngOnInit() {
    this.loading = true;
    this.hospitalService.getDashboardData().subscribe({
      next: (d) => { this.data = d; this.loading = false; },
      error: (err) => { this.error = 'Failed to load dashboard'; this.loading = false; console.error(err); }
    });
  }

  get uniqueDoctorsCount(): number {
    if (!this.data) return 0;
    const doctorIds = this.data.map(d => d.doctor?.id).filter(id => id);
    return new Set(doctorIds).size;
  }
}
