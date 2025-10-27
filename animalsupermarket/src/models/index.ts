export interface Product {
    id: number;
    name: string;
    price: number;
}

export class ProductModel {
    private products: Product[] = [];

    constructor(initialProducts: Product[]) {
        this.products = initialProducts;
    }

    getAllProducts(): Product[] {
        return this.products;
    }

    getProductById(id: number): Product | undefined {
        return this.products.find(product => product.id === id);
    }

    addProduct(product: Product): void {
        this.products.push(product);
    }
}