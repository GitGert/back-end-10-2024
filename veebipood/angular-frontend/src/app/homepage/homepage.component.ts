import { HttpBackend, HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ProductService } from '../services/product.service';
import { Product } from '../Models/Product';
import { OrderRow } from '../Models/OrderRow';
import { RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { PaginationComponent } from './pagination/pagination.component';
import { SearchBarComponent } from "./search-bar/search-bar.component";
import { NameShortenerPipe } from '../pipes/name-shortener.pipe';

@Component({
  selector: 'app-homepage',
  standalone: true,
  imports: [RouterLink, FormsModule, PaginationComponent, SearchBarComponent, NameShortenerPipe],
  templateUrl: './homepage.component.html',
  styleUrl: './homepage.component.css'
})
export class HomepageComponent implements OnInit {
  products : Product[] = []
  search = ""
  totalPages = 0;
  totalElements = 0;
  nameLength = 5

  constructor(private productService : ProductService) {}

  ngOnInit(): void {
    //Called after the constructor, initializing input properties, and the first call to ngOnChanges.
    //Add 'implements OnInit' to the class.

    this.fetchProducts(0)
  }

  fetchProducts(page : number){
    this.productService.getProducts(page, 3).subscribe(response => {
      this.products = response.content
      this.totalPages = response.totalPages
      this.totalElements = response.totalElements
    })
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

  fetchProductsByName(search : string){
    this.productService.getProductByName(search, 0, 3).subscribe(response => 
      this.products = response.content
    )
  }
}
