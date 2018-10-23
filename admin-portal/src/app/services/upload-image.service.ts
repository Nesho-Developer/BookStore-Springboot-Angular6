import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UploadImageService {

  filesToUpload: Array<File>;

  constructor() {
    this.filesToUpload = [];
  }

  upload(bookId: number) {
    this.makeFileRequest("http://localhost:8080/book/add/image?id=" + bookId, [], this.filesToUpload)
      .then((result) => {
        console.log(result);
      }, (error) => {
        console.log(error);
      });
  }

  fileChangeEvent(fileInput: any) {
    this.filesToUpload = <Array<File>> fileInput.target.files;
  }

  makeFileRequest(url: string, params: Array<string>, files: Array<File>) {
    return new Promise((resolve, reject) => {
      var formData: FormData = new FormData();
      var xhr = new XMLHttpRequest();
      for (let file of files) {
        formData.append('uploads[]', file, file.name);
      }

      console.log(formData.getAll('uploads[]'));


      xhr.onreadystatechange = function () {
        console.log(xhr.readyState, 'llllll');

        if (xhr.readyState == 4) {
          if (xhr.status == 200) {
            console.log("image uploaded successfully!");
          } else {
            reject(xhr.response);
          }
        }
      };
      console.log(xhr.status);
      xhr.open("POST", url, true);
      xhr.setRequestHeader("x-auth-token", localStorage.getItem("xAuthToken"));
      xhr.send(formData);
    });
  }


  modify(id: number) {
    if (this.filesToUpload.length > 0) {
      this.upload(id);
    }
    else {
      console.log(this.filesToUpload, 'not change');
    }

  }
}
