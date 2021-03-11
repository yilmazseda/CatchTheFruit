package com.sedayilmaz.catchthekenny;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.sip.SipSession;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
//Tanımlama yapılır.Her yerden ulaşabilmek için.
    TextView scoreText;
    TextView timeText;
    int score;
    ImageView imageView;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView imageView9;
    ImageView imageView10;
    ImageView imageView11;
    ImageView imageView12;
    ImageView imageView13;
    ImageView imageView14;
    ImageView imageView15;
    ImageView imageView16;
    ImageView[] imageArray;
    Handler handler;
    Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initalize edilir.Yani başlatılır.
        scoreText=(TextView)findViewById(R.id.scoreText);
        timeText=(TextView)findViewById(R.id.timerText);
        imageView=findViewById(R.id.imageView);
        imageView2=findViewById(R.id.imageView2);
        imageView3=findViewById(R.id.imageView3);
        imageView4=findViewById(R.id.imageView4);
        imageView5=findViewById(R.id.imageView5);
        imageView6=findViewById(R.id.imageView6);
        imageView7=findViewById(R.id.imageView7);
        imageView8=findViewById(R.id.imageView8);
        imageView9=findViewById(R.id.imageView9);
        imageView10=findViewById(R.id.imageView10);
        imageView11=findViewById(R.id.imageView11);
        imageView12=findViewById(R.id.imageView12);
        imageView13=findViewById(R.id.imageView13);
        imageView14=findViewById(R.id.imageView14);
        imageView15=findViewById(R.id.imageView15);
        imageView16=findViewById(R.id.imageView16);


        imageArray= new ImageView[] {imageView,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9,imageView10,imageView11,imageView12,imageView13,imageView14,imageView15,imageView16};
        //meyveler oyun açıldığında kapalı olacak
        hideImages();


        //oyun açıldığı gibi zaman geriye saysın
        //millisn:saymaya kaçtan başlayayım(10000ms=10sn)
        //interval:kaç sn'de bir azalayım (1sn 1 azal)
        score = 0;
        new CountDownTimer(30000,1000) //30 snlik süre olacak ve her 1 saniyede bir azalacak
        {
            @Override
            public void onTick(long millisUntilFinished) {
                timeText.setText("Time: " + millisUntilFinished/1000); // her 1 saniyede bir azalacak

            }
            //Zaman bitince ne olacak
            @Override
            public void onFinish() {

                timeText.setText("Time off");
                //Süre bittiğinde oyunda biteceği için runnable'i durdur.(runnable görselleri açıyordu yani artık hepsi kapansın)
                handler.removeCallbacks(runnable);//runnable'i durdurduk.
                //Meyveleri  komple saklayalım
                for(ImageView image:imageArray)
                {
                    image.setVisibility(View.INVISIBLE);
                }
                //Baştan başlaması için uyarı yani alertdialog
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Restart Game ?");
                alert.setMessage("Are you sure to restart game?");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //restart olacak
                        Intent intent = getIntent();
                        finish(); //güncel aktivite biter aynı aktivite baştan başlayacak
                        startActivity(intent);

                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "Game Over", Toast.LENGTH_SHORT).show();
                    }
                });
                alert.show();

            }
        }.start();

    }

    public void increaseScoringOne(View view) //avocado 1 artsın
    {
        score++;
        scoreText.setText("Score : "+score);
    }
    public void increaseScoringTwo(View view) //muz:2 puan
    {
        score+=2;
        scoreText.setText("Score : "+score);
    }
    public void increaseScoringThree(View view) //çilek:2 puan
    {
        score+=3;
        scoreText.setText("Score : "+score);
    }


    public  void hideImages()
    {
        handler = new Handler();
        runnable=new Runnable() {
            @Override
            public void run() {
                //Önce hepsi kapalı yani gizli()invisible
                for(ImageView images: imageArray)
                {
                    images.setVisibility(View.INVISIBLE);
                }
                //rastgele açılacak
                Random random = new Random();
                int i=random.nextInt(16); //random olarak 0-15 arasaı değer üretir.16 adet elemanımız var yani Image
                imageArray[i].setVisibility(View.VISIBLE);//rastgele 16 imageden birini görünür yaptı

                handler.postDelayed(this,1000); //Runnable'ı rötarlı çalıştır.yani saniye de bir değil.Her saniye de bir değişik image açılsın
            }
        };
        handler.post(runnable); //runnable ktifleştirildi yai çalışmaya başlatıldı.


    }
}