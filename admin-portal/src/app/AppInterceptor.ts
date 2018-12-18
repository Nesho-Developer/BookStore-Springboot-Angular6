import {Injectable} from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Observable, throwError} from "rxjs";
import {catchError} from "rxjs/operators";
import {Router} from "@angular/router";


@Injectable({providedIn: 'root'})
export class AppInterceptor implements HttpInterceptor {
  constructor(private router: Router) {
  }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (request.url !== 'http://localhost:8080/oauth/token') {
      request = request.clone({
        setHeaders: {
          Authorization: localStorage.getItem('Authorization')
        }
      });
    }
    return next.handle(request)
      .pipe(
        catchError(
          (error: Response) => {
            if (error.status === 401) {
              this.router.navigate(['/myAccount']);
            }
            const err = error.statusText;
            return throwError(err);
          }
        ));
  }
}
