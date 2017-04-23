package kr.povice.mart;
import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import kr.povice.mart.databinding.ActivityMainBinding;
import kr.povice.mart.databinding.ListItemBinding;

/**
 * Created by erith on 2017-04-23.
 */

public class ProductAdaper extends ArrayAdapter<Product> {

    private ArrayList<Product> items;
    private int checkedIndex = 0;
    //private ListItemBinding binding;
    //private ListItemBinding binding;
    private Context context;

    public ProductAdaper(@NonNull Context context, int resourceId,  @NonNull ArrayList<Product> items) {
        super(context,  resourceId, items);
        this.items = items;
        this.context = context;
        //ActivityMainBinding binding = DataBindingUtil.setContentView((Activity) context, R.layout.activity_main);
        //binding =  DataBindingUtil.setContentView((Activity) context, R.layout.list_item);



    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        ListItemBinding binding = DataBindingUtil.inflate(inflater, R.layout.list_item,  parent, false);
        Product item = getItem(position);
        binding.productName.setText(item.NAME);
        binding.productCost.setText(String.format("%d",item.COST));

        return binding.getRoot();
    }
}
