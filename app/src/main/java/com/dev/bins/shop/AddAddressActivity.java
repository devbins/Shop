package com.dev.bins.shop;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.dev.bins.shop.bean.City;
import com.dev.bins.shop.bean.District;
import com.dev.bins.shop.bean.OrderAddress;
import com.dev.bins.shop.bean.Province;
import com.dev.bins.shop.fragment.me.ProvinceHandler;
import com.dev.bins.shop.widget.MyToolbar;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.dev.bins.shop.R.string.address;
import static org.litepal.LitePalApplication.getContext;

public class AddAddressActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.ok_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.ll_add)
    LinearLayout mLinearLayoutAdd;
    OptionsPickerView mOptionsPickerView;
    @BindView(R.id.et_name)
    EditText mEditTextName;
    @BindView(R.id.et_phone)
    EditText mEditTextPhone;
    @BindView(R.id.tv_addr)
    TextView mTextViewAdd;
    @BindView(R.id.et_add_detail)
    EditText mEditTextAddress;
    @BindView(R.id.btn_ok)
    Button mBtnOK;
    private ArrayList<Province> mProvinceList;
    private ArrayList<ArrayList<String>> mCities = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<District>>> mDistricts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);
        ButterKnife.bind(this);
        initData();
        initPickView();
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        mBtnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });
        mTextViewAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        mOptionsPickerView.show();
    }

    private void initPickView() {
        mOptionsPickerView = new OptionsPickerView.Builder(this, new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = mProvinceList.get(options1).getPickerViewText()
                        + mCities.get(options1).get(option2)
                        + mDistricts.get(options1).get(option2).get(options3).getPickerViewText();
                mTextViewAdd.setText(tx);
            }
        }).setSubmitText("确定")
                .setCancelText("取消")
                .setTitleText("城市选择")
                .setCyclic(false, false, false)//循环与否
                .setOutSideCancelable(true)//点击外部dismiss default true
                .build();
        mOptionsPickerView.setPicker(mProvinceList, mCities, mDistricts);

    }

    private void initData() {

        AssetManager assetManager = getContext().getAssets();

        try {
            InputStream inputStream = assetManager.open("province.xml");
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            ProvinceHandler dh = new ProvinceHandler();
            parser.parse(inputStream, dh);
            inputStream.close();
            mProvinceList = dh.getProvinceList();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }


        if (null != mProvinceList) {
            for (Province p : mProvinceList) {

                List<City> cities = p.getCities();
                ArrayList<String> citys = new ArrayList<>(cities.size());

                ArrayList<ArrayList<District>> dts = new ArrayList<>();
                for (City c : cities) {

                    citys.add(c.getName());
                    ArrayList<District> districts = c.getDistricts();

                    dts.add(districts);

                }
                mDistricts.add(dts);

                mCities.add(citys);

            }
        }
    }


    public void save() {
        String name = mEditTextName.getText().toString().trim();
        String phone = mEditTextPhone.getText().toString().trim();
        String addr = mTextViewAdd.getText().toString().trim();
        String address = mEditTextAddress.getText().toString().trim();
        boolean isEmpty = false;
        if (name.isEmpty()) {
            mEditTextName.setError(getString(R.string.error_empty));
            isEmpty = true;
        }
        if (phone.isEmpty()) {
            mEditTextPhone.setError(getString(R.string.error_empty));
            isEmpty = true;
        }
        if (addr.isEmpty()) {
            mTextViewAdd.setError(getString(R.string.error_empty));
            isEmpty = true;
        }
        if (address.isEmpty()) {
            mEditTextAddress.setError(getString(R.string.error_empty));
            isEmpty = true;
        }
        if (!isEmpty) {
            OrderAddress orderAddress = new OrderAddress(name, phone, addr, address);
            orderAddress.save();
        }


    }


}
