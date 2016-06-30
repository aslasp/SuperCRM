package wn13.supercrm.presenter;

import wn13.supercrm.model.Product;

/**
 * Created by wn13 on 2016/6/29.
 */
public class AddProductPresenter {
    private IAddProductView view;
    private IAddProductModel pm;

    public static int ADD_FAILURE=0;
    public static int ADD_SUCCESS=1;

    public AddProductPresenter(IAddProductView v){
        view=v;
        pm=new AddProductModel(this);
    }

    public void addProduct(String url){
        pm.addProduct(view.getProductInfo(),url);
    }


    public void onAddError(){
        view.onAddError();
    }

    public void onAddResp(String info){
        view.onAddResp(info);
    }
}
