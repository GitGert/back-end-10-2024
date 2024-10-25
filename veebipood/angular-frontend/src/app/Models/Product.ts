import { Nutrients } from "./Nutrients"

export interface Product{
    id : number
    name : string
    price : number
    image : string
    active : boolean
    description : string
    nutrients : Nutrients
    category : {}
}