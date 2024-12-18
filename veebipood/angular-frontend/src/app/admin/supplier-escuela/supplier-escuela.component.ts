import { Component } from '@angular/core';
import { SupplierService } from '../../services/supplier.service';

@Component({
  selector: 'app-supplier-escuela',
  standalone: true,
  imports: [],
  templateUrl: './supplier-escuela.component.html',
  styleUrl: './supplier-escuela.component.css'
})
export class SupplierEscuelaComponent {
  products: any[] = [];

  constructor (private supplierService : SupplierService
  ) {} // import for TS

  
  ngOnInit(): void {

    this.supplierService.getSupplierProductsEscuela().subscribe(res => {
      this.products = res;
    })
  }
}