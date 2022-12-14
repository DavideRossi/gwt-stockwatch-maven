package com.google.gwt.sample.stockwatcher.shared;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("stockPrices")
public interface StockPriceService extends RemoteService {

    StockPrice[] getPrices(String[] symbols) throws DelistedException;

    void saveSymbols(String[] symbols);

	String[] loadSymbols();
}
