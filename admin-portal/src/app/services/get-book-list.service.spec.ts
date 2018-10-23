import {inject, TestBed} from '@angular/core/testing';

import {GetBookListService} from './get-book-list.service';

describe('GetBookListService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [GetBookListService]
    });
  });

  it('should be created', inject([GetBookListService], (service: GetBookListService) => {
    expect(service).toBeTruthy();
  }));
});
