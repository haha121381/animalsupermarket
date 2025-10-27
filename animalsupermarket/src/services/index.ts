class ProductService {
    private products: { id: number; name: string; price: number }[] = [];

    constructor() {
        // Initialize with some dummy products
        this.products = [
            { id: 1, name: 'Product 1', price: 10.0 },
            { id: 2, name: 'Product 2', price: 20.0 },
            { id: 3, name: 'Product 3', price: 30.0 },
        ];
    }

    public getProducts() {
        return this.products;
    }

    public getProductById(id: number) {
        return this.products.find(product => product.id === id);
    }
}

export default ProductService;