import { Component } from '@angular/core';
import { StorageService } from './auth/services/storage/storage.service';
import { every } from 'rxjs';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
})
export class AppComponent {
  title = 'angular-proj';

  isCustomerLoggedIn: boolean = StorageService.isUserLoggedIn();
  isAdminLoggedIn: boolean = StorageService.isAdminLoggedIn();

  constructor(private router: Router) {}

  ngOnInit() {
    this.router.events.subscribe((event) => {
      if (event.constructor.name === 'NavigationEnd') {
        this.isAdminLoggedIn = StorageService.isAdminLoggedIn();
        this.isCustomerLoggedIn = StorageService.isUserLoggedIn();
      }
    });
  }

  logout() {
    StorageService.logout();
    this.router.navigateByUrl('/login');
  }
}
