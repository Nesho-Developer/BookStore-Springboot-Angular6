import {TestBed} from '@angular/core/testing';

import {GetBookService} from './get-book.service';

describe('GetBookService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: GetBookService = TestBed.get(GetBookService);
    expect(service).toBeTruthy();
  });
});
