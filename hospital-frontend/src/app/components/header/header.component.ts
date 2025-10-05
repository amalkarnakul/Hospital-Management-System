import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { SupabaseService } from '../../services/supabase.service';
import { User } from '@supabase/supabase-js';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent implements OnInit {
  currentUser: User | null = null;

  constructor(private supabaseService: SupabaseService, private router: Router) {}

  ngOnInit() {
    this.supabaseService.currentUser$.subscribe(user => {
      this.currentUser = user;
    });
  }

  getUserInitials(): string {
    const fullName = this.currentUser?.user_metadata?.['full_name'] || this.currentUser?.email;
    if (!fullName) return 'U';
    
    if (fullName.includes('@')) {
      // If it's an email, use first letter
      return fullName[0].toUpperCase();
    }
    
    return fullName
      .split(' ')
      .map((name: string) => name[0])
      .join('')
      .toUpperCase()
      .substring(0, 2);
  }

  getUserName(): string {
    return this.currentUser?.user_metadata?.['full_name'] || 
           this.currentUser?.email?.split('@')[0] || 
           'User';
  }

  getUserRole(): string {
    return this.currentUser?.user_metadata?.['role'] || 'USER';
  }

  async logout() {
    await this.supabaseService.signOut();
    this.router.navigate(['/login']);
  }
}
