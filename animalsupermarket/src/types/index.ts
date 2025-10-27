export interface Product {
    id: number;
    name: string;
    price: number;
}

export interface ServiceResponse<T> {
    data: T;
    message: string;
    success: boolean;
}