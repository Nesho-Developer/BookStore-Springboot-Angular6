import {TestBed} from '@angular/core/testing';

import {ShippingService} from './shipping.service';

describe('ShippingService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ShippingService = TestBed.get(ShippingService);
    expect(service).toBeTruthy();
  });
});
