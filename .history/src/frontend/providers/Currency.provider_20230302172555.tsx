/*
 * @Author: Ning Tang
 * @LastEditors: Ning Tang
 * @email: Ning.Tang@daocloud.io
 * @gitlab: https://gitlab.daocloud.cn/ning.tang/dso-ui.git
 * @Date: 2023-03-02 17:19:51
 * @LastEditTime: 2023-03-02 17:25:46
 * @motto: Still water run deep
 * @Description: Modify here please
 * @FilePath: \opentelemetry-demo\src\frontend\providers\Currency.provider.tsx
 */
import { createContext, useCallback, useContext, useMemo, useState, useEffect } from 'react';
import { useQuery } from 'react-query';
import ApiGateway from '../gateways/Api.gateway';
import SessionGateway from '../gateways/Session.gateway';

const { currencyCode } = SessionGateway.getSession();

interface IContext {
  currencyCodeList: string[];
  setSelectedCurrency(currency: string): void;
  selectedCurrency: string;
}

export const Context = createContext<IContext>({
  currencyCodeList: [],
  selectedCurrency: 'USD',
  setSelectedCurrency: () => ({}),
});

interface IProps {
  children: React.ReactNode;
}

export const useCurrency = () => useContext(Context);

const CurrencyProvider = ({ children }: IProps) => {
  const { data: currencyCodeListUnsorted = [] } = useQuery('currency', {}
  // ApiGateway.getSupportedCurrencyList
  );
  const [selectedCurrency, setSelectedCurrency] = useState<string>('');

  useEffect(() => {
    setSelectedCurrency(currencyCode);
  }, []);

  const onSelectCurrency = useCallback((currencyCode: string) => {
    setSelectedCurrency(currencyCode);
    SessionGateway.setSessionValue('currencyCode', currencyCode);
  }, []);

  const currencyCodeList = currencyCodeListUnsorted.sort();

  const value = useMemo(
      () => ({
        currencyCodeList,
        selectedCurrency,
        setSelectedCurrency: onSelectCurrency,
      }),
      [currencyCodeList, selectedCurrency, onSelectCurrency]
  );

  return <Context.Provider value={value}>{children}</Context.Provider>;
};

export default CurrencyProvider;
