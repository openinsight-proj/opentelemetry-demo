
import { IProductCartItem } from '../../types/Cart';
import * as S from './CartItems.styled';
interface IProps {
  productList: IProductCartItem[];
  shouldShowPrice?: boolean;
}

const CartItems = ({ shouldShowPrice = true }: IProps) => {


  return (
    <S.CartItems>
      <S.CardItemsHeader>
        <label>Product</label>
        <label>Quantity</label>
        <label>Price</label>
      </S.CardItemsHeader>
      {shouldShowPrice && (
        <>
          <S.DataRow>
            <span>Shipping</span>
          </S.DataRow>
          <S.DataRow>
            <S.TotalText>Total</S.TotalText>
            <S.TotalText>
            </S.TotalText>
          </S.DataRow>
        </>
      )}
    </S.CartItems>
  );
};
export default CartItems;
