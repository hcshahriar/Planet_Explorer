package com.shahriar.planetexplorer;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.facebook.shimmer.ShimmerFrameLayout;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DELAY = 3000; // 3 seconds
    private ShimmerFrameLayout shimmerViewContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        shimmerViewContainer = findViewById(R.id.shimmer_view_container);
        TextView tvTagline = findViewById(R.id.tv_tagline);

        // Start shimmer text animation
        if (shimmerViewContainer != null) {
            shimmerViewContainer.startShimmer();
        }

        // Smooth fade-in effect for tagline
        if (tvTagline != null) {
            tvTagline.setAlpha(0f);
            tvTagline.animate()
                    .alpha(0.7f)
                    .setDuration(1200)
                    .setStartDelay(500)
                    .start();
        }

        // Wait 3 seconds, then check login session
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            SharedPreferences pref = getSharedPreferences("UserSession", MODE_PRIVATE);
            boolean isLoggedIn = pref.getBoolean("isLoggedIn", false);

            Intent intent;
            if (isLoggedIn) {
                // User checked "Remember Me", bypass LoginScreen
                intent = new Intent(SplashActivity.this, MainActivity.class);
            } else {
                // Show LoginScreen normally
                intent = new Intent(SplashActivity.this, LoginActivity.class);
            }

            startActivity(intent);
            applyFadeAnimation();
            finish();
        }, SPLASH_DELAY);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (shimmerViewContainer != null) {
            shimmerViewContainer.startShimmer();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (shimmerViewContainer != null) {
            shimmerViewContainer.stopShimmer();
        }
    }

    private void applyFadeAnimation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            overrideActivityTransition(OVERRIDE_TRANSITION_OPEN, R.anim.fade_in, R.anim.fade_out);
        } else {
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }
    }
}