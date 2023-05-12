import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { NewsComponent } from './components/news/news.component';
import { ProfileComponent } from './components/profile/profile.component';
import { CardsComponent } from "./components/cards/cards.component";
import {DebitCardComponent} from "./components/debit-card/debit-card.component";
import {AuthGuard} from "./guard/auth.guard";
import {CreditCardComponent} from "./components/credit-card/credit-card.component";
import {JuniorCardComponent} from "./components/junior-card/junior-card.component";
import {TransfersComponent} from "./components/transfers/transfers.component";
import {AdminComponent} from "./components/admin/admin.component";

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'news', component: NewsComponent },
  { path: 'profile', component: ProfileComponent, canActivate: [AuthGuard] },
  { path: 'cards', component: CardsComponent },
  { path: 'transfers', component: TransfersComponent, canActivate: [AuthGuard] },
  { path: 'create-debit-card', component: DebitCardComponent, canActivate: [AuthGuard] },
  { path: 'create-credit-card', component: CreditCardComponent, canActivate: [AuthGuard] },
  { path: 'create-junior-card', component: JuniorCardComponent, canActivate: [AuthGuard] },
  { path: 'admin', component: AdminComponent, canActivate: [AuthGuard] }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
