import { Injectable } from '@angular/core';
import { createClient, SupabaseClient, User } from '@supabase/supabase-js';
import { BehaviorSubject } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class SupabaseService {
  private supabase: SupabaseClient;
  private currentUserSubject = new BehaviorSubject<User | null>(null);
  public currentUser$ = this.currentUserSubject.asObservable();

  constructor() {
    this.supabase = createClient(environment.supabase.url, environment.supabase.anonKey);
    
    // Check for existing session
    this.supabase.auth.getSession().then(({ data: { session } }) => {
      this.currentUserSubject.next(session?.user ?? null);
    });

    // Listen for auth changes
    this.supabase.auth.onAuthStateChange((event, session) => {
      this.currentUserSubject.next(session?.user ?? null);
    });
  }

  // Sign up with email and password
  async signUp(email: string, password: string, userData?: any) {
    const { data, error } = await this.supabase.auth.signUp({
      email,
      password,
      options: {
        data: userData // Additional user metadata
      }
    });
    return { data, error };
  }

  // Sign in with email and password
  async signIn(email: string, password: string) {
    const { data, error } = await this.supabase.auth.signInWithPassword({
      email,
      password
    });
    return { data, error };
  }

  // Sign out
  async signOut() {
    const { error } = await this.supabase.auth.signOut();
    return { error };
  }

  // Get current user
  get currentUser(): User | null {
    return this.currentUserSubject.value;
  }

  // Check if user is logged in
  get isLoggedIn(): boolean {
    return this.currentUser !== null;
  }

  // Reset password
  async resetPassword(email: string) {
    const { data, error } = await this.supabase.auth.resetPasswordForEmail(email, {
      redirectTo: `${window.location.origin}/reset-password`
    });
    return { data, error };
  }

  // Update user profile
  async updateProfile(updates: any) {
    const { data, error } = await this.supabase.auth.updateUser({
      data: updates
    });
    return { data, error };
  }

  // Get Supabase client for direct database operations
  get client() {
    return this.supabase;
  }
}