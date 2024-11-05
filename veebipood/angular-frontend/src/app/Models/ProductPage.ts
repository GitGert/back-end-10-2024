import { Product } from "./Product"

export interface Productpage{
    content: Product[]
    pageable: Pageable
    last: boolean
    totalPages: number
    totalElements: number
    first: boolean
    size: number
    number: number
    sort: Sort
    numberOfElements: number
    empty: boolean
}

export interface Pageable {
    pageNumber: number
    pageSize: number
    sort: Sort
    offset: number
    paged: boolean
    unpaged: boolean
  }
  
  export interface Sort {
    sorted: boolean
    empty: boolean
    unsorted: boolean
  }