import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {AppConst} from "../constans/app-const";

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private serverPath: string = AppConst.serverPath;
  private authToken;
  private client_id: string = "bookstore";
  private client_secret: string = "password";

  constructor(private http: HttpClient) {
  }

  getToken() {
    return this.authToken;
  }

  sendCredential(username: string, password: string) {
    let url = 'http://localhost:8080/oauth/token';
    let userInfo = {
      'username': username, "password": password, 'grant_type': "password"
    };
    let bo = new FormData();
    bo.append('username', username);
    bo.append('password', password);
    bo.append('grant_type', 'password');
    let bodySerialized = 'password=' + password + '&username=' + 'username&grant_type=password';
    console.log(bodySerialized);
    let encodedCredential = btoa(this.client_id + ":" + this.client_secret);
    let basicHeader = 'Basic ' + encodedCredential;
    this.authToken = basicHeader;
    // localStorage.setItem('Authorization', basicHeader);
    console.log(basicHeader);
    let headers = new HttpHeaders({
      // 'ContentType': 'application/form-data',
      'Authorization': basicHeader
    });

    return this.http.post(url, bo, {headers: headers});
  }

  checkSession() {
    let url = 'http://localhost:8080/checkSession';
    let headers = new HttpHeaders({
      'Authorization': localStorage.getItem('Authorization')
    });
    console.log('checkSession');

    return this.http.get(url, {headers: headers, responseType: 'text'});
  }

  logout() {
    let url = 'http://localhost:8080/userLogout';

    let headers = new HttpHeaders({
      'Authorization': localStorage.getItem('Authorization')
    });

    return this.http.post(url, '', {headers: headers, responseType: 'text'});
  }
}
