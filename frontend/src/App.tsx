import { useEffect, useState } from "react";
import type { Product } from "./types";

function App() {
  const [products, setProducts] = useState<Product[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    fetch("http://localhost:8080/api/products")
      .then((res) => {
        if (!res.ok) {
          throw new Error("Kunne ikke hente produkter");
        }
        return res.json();
      })
      .then((data: Product[]) => {
        setProducts(data);
        setLoading(false);
      })
      .catch((err: Error) => {
        setError(err.message);
        setLoading(false);
      });
  }, []);

  if (loading) {
    return <p>Laster produkter...</p>;
  }

  if (error) {
    return <p>Feil: {error}</p>;
  }

  return (
    <div>
      <h1>Produkter</h1>
      <ul>
        {products.map((product) => (
          <li key={product.id}>
            <strong>{product.name}</strong> – {product.price} kr
            <br />
            På lager: {product.stockQuantity}
          </li>
        ))}
      </ul>
    </div>
  );
}

export default App;