package com.codelab.foldables.window_manager;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Consumer;
import androidx.window.java.layout.WindowInfoRepositoryCallbackAdapter;
import androidx.window.layout.WindowInfoRepository;
import androidx.window.layout.WindowLayoutInfo;
import androidx.window.layout.WindowMetrics;

import com.codelab.foldables.window_manager.databinding.ActivityJavaBinding;

import java.util.concurrent.Executor;

public class JavaActivity extends AppCompatActivity {
    private String TAG = "CESAR";

    private ActivityJavaBinding binding;
    private WindowInfoRepositoryCallbackAdapter adapter;
    private Consumer<WindowMetrics> consumerWindowMetrics;
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

        consumerWindowMetrics =
                windowMetrics -> binding.text1.setText("Window metrics: " + windowMetrics.getBounds().flattenToString());
        consumerWindowLayoutInfo = windowLayoutInfo -> showUI(windowLayoutInfo);
    }

    @Override
    protected void onStart() {
        super.onStart();

        adapter.addCurrentWindowMetricsListener(runOnUiThreadExecutor, consumerWindowMetrics);
        adapter.addWindowLayoutInfoListener(runOnUiThreadExecutor, consumerWindowLayoutInfo);
    }

    @Override
    protected void onStop() {
        super.onStop();

        adapter.removeCurrentWindowMetricsListener(consumerWindowMetrics);
        adapter.removeWindowLayoutInfoListener(consumerWindowLayoutInfo);
    }

    private void showUI(WindowLayoutInfo windowLayoutInfo) {
        binding.text1.setText("Size: " + windowLayoutInfo.getDisplayFeatures().size());
        binding.text2.setText("WindowLayoutInfo: " + windowLayoutInfo.getDisplayFeatures());
    }
}
