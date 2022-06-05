package com.sheriffs.babysheriff.view.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sheriffs.babysheriff.R;
import com.sheriffs.babysheriff.adapter.CalendarAdapter;
import com.sheriffs.babysheriff.adapter.DiaryAdapter;
import com.sheriffs.babysheriff.database.DBHelper;
import com.sheriffs.babysheriff.model.EventInfo;
import com.sheriffs.babysheriff.util.BaseCalendar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class Menu_diary extends Fragment implements View.OnClickListener {
    View view;
    //캘린더
    private CalendarAdapter mCalendarAdapter;
    private RecyclerView mRv_schedule;
    private TextView mTv_current;
    private ImageView mIv_prev,
            mIv_next;

    // 다이어리 리스트
    private ArrayList<EventInfo> arrayList;
    private DiaryAdapter diaryAdapter;
    private RecyclerView recyclerView, rc_calendar;
    private LinearLayoutManager linearLayoutManager;


    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @org.jetbrains.annotations.NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.menu_diary,container,false);

        setInit();

        // 다이어리 리스트

        diaryListSetInit();
        return view;
    }
    public void diaryListSetInit(){
        recyclerView = (RecyclerView)view.findViewById(R.id.rc_view);
        linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        String date = mTv_current.getText().toString();
        String[] year = date.split(" ");
        if(year[1].equals("10")||year[1].equals("11")||year[1].equals("12"))
            date = String.format("%s 년 %s 월",year[0],year[1]);
        else{
            int day = Integer.parseInt(year[1]);

            date = String.format("%s 년 %s 월",year[0],day);
        }
        // 임의 값
        DBHelper mDBHelper;
        mDBHelper = new DBHelper(this.getContext());
        arrayList = new ArrayList<>();
        arrayList = mDBHelper.getSelectedLikeEventItem(date);
        diaryAdapter = new DiaryAdapter(arrayList);
        recyclerView.setAdapter(diaryAdapter);

        diaryAdapter.notifyDataSetChanged();

    }

    private void setInit() {
        mIv_prev = view.findViewById(R.id.iv_prev_month);
        mIv_next = view.findViewById(R.id.iv_next_month);
        mTv_current = view.findViewById(R.id.tv_current_month);
        mRv_schedule = view.findViewById(R.id.rv_schedule);

        mRv_schedule.setLayoutManager(new GridLayoutManager(getContext(), BaseCalendar.DAYS_OF_WEEK));               // 그리드 레이아웃 매니져 적용 (엑셀 표처럼 네모 칸 형태 구조)
//        mRv_schedule.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.HORIZONTAL));   // 가로 방향으로 Divider (구분선) 을 두어 나눈다.
//        mRv_schedule.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));     // 세로 방향으로 Divider (구분선) 을 두어 나눈다.

        mIv_prev.setOnClickListener(this);
        mIv_next.setOnClickListener(this);

        mCalendarAdapter = new CalendarAdapter(this,this);
        mRv_schedule.setAdapter(mCalendarAdapter);
    }

    @Override
    public void onClick(View _view) {
        switch (_view.getId()) {
            case R.id.iv_prev_month: {
                mCalendarAdapter.changeToPrevMonth();
                break; }
            case R.id.iv_next_month: {
                mCalendarAdapter.changeToNextMonth();
                break; }
        }
    }

    /**
     * 다음달 or 이전달 변동에 의한 현재달 최신화
     * @param _calendar
     */
    public void refreshCurrentMonth(Calendar _calendar) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy MM", Locale.KOREAN);
        mTv_current.setText(formatter.format(_calendar.getTime()));
        diaryListSetInit();
    }
}
