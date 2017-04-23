package kr.povice.mart;

import android.databinding.DataBindingUtil;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import kr.povice.mart.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static final String SERVER_HOST_NAME = "192.168.0.70";
    private static final int SERVER_HOST_PORT = 9090;

    private TTransport transport;
    private TProtocol protocol;
    private MarketService.Client client;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);


        transport = new TSocket("192.168.0.70", 9090);
        TProtocol protocol = new TBinaryProtocol(transport);
        client = new MarketService.Client(protocol);

    }


    public  void  onClick(View v)
    {


        //binding.listView.set

        //binding.lblCount.setText("TEST");

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    transport.open();
                    final ArrayList<Product> rows = new ArrayList<Product>(client.GetProductList(200));
                    transport.close();

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {


                            ProductAdaper adaper = new ProductAdaper(MainActivity.this, R.layout.list_item, rows);
                            binding.listView.setAdapter(adaper);

                            binding.lblCount.setText(rows.size()+"");
                        }
                    });

                } catch (Exception e)
                {
                    binding.lblCount.setText(e.getMessage()+e.getStackTrace()+e.toString()+"");
                }

            }
        }).start();



    }
}
