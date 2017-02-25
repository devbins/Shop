package com.dev.bins.shop;

import android.content.res.AssetManager;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.bigkoo.pickerview.OptionsPickerView;
import com.dev.bins.shop.bean.City;
import com.dev.bins.shop.bean.District;
import com.dev.bins.shop.bean.Province;
import com.dev.bins.shop.fragment.me.ProvinceHandler;

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

import static org.litepal.LitePalApplication.getContext;

public class AddAddressActivity extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.ll_add)
    LinearLayout mLinearLayoutAdd;
    OptionsPickerView mOptionsPickerView;
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
        mLinearLayoutAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        mOptionsPickerView.show();
    }

    private void initPickView() {
        mOptionsPickerView = new OptionsPickerView.Builder(getContext(), new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = mProvinceList.get(options1).getPickerViewText()
                        + mCities.get(options1).get(option2)
                        + mDistricts.get(options1).get(option2).get(options3).getPickerViewText();
//                tvOptions.setText(tx);
            }
        }).setSubmitText("确定")
                .setCancelText("取消")
                .setTitleText("城市选择")
                .setSubCalSize(18)
                .setTitleSize(20)
                .setTitleColor(Color.BLACK)
                .setSubmitColor(Color.BLUE)
                .setCancelColor(Color.BLUE)
                .setTitleBgColor(0xFF333333)//标题背景颜色 Night mode
                .setBgColor(0xFF000000)//滚轮背景颜色 Night mode
                .setContentTextSize(18)
                .setLinkage(false)
                .setLabels("省", "市", "区")//设置选择的三级单位
                .setCyclic(false, false, false)//循环与否
                .setSelectOptions(1, 1, 1)  //设置默认选中项
                .setOutSideCancelable(false)//点击外部dismiss default true
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
                ArrayList<String> citys = new ArrayList<>(cities.size()); //城市List


                for (City c : cities) {

                    citys.add(c.getName()); // 把城市名称放入 cityStrs


                    ArrayList<ArrayList<District>> dts = new ArrayList<>(); // 地区 List

                    List<District> districts = c.getDistricts();
                    ArrayList<District> districtStrs = new ArrayList<>(districts.size());

                    for (District d : districts) {
                        districtStrs.add(d); // 把城市名称放入 districtStrs
                    }
                    dts.add(districtStrs);


                    mDistricts.add(dts);
                }

                mCities.add(citys); // 组装城市数据

            }
        }
    }


}
