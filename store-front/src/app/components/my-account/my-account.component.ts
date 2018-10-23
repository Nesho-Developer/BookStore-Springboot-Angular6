import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {UserService} from "../../services/user.service";
import {LoginService} from "../../services/login.service";
import {AppConst} from "../../constans/app-const";
import {FormControl, Validators} from "@angular/forms";


@Component({
  selector: 'app-my-account',
  templateUrl: './my-account.component.html',
  styleUrls: ['./my-account.component.css']
})
export class MyAccountComponent implements OnInit {

  validator = new FormControl('', [Validators.required]);
  private path = AppConst.serverPath;
  private errorLogin: boolean;
  private errorMessage: string;
  private loggedIn: boolean = false;
  private credential = {'username': '', 'password': ''};
  private emailSent: boolean;
  private usernameExists: boolean;
  private emailExists: boolean;
  private username: string;
  private email: string;
  private emailNotExists: boolean = false;
  private forgetPasswordEmailSent: boolean;
  private recoverEmail: string;

  constructor(private  router: Router,
              private  userService: UserService,
              private  loginService: LoginService) {
  }

  getvalidatorErrorMessage() {
    return this.validator.hasError('required') ? 'You must enter a value' : '';
  }

  onLogin() {
    this.errorLogin = false;
    this.errorMessage = '';
    this.loginService.sendCredential(this.credential.username, this.credential.password).subscribe(
      res => {
        //localStorage.setItem('xAuthToken2',res.body);
        this.loggedIn = true;
        location.reload();
        this.router.navigate(['/home']);

      },
      err => {
        this.errorMessage = err['error'];
        console.log(this.errorMessage);
        this.errorLogin = true;
        this.loggedIn = false;
      }
    );
  }

  onNewAccount() {
    console.log(this.username, this.email);
    this.usernameExists = false;
    this.emailExists = false;
    this.emailSent = false;

    this.userService.newUser(this.username, this.email).subscribe(
      res => {
        console.log(res);
        this.emailSent = true;
      },
      err => {
        console.log(typeof err, typeof err['error']);
        let errorMessage = err['error'];
        console.log(errorMessage);
        if (errorMessage === 'UsernameExists') {
          this.usernameExists = true;
          console.log(this.usernameExists);
        }
        if (errorMessage === 'EmailExists') {
          this.emailExists = true
        }
      }
    );
  }

  onForgetPassword() {
    this.forgetPasswordEmailSent = false;
    this.emailNotExists = false;
    this.userService.retrivePassword(this.recoverEmail).subscribe(
      res => {
        console.log(res);
        this.forgetPasswordEmailSent = true;
      },
      err => {
        let errorMessage = err['error'];
        if (errorMessage === 'Email not found.') {
          this.emailNotExists = true
        }

      }
    );
  }

  ngOnInit() {
    this.loginService.checkSession().subscribe(
      res => {
        this.loggedIn = true;
        console.log(res);
      },
      err => {
        this.loggedIn = false;
        console.log(err['error']);
      }
    );
  }

}
