import { HttpClient } from '@angular/common/http';
import { ParseSourceFile } from '@angular/compiler';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, Subject } from 'rxjs';
import { Person } from '../Models/Person';
import { Token } from '../Models/Token';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  loggedInSubject = new BehaviorSubject(sessionStorage.getItem("token") !==null); //tODO: sessionsto
  adminSubject = new Subject();
  

  constructor(private http: HttpClient) {  }


  register(person: Person) : Observable<Token> {
    return this.http.post<Token>("http://localhost:8080/signup", person)
  }

  login(email: string, password : string) : Observable<Token> {
    return this.http.post<Token>("http://localhost:8080/login", {email, password})
  }

  admin() : Observable<boolean> {
    return this.http.get<boolean>("http://localhost:8080/admin", 
      {headers : {
        Authorization : "Bearer " + (sessionStorage.getItem("token")|| "")
      }}
    )
  }

  

  
  signup(person : Person) : Observable<Token> {
    return this.http.post<Token>("http://localhost:8080/signup", person)
  }

}
