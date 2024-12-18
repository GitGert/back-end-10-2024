import { Component } from '@angular/core';
import { OrderRow } from '../Models/OrderRow';
import { FormsModule, NgModel } from '@angular/forms';
import { OrderService } from '../services/order.service';
import { Order } from '../Models/Order';
import { RouterLink } from '@angular/router';
import { ParcelMachinesComponent } from "./parcel-machines/parcel-machines.component";
import { PaymentComponent } from './payment/payment.component';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-cart',
  standalone: true,
  imports: [FormsModule, RouterLink, ParcelMachinesComponent, PaymentComponent, DatePipe], //import for HTML
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.css'
})
export class CartComponent {
  date = new Date();
  cart :OrderRow[] = [];
  isLoggedIn = sessionStorage.getItem("token") !== null;
  // view = "cart";
  // email = "";


  ngOnInit(): void {
    //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
    //Add 'implements OnInit' to the class.
    this.cart = JSON.parse(localStorage.getItem("cart") || "[]")
  }


  decreaseQuantity(i : number) {
    this.cart[i].pcs--
    if (this.cart[i].pcs < 1){
      this.cart.splice(i,1)
    }
    localStorage.setItem("cart", JSON.stringify(this.cart))
  }
  increaseQuantity(i : number) {
    this.cart[i].pcs++

    localStorage.setItem("cart", JSON.stringify(this.cart))
  }
  removeFromCart(i : number) {
    this.cart.splice(i,1)
    localStorage.setItem("cart", JSON.stringify(this.cart))
  }

  calculateTotal(){
    let sum = 0;
    this.cart.forEach(orderRow => {
      sum+= orderRow.product.price * orderRow.pcs  
    });
    return sum;
  }

  // pay(){
  //   //TODO: add payment functionality here.
  // }

  // changeView(view :string){
  //   this.view = view
  // }

}
