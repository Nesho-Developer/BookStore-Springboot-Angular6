import {Component, OnInit} from '@angular/core';
import {LoginService} from '../../services/login.service';
import {Router} from "@angular/router";


@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent implements OnInit {

  private LoggedIn = false;

  constructor(private loginService: LoginService, private router: Router) {
  }

  logout() {
    this.loginService.logout().subscribe(
      data => {
        console.log(data, 'logout!');
        //localStorage.removeItem('xAuthToken');
        location.reload();
      },
      error => {
        console.log(error, 'Ops! logout fail!');

      }
    );
    this.router.navigate(['/']);
  }

  ngOnInit() {
    this.loginService.checkSession().subscribe(
      data => {
        console.log(data, 'success!');
        this.LoggedIn = true;
      },
      error => {

        this.LoggedIn = false;
      }
    );
  }

}
