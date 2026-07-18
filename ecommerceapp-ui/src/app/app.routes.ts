import { Routes } from '@angular/router';
import { HomePageComponent } from './components/home-page/home-page.component';
import { AuthPageComponent } from './components/auth-page/auth-page.component';
import { CartPageComponent } from './components/cart-page/cart-page.component';
import { OrdersPageComponent } from './components/orders-page/orders-page.component';
import { ProfilePageComponent } from './components/profile-page/profile-page.component';

export const routes: Routes = [
  { path: '', component: HomePageComponent },
  { path: 'auth', component: AuthPageComponent },
  { path: 'cart', component: CartPageComponent },
  { path: 'orders', component: OrdersPageComponent },
  { path: 'profile', component: ProfilePageComponent },
  { path: '**', redirectTo: '' }
];
