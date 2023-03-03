import * as S from './CartItems.styled';
const CartItems = () => {
  return (
    <S.CartItems>
      <S.CardItemsHeader>
        <label>Product</label>
        <label>Quantity</label>
        <label>Price</label>
      </S.CardItemsHeader>
    </S.CartItems>
  );
};

export default CartItems;
