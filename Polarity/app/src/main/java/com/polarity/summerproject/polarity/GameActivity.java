package com.polarity.summerproject.polarity;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.util.Timer;
import java.util.TimerTask;

public class GameActivity extends Activity {
    int FPS = 30;
    Game game;
    Screen screen;
    protected GameSurfaceView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screen = new Screen(size.y, size.x); // SCREEN

        game = new Game(screen); // GAME DECLARATION
        gameView = new GameSurfaceView(this, game);
        setContentView(gameView);

        // Get Touch Input
        gameView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent me){
                // DO THINGS WITH TOUCH EVENTS
                game.onTouch(me);
                return false;
            }
        });
    }

    @Override
    protected void onResume(){
        super.onResume();
        gameView.resume();
    }

    @Override
    protected void onPause(){
        super.onPause();
        gameView.pause();
    }
}
