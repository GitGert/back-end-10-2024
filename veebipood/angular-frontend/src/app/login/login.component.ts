import { Component } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  

  constructor(private authService : AuthService
    ,private router  : Router
  ){}
  
  login(form :NgForm){
    const email = form.value.email
    const password = form.value.password
    this.authService.login(email, password).subscribe(res => {
      sessionStorage.setItem("token", res.token)
      sessionStorage.setItem("expiration", new Date(res.expiration).getTime().toString())
      this.authService.loggedInSubject.next(true)
      this.authService.adminSubject.next(true)
      this.router.navigateByUrl("/")
    })
  }
}
