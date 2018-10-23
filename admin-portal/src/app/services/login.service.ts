import {Injectable} from '@angular/core';

import {HttpClient, HttpHeaders} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private _http: HttpClient) {
  }

  sendCredemtial(credential) {
    const url = 'http://localhost:8080/token';
    const encodedCredential = btoa(credential.username + ':' + credential.password);
    const basicHeader = 'basic ' + encodedCredential;

    let headers = new HttpHeaders({
      'Content-Type': 'application/x-www-form-urlencoded',
      'Authorization': basicHeader
    });

    return this._http.get(url, {headers: headers});


  }

  checkSession() {

    const url = 'http://localhost:8080/checkSession';


    let headers = new HttpHeaders({
      'x-auth-token': localStorage.getItem('xAuthToken')

    });

    return this._http.get(url, {headers: headers, responseType: 'text'});


  }

  logout() {

    const url = 'http://localhost:8080/ulogout';


    let headers = new HttpHeaders({
      'x-auth-token': localStorage.getItem('xAuthToken')

    });
    //localStorage.removeItem('xAuthToken');

    return this._http.post(url, '', {headers: headers, responseType: 'text'});


  }
}
