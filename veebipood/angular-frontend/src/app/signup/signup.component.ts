import { Component } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { AuthService } from '../services/auth.service';
import { Person } from '../Models/Person';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css'
})
export class SignupComponent {
  message = ""
  

  constructor(private authService : AuthService,
    private router : Router
  ){}


  
  signup(form :NgForm){
    const email = form.value.email
    // if (email === ""){
    //   this.message = "Email cannot be empty"
    //   return
    // }

    const password = form.value.password
    // if (password === ""){
    //   this.message = "password cannot be empty"
    //   return
    // }
    const firstName = form.value.firstName
    // if (firstName === ""){
    //   this.message = "firstName cannot be empty"
    //   return
    // }
    const LastName = form.value.lastName
    // if (LastName === ""){
    //   this.message = "LastName cannot be empty"
    //   return
    // }

    const person : Person = new Person(email, password, firstName, LastName)

    this.authService.signup(person).subscribe(res => {
      sessionStorage.setItem("token", res.token)
      sessionStorage.setItem("expiration", new Date(res.expiration).getTime().toString())
      
      this.authService.loggedInSubject.next(true)
      this.authService.adminSubject.next(true)
      this.router.navigateByUrl("/")
    },
    error =>{
      this.message = error.error.name
    }
  )
  }
}
