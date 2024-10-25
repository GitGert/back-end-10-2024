import { HttpBackend, HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ProductService } from '../services/product.service';
import { Product } from '../Models/Product';
import { OrderRow } from '../Models/OrderRow';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-homepage',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './homepage.component.html',
  styleUrl: './homepage.component.css'
})
export class HomepageComponent implements OnInit {
  products : Product[] = []

  constructor(private productService : ProductService) {}

  ngOnInit(): void {
    //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
    //Add 'implements OnInit' to the class.

    this.productService.getProducts().subscribe(response => this.products = response)
  }

  addToCart(productClicked : Product) : void{
    const storageCart : OrderRow[] =JSON.parse(localStorage.getItem("cart")|| "[]")
    const index  = storageCart.findIndex(orderRow => orderRow.product.id === productClicked.id)
    if (index !== -1){
      storageCart[index].pcs++
    }else{
      storageCart.push({pcs : 1, product : productClicked})
    }

    localStorage.setItem("cart", JSON.stringify(storageCart))

  }
}
