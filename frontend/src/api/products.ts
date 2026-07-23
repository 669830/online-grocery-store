import type { Product } from "../types";

const BASE_URL = "http://localhost:8080/api/products";

export async function getProducts(): Promise<Product[]> {
    const res = await fetch(BASE_URL);
    if(!res.ok){
        throw new Error("Kunne ikke hente produkter");
    }
    return res.json();
}

export async function getProduct(id: number): Promise<Product>{
    const res = await fetch(`${BASE_URL}/${id}`);
    if(!res.ok){
        throw new Error("Fant ikke produktet");
    }
    return res.json();
}