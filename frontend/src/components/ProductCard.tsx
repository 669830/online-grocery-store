import type { Product } from "../types";

interface Props {
  product: Product;
}

function ProductCard({ product }: Props) {
  const inStock = product.stockQuantity > 0;

  return (
    <article className="product-card">
      <div className="product-image">
        {product.imageUrl ? (
          <img src={product.imageUrl} alt={product.name} />
        ) : (
          <span className="no-image">Ingen bilde</span>
        )}
      </div>

      <div className="product-body">
        <h2>{product.name}</h2>
        {product.category && <p className="category">{product.category}</p>}
        {product.description && <p className="description">{product.description}</p>}

        <div className="product-footer">
          <span className="price">{product.price.toFixed(2)} kr</span>
          {inStock ? (
            <span className="stock in-stock">{product.stockQuantity} på lager</span>
          ) : (
            <span className="stock out-of-stock">Utsolgt</span>
          )}
        </div>
      </div>
    </article>
  );
}

export default ProductCard;