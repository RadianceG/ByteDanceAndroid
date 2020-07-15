package com.example.homework8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.PathUtils;


import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.hardware.camera2.CameraManager;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MediaRecorder mMediaRecorder;
    private Camera mCamera;
    private boolean isRecording;
    private SurfaceView mSurfaceView;
    private Button recordBu;
    private VideoView mVideoView;
    private SurfaceHolder mHolder;
    private String mp4Path;
    String[] permissions = new String[] {
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.CAMERA
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        initializePermissions();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mVideoView=(VideoView) findViewById(R.id.VideoV);
        recordBu=findViewById(R.id.recordBU);
        final Camera.PictureCallback mPictureCallback=new Camera.PictureCallback() {
            @Override
            public void onPictureTaken(byte[] bytes, Camera camera) {
                FileOutputStream fos=null;
                String filePath=getExternalFilesDir(Environment.DIRECTORY_PICTURES).getAbsolutePath()+ File.separator+"1.jpg";
                File file=new File(filePath);
                try{
                    fos = new FileOutputStream(file);
                    fos.write(bytes);
                    fos.flush();
                    Bitmap bitmap= BitmapFactory.decodeFile(filePath);
                }
                catch (Exception a)
                {
                    a.printStackTrace();
                }
                finally {
                    mCamera.startPreview();
                    if(fos!=null)
                        try{
                            fos.close();
                        }catch (IOException a)
                        {
                            a.printStackTrace();
                        }
                }
            }
        };

        recordBu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mVideoView.setVisibility(View.GONE);
                record(mSurfaceView);
            }
        });
        if (!ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.CAMERA))
        {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},0);
        }
        mSurfaceView=(SurfaceView)findViewById(R.id.surfaceV);
        mHolder=mSurfaceView.getHolder();
        initCamera();
        mHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
                try{
                    mCamera.setPreviewDisplay(surfaceHolder);
                    mCamera.startPreview();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            @Override
            public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {
                if (surfaceHolder.getSurface()==null)
                {
                    return;
                }
                mCamera.stopPreview();
                try{
                    mCamera.setPreviewDisplay(surfaceHolder);
                    mCamera.startPreview();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
                mCamera.stopPreview();
                mCamera.release();
                mCamera=null;
            }
        });

    }
    private boolean prepareVideoRecorder(){
        mMediaRecorder=new MediaRecorder();
        mCamera.unlock();
        mMediaRecorder.setCamera(mCamera);
        mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
        mMediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
        mMediaRecorder.setProfile(CamcorderProfile.get(CamcorderProfile.QUALITY_HIGH));
        mp4Path=getExternalFilesDir(Environment.DIRECTORY_MOVIES).getAbsolutePath()+ File.separator+"1.mp4";
        mMediaRecorder.setOutputFile(mp4Path);
        mMediaRecorder.setPreviewDisplay(mHolder.getSurface());
        mMediaRecorder.setOrientationHint(90);

        try{
            mMediaRecorder.prepare();
        }
        catch (Exception e)
        {
            mMediaRecorder.release();
            mMediaRecorder=null;
            return false;
        }
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        mCamera.stopPreview();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mCamera==null)
        {
            initCamera();
        }
        mCamera.startPreview();
    }

    private void initCamera(){
        mCamera=Camera.open();
        Camera.Parameters parameters=mCamera.getParameters();
        parameters.setPictureFormat(ImageFormat.JPEG);
        parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
        parameters.set("orientation","portrait");
        parameters.set("rotation",90);
        mCamera.setDisplayOrientation(90);
    }
    private void initializePermissions()
    {
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
            return;
        reuqestPermissions(MainActivity.this, permissions, 123);
    }
    public static void reuqestPermissions(Activity activity, String[] permissions, int requestCode) {
        if (permissions == null || android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return;
        }
        List<String> mPermissionList = new ArrayList<>();
        for (int i = 0; i < permissions.length; i++) {
            if (ContextCompat.checkSelfPermission(activity, permissions[i]) != PackageManager.PERMISSION_GRANTED) {
                mPermissionList.add(permissions[i]);//添加还未授予的权限
            }
        }
        if (mPermissionList.size() > 0) {//有权限没有通过，需要申请
            ActivityCompat.requestPermissions(activity, permissions, requestCode);
        }
    }
    public void record(View view){
        if(isRecording)
        {
            recordBu.setText("录制");
            mMediaRecorder.stop();
            mMediaRecorder.reset();
            mMediaRecorder.release();
            mMediaRecorder=null;
            mCamera.lock();
            mVideoView.setVisibility(View.VISIBLE);
            mVideoView.setVideoPath(mp4Path);
            mVideoView.start();
        }
        else
        {
            if(prepareVideoRecorder())
            {
                recordBu.setText("录制");
                mMediaRecorder.start();
            }
        }
        isRecording=!isRecording;
    }


}