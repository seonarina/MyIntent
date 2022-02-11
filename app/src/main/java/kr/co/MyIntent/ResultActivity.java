package kr.co.MyIntent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Rating;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_launcher);
        setTitle("명화 선호도 투표 결과");

        // 메인 액티비티에서 보낸 인텐트 받고, 넘겨받은 투표 결과 배열과 그림 이름 배열 저장

        // 앞 액티비티(화면)에서 보낸 투표 결과 값을 받음
        Intent intent = getIntent();
        int[] voteResult = intent.getIntArrayExtra("VoteCount");
        String[] imageName = intent.getStringArrayExtra("ImageName");

        // activity_result.xml의 텍스트뷰 9개, 레이팅바 9개 위젯 변수 배열을 선언
        TextView[] textViews = new TextView[imageName.length];
        RatingBar[] ratingBars = new RatingBar[imageName.length];

        // 텍스트뷰 id를 저장할 배열 변수, 레이팅바 id를 저장할 배열 변수를 선언
        Integer[] tvID = {R.id.tv1, R.id.tv2, R.id.tv3, R.id.tv4, R.id.tv5, R.id.tv6, R.id.tv7,
                          R.id.tv8, R.id.tv9};
        Integer[] rbarID = {R.id.rbar1, R.id.rbar2, R.id.rbar3, R.id.rbar4, R.id.rbar5,
                            R.id.rbar6, R.id.rbar7, R.id.rbar8, R.id.rbar9};

        // XML 파일의 텍스트뷰와 레이팅바를 위젯 변수에 대입
        // 9개의 TextView, RatingBar 개체 연결하기
        for (int i = 0; i < voteResult.length; i++) {
            textViews[i] = findViewById(tvID[i]);
            ratingBars[i] = findViewById(rbarID[i]);

        }

        // 텍스트뷰 위젯 변수에 넘겨받은 그림이름 적용
        // 레이팅바에는 넘겨받은 투표결과를 적용
        for(int i = 0; i < voteResult.length; i++) {
            textViews[i].setText(imageName[i]);
            ratingBars[i].setRating((float) voteResult[i]);
        }

        // 버튼을 클릭하면 ResultActivity를 종료, 즉 메인 액티비티로 돌아감
        Button btnReturn = findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}