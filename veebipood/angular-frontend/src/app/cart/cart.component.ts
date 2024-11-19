import { Component } from '@angular/core';
import { OrderRow } from '../Models/OrderRow';
import { FormsModule, NgModel } from '@angular/forms';
import { OrderService } from '../services/order.service';
import { Order } from '../Models/Order';
import { RouterLink } from '@angular/router';
import { ParcelMachineService } from '../services/parcel-machine.service';

@Component({
  selector: 'app-cart',
  standalone: true,
  imports: [FormsModule, RouterLink], //import for HTML
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.css'
})
export class CartComponent {
  cart :OrderRow[] = [];
  isLoggedIn = sessionStorage.getItem("token") !== null;
  // view = "cart";
  // email = "";
  parcelMachines: any[] = [];

  ngOnInit(): void {
    //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
    //Add 'implements OnInit' to the class.
    this.cart = JSON.parse(localStorage.getItem("cart") || "[]")
    this.parcelMachineService.getParcelMachines().subscribe(res => {
      this.parcelMachines = res;
    })
  }

  constructor (private orderService : OrderService,
    private parcelMachineService : ParcelMachineService
  ) {} // import for TS

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

  pay(){
    //TODO: add payment functionality here.
  }

  // changeView(view :string){
  //   this.view = view
  // }


  getPMsByCOuntry(country : string){
    this.parcelMachineService.getParcelMachinesByCountry(country).subscribe(res => {
      this.parcelMachines = res;
    })
  }

  sendOrderToBE(){
    const order : Order = {
      orderRows : this.cart
    }
    if (sessionStorage.getItem("token") === null){
      return
    }

    this.orderService.saveOrder(order).subscribe(res => {
      window.location.href = res.link
    })
  }
}
