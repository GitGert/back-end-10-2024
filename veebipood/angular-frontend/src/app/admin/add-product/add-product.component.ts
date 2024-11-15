import { Component } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { Product } from '../../Models/Product';
import { Nutrients } from '../../Models/Nutrients';
import { Category } from '../../Models/Category';
import { ProductService } from '../../services/product.service';

@Component({
  selector: 'app-add-product',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './add-product.component.html',
  styleUrl: './add-product.component.css'
})
export class AddProductComponent {
  errorMessage = ""

  constructor(private productService : ProductService){}
  addProduct(form : NgForm){
    console.log(form)
    console.log(form.value)

    const val = form.value

    const product = new Product(
      val.id,
      val.name,
      val.price,
      val.image,
      val.active,
      val.description,
      new Nutrients(
        val.protein,
        val.carbohydrate,
        val.fat
      ),
      new Category(
        val.category,
        ""
      ),
    )

    this.productService.addProduct(product).subscribe(
      ()=>{},
      error => {this.errorMessage = error.error.name}
    );

  }
}
