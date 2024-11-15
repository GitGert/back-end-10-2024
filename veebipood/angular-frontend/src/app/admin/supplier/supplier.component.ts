import { Component } from '@angular/core';
import { SupplierService } from '../../services/supplier.service';

@Component({
  selector: 'app-supplier',
  standalone: true,
  imports: [],
  templateUrl: './supplier.component.html',
  styleUrl: './supplier.component.css'
})
export class SupplierComponent {
  products: any[] = [];

  constructor (private supplierService : SupplierService
  ) {} // import for TS

  
  ngOnInit(): void {

    this.supplierService.getSuplierPorducts().subscribe(res => {
      this.products = res;
    })
  }


}
