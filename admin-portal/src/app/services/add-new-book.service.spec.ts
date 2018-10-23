import {inject, TestBed} from '@angular/core/testing';

import {AddNewBookService} from './add-new-book.service';

describe('AddNewBookService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AddNewBookService]
    });
  });

  it('should be created', inject([AddNewBookService], (service: AddNewBookService) => {
    expect(service).toBeTruthy();
  }));
});
