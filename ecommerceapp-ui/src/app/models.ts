export interface Product {
  id: number;
  name: string;
  price: number;
  description: string;
  count: number;
  imageUrl?: string;
}

export interface UserProfile {
  username: string;
  firstname?: string;
  lastname?: string;
  email?: string;
  phoneNumber?: string;
  role?: string;
}

export interface CartItem {
  product: Product;
  quantity: number;
}

export interface OrderItem {
  productName?: string;
  quantity?: number;
  price?: number;
}

export interface OrderSummary {
  orderId: number;
  status: string;
  amount: number;
  orderItems?: OrderItem[];
}
