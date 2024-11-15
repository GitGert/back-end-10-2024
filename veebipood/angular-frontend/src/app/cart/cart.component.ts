import { Component } from '@angular/core';
import { OrderRow } from '../Models/OrderRow';
import { FormsModule, NgModel } from '@angular/forms';
import { OrderService } from '../services/order.service';
import { Order } from '../Models/Order';

@Component({
  selector: 'app-cart',
  standalone: true,
  imports: [FormsModule], //import for HTML
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.css'
})
export class CartComponent {
  cart :OrderRow[] = [];
  view = "cart";
  email = "";

  ngOnInit(): void {
    //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
    //Add 'implements OnInit' to the class.
    this.cart = JSON.parse(localStorage.getItem("cart") || "[]")

        
  }

  constructor (private orderService : OrderService) {} // import for TS

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

  changeView(view :string){
    this.view = view
  }


  sendOrderToBE(){
    const order : Order = {
      person : {email : this.email},
      orderRows : this.cart
    }
    if (sessionStorage.getItem("token") === null){
      return
    }

    this.orderService.saveOrder(order).subscribe()
  }
}
