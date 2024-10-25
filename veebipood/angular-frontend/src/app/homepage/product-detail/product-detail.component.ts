import { Component } from '@angular/core';
import { Product } from '../../Models/Product';
import { ProductService } from '../../services/product.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-product-detail',
  standalone: true,
  imports: [],
  templateUrl: './product-detail.component.html',
  styleUrl: './product-detail.component.css'
})
export class ProductDetailComponent {
  product !: Product //null
  
  constructor(private productService : ProductService,
    private route : ActivatedRoute
  ){

  }

  ngOnInit(): void {
    const productId = this.route.snapshot.paramMap.get("id")
    console.log(productId)
    if (!productId){
      return;
    }
      this.productService.getProduct(Number(productId)).subscribe(res => this.product = res)
  }

}
