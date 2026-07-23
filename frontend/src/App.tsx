import { useEffect, useState } from "react";
import type { Product } from "./types";
import { getProducts } from "./api/products";
import ProductCard from "./components/ProductCard";
import "./App.css";

function App() {
  const [products, setProducts] = useState<Product[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    async function loadProducts() {
      try {
        const data = await getProducts();
        setProducts(data);
      } catch (err) {
        setError(err instanceof Error ? err.message : "Ukjent feil");
      } finally {
        setLoading(false);
      }
    }

    loadProducts();
  }, []);

  return (
    <div className="app">
      <header className="app-header">
        <h1>Asiatisk Matbutikk</h1>
        <p>Ris, nudler, sauser og mer</p>
      </header>

      <main>
        {loading && <p className="status">Laster produkter...</p>}
        {error && <p className="status error">{error}</p>}
        {!loading && !error && products.length === 0 && (
          <p className="status">Ingen produkter tilgjengelig.</p>
        )}

        <div className="product-grid">
          {products.map((product) => (
            <ProductCard key={product.id} product={product} />
          ))}
        </div>
      </main>
    </div>
  );
}

export default App;