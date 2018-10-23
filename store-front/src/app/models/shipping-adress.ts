import {Order} from './order';

export class ShippingAddress {
  public id: number;
  public shippingAddressName: string;
  public shippingAddressStreet1: string;
  public shippingAddressStreet2: string;
  public shippingAddressCity: string;
  public shippingAddressState: string;
  public shippingAddressCountry: string;
  public shippingAddressZipcode: string;
  public shippingAddressDefault: boolean;
  public order: Order;
}
