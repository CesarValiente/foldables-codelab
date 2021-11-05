package com.codelab.foldables.window_manager;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Consumer;
import androidx.window.java.layout.WindowInfoRepositoryCallbackAdapter;
import androidx.window.layout.WindowInfoRepository;
import androidx.window.layout.WindowLayoutInfo;
import androidx.window.layout.WindowMetrics;
import androidx.window.layout.WindowMetricsCalculator;

import com.codelab.foldables.window_manager.databinding.ActivityJavaBinding;

import java.util.concurrent.Executor;

public class JavaActivity extends AppCompatActivity {
    private String TAG = "CESAR";

    private ActivityJavaBinding binding;
    private WindowInfoRepositoryCallbackAdapter adapter;
    private Consumer<WindowLayoutInfo> consumerWindowLayoutInfo;

    private Executor runOnUiThreadExecutor = command -> {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(command);
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityJavaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        WindowInfoRepository windowInfoRepository = WindowInfoRepository.Companion.getOrCreate(this);
        adapter = new WindowInfoRepositoryCallbackAdapter(windowInfoRepository);

        showWindowMetrics();
        consumerWindowLayoutInfo = windowLayoutInfo -> showUI(windowLayoutInfo);
    }

    @Override
    protected void onStart() {
        super.onStart();

        adapter.addWindowLayoutInfoListener(runOnUiThreadExecutor, consumerWindowLayoutInfo);
    }

    @Override
    protected void onStop() {
        super.onStop();

        adapter.removeWindowLayoutInfoListener(consumerWindowLayoutInfo);
    }

    private void showUI(WindowLayoutInfo windowLayoutInfo) {
        showWindowMetrics();

        binding.text2.setText("Size: " + windowLayoutInfo.getDisplayFeatures().size() +
                "\nWindowLayoutInfo: " + windowLayoutInfo.getDisplayFeatures());
    }

    private void showWindowMetrics() {
        WindowMetricsCalculator windowMetricsCalculator = WindowMetricsCalculator.getOrCreate();
        WindowMetrics maxWindowMetrics = windowMetricsCalculator.computeMaximumWindowMetrics(this);
        WindowMetrics currentWindowMetrics = windowMetricsCalculator.computeCurrentWindowMetrics(this);

        binding.text1.setText(
                "Max Window metrics: " + maxWindowMetrics.getBounds().flattenToString() +
                        "\nCurrent Window Metrics: " + currentWindowMetrics.getBounds().flattenToString());
    }
}
