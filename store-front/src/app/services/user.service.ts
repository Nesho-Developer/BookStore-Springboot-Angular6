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

    return this.http.post(url, (userInfo), {responseType: 'text'});

  }

  retrivePassword(recoverEmail: string) {
    let url = 'http://localhost:8080/user/forgetPassword';

    return this.http.post(url, recoverEmail, { responseType: 'text'});
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

    return this.http.post(url, userInfo);

  }

  getCurrentUser() {
    let url = 'http://localhost:8080/user/getCurrentUser';

    return this.http.get(url);

  }
}
