import { HttpBackend, HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ProductService } from '../services/product.service';
import { Product } from '../Models/Product';
import { OrderRow } from '../Models/OrderRow';
import { RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-homepage',
  standalone: true,
  imports: [RouterLink, FormsModule],
  templateUrl: './homepage.component.html',
  styleUrl: './homepage.component.css'
})
export class HomepageComponent implements OnInit {
  products : Product[] = []
  search : string = ""
  page : number = 0;
  totalPages = 0;
  totalElements :number = 0;

  constructor(private productService : ProductService) {}

  ngOnInit(): void {
    //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
    //Add 'implements OnInit' to the class.

    this.fetchProducts()
  }

  fetchProducts(){
    this.productService.getProducts(this.page, 3).subscribe(response => {
      this.products = response.content
      this.totalPages = response.totalPages
      this.totalElements = response.totalElements
    })
  }

  previousPage(){
    this.page--;
    this.fetchProducts()
  }
  nextPage(){
    this.page++;
    this.fetchProducts()
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

  searchFormProducts(){
    this.productService.getProductByName(this.search).subscribe(response => 
      this.products = response.content
    )
  }
}
