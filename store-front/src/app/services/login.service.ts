import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {AppConst} from "../constans/app-const";

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  private serverPath: string = AppConst.serverPath;
  private authToken;

  constructor(private http: HttpClient) {
  }

  getToken() {
    return this.authToken;
  }

  sendCredential(username: string, password: string) {
    let url = 'http://localhost:8080/login';
    let userInfo = {
      'username': username, "password": password
    };
    let encodedCredential = btoa(username + ":" + password);
    let basicHeader = 'Basic ' + encodedCredential;
    this.authToken = basicHeader;
    localStorage.setItem('Authorization', basicHeader);
    let headers = new HttpHeaders({
      'ContentType': 'application/x-www-form-urlencoded',
      'Authorization': basicHeader
    });

    return this.http.post(url, userInfo, {responseType: 'text', observe: 'response'});
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
