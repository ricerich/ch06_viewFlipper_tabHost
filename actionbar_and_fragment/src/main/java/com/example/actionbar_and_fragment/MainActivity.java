package com.example.actionbar_and_fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.android.material.tabs.TabLayout;

@SuppressWarnings("deprecation")
public class MainActivity extends AppCompatActivity implements ActionBar.TabListener 
{

    ActionBar.Tab tab1, tab2,tab3;//프래그먼트랑 연결해야하기 때문에 멤버변수로 격상되었음
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);//프레그먼트를 java로, xml아님(xml이 추천)

        //1."액션바" 만들기
        //2."탭" 만들기 - 액션바에 들어갈 탭
        //3."프레그먼트" 만들기
        //4. 탭과 프래그먼트 연결하기


        //1."액션바" 만들기
        ActionBar bar = getSupportActionBar();
        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        //2."탭" 만들기 - 액션바에 들어갈 탭, 탭만들고 액션바에 꽂기
//        ActionBar.Tab tab1, tab2,tab3;//프래그먼트랑 연결해야하기 때문에 멤버변수로 격상

        tab1 = bar.newTab();//탭만들기
        tab1.setText("음악별");//탭 옵션정하기
//        tab1.setTabListener(리스너);//탭에 리스너를 단다(이벤트처리)
        tab1.setTabListener(this);//탭에 리스너를 단다(이벤트처리)
        bar.addTab(tab1);//bar에 탭을 꽂는다

        tab2 = bar.newTab();
        tab2.setText("가수별");
        tab2.setTabListener(this);//탭에 리스너를 단다(이벤트처리)
        bar.addTab(tab2);

        tab3 = bar.newTab();
        tab3.setText("앨범별");
        tab3.setTabListener(this);//탭에 리스너를 단다(이벤트처리)
        bar.addTab(tab3);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
//        "tabName"
        MyTabFragment frag1 = null;//멤버변수로 격상
        MyTabFragment fragArr[] = new MyTabFragment[3];

        if(fragArr[tab.getPosition()] == null) //눌러진 탭인덱스가 배열에 있니? 처음이니?
        {
            //처음이라면
            //1.프래그먼트 생성 -> new MyFragment()
            //2.옵션: 프래그먼트 안에 리니어 색상 결정 -> Os로부터 받아온 Bundle의 키 중에서 "tabName"
            //3.프래그먼트와 탭연결

            //4.새로만든 프래그먼트1개 짜리를 프래그먼트배열에 넣어주기


            //1.프래그먼트 1개짜리 생성 -> new MyFragment()
            frag1 = new MyTabFragment();

            //2.옵션: 프래그먼트 안에 리니어 색상 결정
            // -> OS로 보내려면 Bundle이 필요함
            //Bundle안에 key를 tabName으로 정함
            //tabName 안에 "가수별","앨범별","음악별" 이런 실제값을 보냄,
            Bundle data = new Bundle();
            data.putString("tabName", tab.getText().toString());
            frag1.setArguments(data);

//            //3.프래그먼트와 탭연결// 저아래서 공용으로
//            ft.replace(android.R.id.content, frag1);

            //4.새로만든 프래그먼트1개 짜리를 프래그먼트배열에 넣어주기
            //나중을 위해서, 배열안에 프래그 있는지로 처음인지 아닌지 판별
            fragArr[tab.getPosition()] = frag1;


        }
        else//처음아님, 아까 왔었음
        {
            //프래그먼트 생성 안함! 왜? 배열안에 있으니까, 그거 꺼내옴
            frag1 = fragArr[tab.getPosition()];

        }

        //3.프래그먼트와 탭연결
        ft.replace(android.R.id.content, frag1);

    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    //3."프레그먼트" 만들기
    //멤버 자리에 - 1.멤변, 2.생성자, 3.메소드, 4,내부클래스
    public static class MyTabFragment extends Fragment
    {
        //alt + insert 키로, override method
        //1.onCreate() 와 2.onCreateView() 메소드 2개를 재정의한다.

        String tabName1;//멤버변수로 격상됨, 왜? onCreateView()에 쓸꺼니까
        //1.탭과 프래그먼트(=me)를 연결하기 위해 탭이름과 연동 설정
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            Bundle data = getArguments();//OS 대빵과 통신,
            tabName1 = data.getString("tabName");//OS의 Bundle의 키로 값을 가져옴
        }

        //프레그먼트(내부에)가 가지는 들어갈 디자인 만들기, 예)리니어레이아웃
        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
        {
            //나는 프레그먼트 뷰임, 리니어레이아웃을 하나 가질 예정임
            LinearLayout.LayoutParams params
                    = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                                                    LinearLayout.LayoutParams.MATCH_PARENT);
            LinearLayout baseLayout = new LinearLayout(super.getActivity());
            baseLayout.setOrientation(LinearLayout.VERTICAL);

            if(tabName1 == "음악별")
                baseLayout.setBackgroundColor(Color.RED);
            if(tabName1 == "가수별")
                baseLayout.setBackgroundColor(Color.GREEN);
            if(tabName1 == "앨범별")
                baseLayout.setBackgroundColor(Color.BLUE);


            return baseLayout;
//            return 리니어;
//            return super.onCreateView(inflater, container, savedInstanceState);
        }
    }



    //4. 탭과 프래그먼트 연결하기
    
}

//여기가 외부자리