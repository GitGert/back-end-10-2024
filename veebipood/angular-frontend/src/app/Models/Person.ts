import { Address } from "./Address";

export interface Person {
    email : string
    firstname?: string
    lastname? : string
    address?: Address;
}