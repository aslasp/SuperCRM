package wn13.supercrm.presenter;

import wn13.supercrm.model.Product;

/**
 * Created by wn13 on 2016/6/29.
 */
public interface IAddProductView {

    Product getProductInfo();
    void onAddError();

    void onAddResp(String info);
}
