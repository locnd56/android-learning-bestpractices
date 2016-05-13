package com.locnd.appbase.fragment.homefragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.locnd.appbase.R;
import com.locnd.appbase.abstracts.AbstractFragment;
import com.locnd.appbase.customview.sortbutton.CustomHeader;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Mr.Incredible on 2/22/2016.
 */
public class HomeFragment extends AbstractFragment {

    ListView lv_content;
    HomeAdapter adapter;
    List<HomeItem> homeItemList;
    CustomHeader header1;
    CustomHeader header2;
    CustomHeader header3;
    CustomHeader header4;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        showToast(getMainActivity(), "Home created");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);
        initData();
        initListener();
        return view;
    }

    private void initListener() {
        header1.setOnChangeFirstTurnListener(new CustomHeader.OnChangeFirstTurnListener() {
            @Override
            public void onChangeFirst() {
                adapter.resetArray();
                adapter.sortArray(new Comparator() {
                    @Override
                    public int compare(Object lhs, Object rhs) {
                        return ((HomeItem) lhs).getItem1().compareTo(((HomeItem) rhs).getItem1());
                    }
                });
            }
        });

        header1.setOnChangeSecondTurnListener(new CustomHeader.OnChangeSecondTurnListener() {
            @Override
            public void onChangeSecond() {
                adapter.sortArray(new Comparator() {
                    @Override
                    public int compare(Object lhs, Object rhs) {
                        return ((HomeItem) rhs).getItem1().compareTo(((HomeItem) lhs).getItem1());
                    }
                });
            }
        });

        header1.setOnChangeThirdTurnListener(new CustomHeader.OnChangeThirdTurnListener() {
            @Override
            public void onChangeThird() {
                adapter.resetArray();
            }
        });

        header2.setOnChangeFirstTurnListener(new CustomHeader.OnChangeFirstTurnListener() {
            @Override
            public void onChangeFirst() {

            }
        });
        header3.setOnChangeFirstTurnListener(new CustomHeader.OnChangeFirstTurnListener() {
            @Override
            public void onChangeFirst() {

            }
        });
        header4.setOnChangeFirstTurnListener(new CustomHeader.OnChangeFirstTurnListener() {
            @Override
            public void onChangeFirst() {

            }
        });

        header2.setOnChangeSecondTurnListener(new CustomHeader.OnChangeSecondTurnListener() {
            @Override
            public void onChangeSecond() {

            }
        });
        header3.setOnChangeSecondTurnListener(new CustomHeader.OnChangeSecondTurnListener() {
            @Override
            public void onChangeSecond() {

            }
        });
        header4.setOnChangeSecondTurnListener(new CustomHeader.OnChangeSecondTurnListener() {
            @Override
            public void onChangeSecond() {

            }
        });

        header2.setOnChangeThirdTurnListener(new CustomHeader.OnChangeThirdTurnListener() {
            @Override
            public void onChangeThird() {

            }
        });
        header3.setOnChangeThirdTurnListener(new CustomHeader.OnChangeThirdTurnListener() {
            @Override
            public void onChangeThird() {

            }
        });
        header4.setOnChangeThirdTurnListener(new CustomHeader.OnChangeThirdTurnListener() {
            @Override
            public void onChangeThird() {

            }
        });

    }

    private void initData() {
        if (homeItemList == null) {
            homeItemList = new ArrayList<>();
        }
        if (adapter == null) {
            adapter = new HomeAdapter(getMainActivity(), 0, homeItemList);
        }
        lv_content.setAdapter(adapter);
        dataTest();
        adapter.notifyDataSetChanged();
    }

    private void initView(View view) {
        lv_content = (ListView) view.findViewById(R.id.lv_friendsfragment_content);
        header1 = (CustomHeader) view.findViewById(R.id.fragmenthome_header1);
        header2 = (CustomHeader) view.findViewById(R.id.fragmenthome_header2);
        header3 = (CustomHeader) view.findViewById(R.id.fragmenthome_header3);
        header4 = (CustomHeader) view.findViewById(R.id.fragmenthome_header4);
    }

    @Override
    public String getTitle(Context context) {
        return context.getString(R.string.title_home);
    }

    @Override
    public void dataTest() {
        String[] arrRow1 = {"123", "234", "345", "456"};
        String[] arrRow2 = {"abc", "bcd", "dce", "efg"};
        String[] arrRow3 = {"efg", "fgh", "ghk", "hkl"};
        String[] arrRow4 = {"klm", "lmn", "mno", "nop"};

        for (int i = 0; i < arrRow1.length; i++) {
            HomeItem item = new HomeItem(arrRow1[i], arrRow2[i], arrRow3[i], arrRow1[i]);
            homeItemList.add(item);
        }
    }
}
