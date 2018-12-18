import {Injectable} from '@angular/core';

import {HttpClient, HttpHeaders} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private client_id: string = "bookstore";
  private client_secret: string = "password";
  constructor(private _http: HttpClient) {
  }

  sendCredemtial(credential) {
    let url = 'http://localhost:8080/oauth/token';
    let userInfo = {
      'username': credential.username, "password": credential.password, 'grant_type': "password"
    };
    let bo = new FormData();
    bo.append('username', credential.username);
    bo.append('password', credential.password);
    bo.append('grant_type', 'password');
    let encodedCredential = btoa(this.client_id + ":" + this.client_secret);
    let basicHeader = 'Basic ' + encodedCredential;

    console.log(basicHeader);
    let headers = new HttpHeaders({
      // 'ContentType': 'application/form-data',
      'Authorization': basicHeader
    });

    return this._http.post(url, bo, {headers: headers});
  }

  checkSession() {

    const url = 'http://localhost:8080/checkSession';

    return this._http.get(url, {responseType: 'text'});


  }

  logout() {

    const url = 'http://localhost:8080/ulogout';

    return this._http.post(url, '', {responseType: 'text'});


  }
}
