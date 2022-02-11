package kr.co.MyIntent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_launcher);
        setTitle("명화 선호도 투표");

        // 그림을 클릭할때마다 투표수를 저장할 9개의 배열을 선언, 0으로 초기화
        final int[] voteCount = new int[9];
        for (int i=0; i<9; i++) {
            voteCount[i] = 0;
        }

        // 이미지뷰 위젯을 저장할 9개의 배열을 선언
        ImageView[] imageViews = new ImageView[9];
        // 이미지뷰 위젯의 id를 저장할 배열 선언
        Integer[] imageId = {R.id.iv1, R.id.iv2, R.id.iv3, R.id.iv4, R.id.iv5, R.id.iv6,
                             R.id.iv7, R.id.iv8, R.id.iv9};
        // 그림의 이름을 저장할 9개의 배열을 선언
        final String[] imageName = {"독서하는소녀", "꽃장식 소녀", "부채를 든 소녀", "이레느깡 단 베르양",
                "잠자는 소녀", "테라스의 두 자매", "피아노 레슨", "피아노 앞의 소녀들", "해변에서"};

        // 각 이미지뷰에 대한 클릭 이벤트 리스너 생성
        // 이미지뷰가 9개이므로 반복문 사용
        // 이미지를 클릭하면 각 이미지의 투표수가 증가하도록 설정
        // 이미지를 클릭할때마다 해당 이미지이름과 누적된 투표수를 토스트 메시지로 보여줌

        for (int i = 0; i < imageId.length; i++) {
            final int index;        // 그때그때 바뀌기 때문에 꼭 필요함
            index = i;

            imageViews[index] = findViewById(imageId[index]);
            imageViews[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // 투표수 증가
                    voteCount[index]++;
                    Toast.makeText(getApplicationContext(), imageName[index] +": 총 "+ voteCount[index] +"표", Toast.LENGTH_SHORT).show();
                }
            });

        }

        // <투표 종료> 클릭 이벤트 리스너 생성
        // 인텐트 생성, 인텐트에 투표수 배열과 그림 이름 배열 넣은후 ResultActivity 호출
        Button btnFinish = findViewById(R.id.btnResult);
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                intent.putExtra("VoteCount", voteCount);
                intent.putExtra("ImageName", imageName);

                startActivity(intent);
            }
        });
    }
}