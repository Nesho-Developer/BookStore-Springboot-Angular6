import {Injectable} from '@angular/core';

import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse} from '@angular/common/http'
import {Observable} from 'rxjs';
import {tap} from 'rxjs/operators';
import {LoginService} from "./login.service";


@Injectable()//{providedIn: 'root'}

export class InterceptService implements HttpInterceptor {

  constructor(private loginService: LoginService) {
  }

  // intercept request and add token
  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    console.log('-----', request);
    if (request.url === 'http://localhost:8080/login') {
      return Observable.throw('Error message');
    }
    // modify request
    request = request.clone({

      setHeaders: {
        Authorization: localStorage.getItem('xAuthToken2')
      }
    });

    console.log("----request----");

    console.log(request);

    console.log("--- end of request---");


    return next.handle(request)
      .pipe(
        tap(event => {
          if (event instanceof HttpResponse) {

            console.log(" all looks good");
            // http response status code
            console.log(event.status);
          }
          // console.log('---', event);
        }, error => {
          // http response status code
          console.log("----response----");
          console.error(error['error']);
          // console.error(error.toString());

          console.log("--- end of response---");

        })
      )

  };


}
