import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {AppConst} from "../constans/app-const";
import {User} from "../models/user";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private serverPath: string = AppConst.serverPath;

  constructor(private http: HttpClient) {
  }

  newUser(username: string, email: string) {
    let url = 'http://localhost:8080/user/newUser';
    let userInfo = {
      'username': username, "email": email
    };
    let headers: HttpHeaders = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': localStorage.getItem('Authorization')
    });

    return this.http.post(url, (userInfo), {responseType: 'text'});

  }

  retrivePassword(recoverEmail: string) {
    let url = 'http://localhost:8080/user/forgetPassword';
    let headers = new HttpHeaders({
      // 'Content-Type':'application/json',
      'Authorization': localStorage.getItem('Authorization')
    });
    return this.http.post(url, recoverEmail, {headers: headers, responseType: 'text'});
  }

  updateUserInfo(user: User, newPassword: string, currentPassword: string) {
    let url = 'http://localhost:8080/user/updateUserInfo';
    let userInfo = {
      "id": user.id,
      "firstName": user.firstName,
      "lastName": user.lastName,
      "username": user.username,
      "currentPassword": currentPassword,
      "email": user.email,
      "newPassword": newPassword
    };
    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': localStorage.getItem("Authorization")
    });
    return this.http.post(url, userInfo, {headers: headers});

  }

  getCurrentUser() {
    let url = 'http://localhost:8080/user/getCurrentUser';
    let headers = new HttpHeaders({
      // 'Content-Type':'application/json',
      'Authorization': localStorage.getItem('Authorization')
    });
    return this.http.get(url, {headers: headers});

  }
}
