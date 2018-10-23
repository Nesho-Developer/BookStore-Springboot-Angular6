import {TestBed} from '@angular/core/testing';

import {RemoveBookService} from './remove-book.service';

describe('RemoveBookService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: RemoveBookService = TestBed.get(RemoveBookService);
    expect(service).toBeTruthy();
  });
});
