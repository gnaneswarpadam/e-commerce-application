import { Observable } from "rxjs"
import { Product } from "../models/product.model"
import { HttpClient } from "@angular/common/http"
import { Injectable } from "@angular/core";

@Injectable({
  providedIn: 'root',
})
export class ProductService {
    
    constructor(private httpClient : HttpClient){

    }
    private baseUrl = "http://localhost:8080";

    getProducts(): Observable<Product[]>{
        let apiUrl = this.baseUrl+"/getAllProductDetails"
        return this.httpClient.get<Product[]>(apiUrl);
    }

    addProduct(product : Product): Observable<string>{
      let apiUrl = this.baseUrl+"/addProduct"
      return this.httpClient.post<string>(apiUrl, product, { responseType : "text" as "json"});
    }

    updateProduct(product : Product): Observable<string>{
      let apiUrl = this.baseUrl+"/updateProduct"
      return this.httpClient.post<string>(apiUrl, product, { responseType : "text" as "json"});
    }
    
}
