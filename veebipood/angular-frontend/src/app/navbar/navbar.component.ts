import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {
  showButton = false;
  loggedIn = false;

  constructor(private authService : AuthService) {
    
  }

  ngOnInit() : void{
    // see subject alustatakse algvaartusega.
    this.authService.loggedInSubject.subscribe(res =>{
      this.loggedIn = res;
    })
    
    // tavalise subjecti .subscribe ei lahe kohe kaima -> laheb kaima ainult nexti abil
    this.showAdmin();
    this.authService.adminSubject.subscribe(res => {
      this.showAdmin();
    })
  }

  showAdmin(){
    this.authService.admin().subscribe(resp => {
      this.showButton = resp;
    })
  }

  logout(){
    sessionStorage.clear()
    this.authService.loggedInSubject.next(false)
    this.authService.adminSubject.next(false)
    // this.showButton
  }
}
