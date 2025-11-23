import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SlicePipe } from '@angular/common';
import { Product } from '../models/product.model';
import { ProductService } from '../services/product.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-product-list',
  imports: [CommonModule, SlicePipe, FormsModule],
  templateUrl: './product-list.html',
  styleUrl: './product-list.css'
})
export class ProductList {
  loading : boolean= true;
  products : Product[] = [];
  product! : Product;
  isEdit:boolean = false;
  isAdd:boolean = false;

  constructor(private productService: ProductService){

  }

  getProducts():void{
    this.productService.getProducts().subscribe({
      next : (data)=>{
        this.products = data;
        this.loading = false
      }
    })
  }
  
  loadFalse() : void {
    this.loading = false;
  }

  printProduct(product : Product):void{
    console.log(product);
  }

  editTrue(product : Product):void{
    this.isEdit = true;
    this.isAdd = false;
    this.product = product;
  }
  addTrue():void{
    this.isAdd = true;
    this.isEdit = false;
    this.product = {name:"",price:0,count:0,description:""};
  }

  addProduct(product : Product):void{
    this.productService.addProduct(product).subscribe({
      next : (data)=>{
          console.log(data);
      }
    })
    this.isAdd=false;
  }

  updateProduct(product : Product):void{
    this.productService.updateProduct(product).subscribe({
      next : (data)=>{
          console.log(data);
      }
    })
    this.isEdit=false;
  }

}
