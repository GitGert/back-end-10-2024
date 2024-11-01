import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from '../Models/Product';
import { Productpage } from '../Models/ProductPage';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http : HttpClient) { 
    
  }

  getProducts(pageNr : number, pageSize : number) : Observable<Productpage> {
    return     this.http.get<Productpage>("http://localhost:8080/products", {params : {page : pageNr, size : pageSize}})
  }
  getProduct(productId : number) : Observable<Product> {
    return     this.http.get<Product>("http://localhost:8080/product", {params : {id : productId}})
  }

  getProductByName(search : string) : Observable<Productpage> {
    return this.http.get<Productpage>("http://localhost:8080/find-by-name", {params : {name : search}})
  }
  addProduct(product : Product) : Observable<void> {
    return this.http.post<void>("http://localhost:8080/ product", product)
  }
}
