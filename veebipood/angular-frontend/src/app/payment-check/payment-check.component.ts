import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { OrderService } from '../services/order.service';

@Component({
  selector: 'app-payment-check',
  standalone: true,
  imports: [],
  templateUrl: './payment-check.component.html',
  styleUrl: './payment-check.component.css'
})
export class PaymentCheckComponent {
  //    
  //payment reference has the info about if the order is payed or not.

  paidStatus = ""

  constructor(private route: ActivatedRoute,
    private orderService: OrderService
  ){

  }


  ngOnInit(): void {
    this.route.queryParams.subscribe(res => {
      const paymentReference =(res["payment_reference"])
      this.orderService.checkPayment(paymentReference).subscribe(res => {
        this.paidStatus = res.status;
      })
    })
  }

}
