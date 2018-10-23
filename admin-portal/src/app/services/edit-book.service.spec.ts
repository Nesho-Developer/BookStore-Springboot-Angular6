import {TestBed} from '@angular/core/testing';

import {EditBookService} from './edit-book.service';

describe('EditBookService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: EditBookService = TestBed.get(EditBookService);
    expect(service).toBeTruthy();
  });
});
