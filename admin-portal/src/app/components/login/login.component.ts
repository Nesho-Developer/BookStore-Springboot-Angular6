import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {LoginService} from '../../services/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public credential = {'username': '', 'password': ''};
  public LoggedIn = false;

  constructor(private loginService: LoginService, private http: HttpClient, private router: Router) {
  }

  onSubmit() {
    this.loginService.sendCredemtial(this.credential).subscribe(
      data => {
        console.log('success', data);

        //this.router.navigateByUrl('/');
        localStorage.setItem('xAuthToken', data['token']);
        this.LoggedIn = true;
        location.reload();
      },
      error => {
        console.log('Ooooops fail login', error);
      }
    );

  }


  ngOnInit() {
    this.loginService.checkSession().subscribe(
      data => {
        console.log("success", data);
        this.LoggedIn = true;
      },
      error => {

        this.LoggedIn = false;
      }
    );
  }

}
