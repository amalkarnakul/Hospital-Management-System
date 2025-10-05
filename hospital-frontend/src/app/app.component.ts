import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet, RouterLink, RouterLinkActive, Router, NavigationEnd } from '@angular/router';
import { HeaderComponent } from './components/header/header.component';
import { SupabaseService } from './services/supabase.service';
import { filter } from 'rxjs/operators';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet, RouterLink, RouterLinkActive, HeaderComponent],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {
  title = 'hospital-frontend';
  isLoginPage = false;
  isLoggedIn = false;

  constructor(private router: Router, private supabaseService: SupabaseService) {}

  ngOnInit() {
    // Track route changes to hide/show header
    this.router.events.pipe(
      filter(event => event instanceof NavigationEnd)
    ).subscribe((event: NavigationEnd) => {
      this.isLoginPage = event.url === '/login';
    });

    // Track authentication status
    this.supabaseService.currentUser$.subscribe(user => {
      this.isLoggedIn = !!user;
    });
  }
}
