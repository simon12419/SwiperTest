package com.example.swipertest.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.swipertest.R
import com.example.swipertest.ui.base.BaseActivity
import com.example.swipertest.util.GlideEngine
import com.luck.picture.lib.PictureSelector
import com.luck.picture.lib.config.PictureMimeType
import com.luck.picture.lib.entity.LocalMedia
import com.luck.picture.lib.listener.OnResultCallbackListener
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.FileNotFoundException

class MainActivity : BaseActivity<MainViewModel>(MainViewModel::class) {
    companion object {
        private const val TAG = "MainActivity"
    }

    private val navController by lazy { findNavController(R.id.main_container) }

    private val mSelectMediaListener = object : OnResultCallbackListener<LocalMedia> {
        override fun onResult(result: MutableList<LocalMedia>?) {
            try {
                // 图片选择结果回调
                // LocalMedia 里面返回三种path
                // 1.media.getPath(); 为原图path
                // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true
                // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true
                // 如果裁剪并压缩了，已取压缩路径为准，因为是先裁剪后压缩的

                val media = result?.firstOrNull() //這裡應當只會有一張圖片
                val path = when {
                    media?.isCompressed == true -> media.compressPath
                    media?.isCut == true -> media.cutPath
                    else -> media?.path
                }

                val file = File(path!!)
                if (file.exists()) {
                    //TODO 拍照後處理
                    Toast.makeText(this@MainActivity, "拍照成功", Toast.LENGTH_LONG)
                } else
                    throw FileNotFoundException()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        override fun onCancel() {
            Log.i(TAG, "PictureSelector Cancel")
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupBottomNav()
    }

    private fun setupBottomNav() {


        nav_phone.setOnClickListener {
            navController.navigate(R.id.phoneFragment)
            switchNavSelected(nav_phone)
        }

        nav_chat.setOnClickListener {
            navController.navigate(R.id.chatFragment)
            switchNavSelected(nav_chat)
        }

        nav_explore.setOnClickListener {
            navController.navigate(R.id.exploreFragment)
            switchNavSelected(nav_explore)
        }

        nav_wallet.setOnClickListener {
            navController.navigate(R.id.walletFragment)
            switchNavSelected(nav_wallet)
        }

        btn_camera.setOnClickListener {
            openCamera()
        }

        //default select wallet
        nav_wallet.isSelected = true
    }

    private fun switchNavSelected(view: View) {
        nav_phone.isSelected = nav_phone == view
        nav_chat.isSelected = nav_chat == view
        nav_explore.isSelected = nav_explore == view
        nav_wallet.isSelected = nav_wallet == view
    }

    private fun openCamera() {
        PictureSelector.create(this)
            .openCamera(PictureMimeType.ofImage())
            .imageEngine(GlideEngine.createGlideEngine())
            .isEnableCrop(true) // 是否裁剪 true or false
            .isCompress(true) // 是否压缩 true or false
            .rotateEnabled(true) // 裁剪是否可旋转图片 true or false
            .circleDimmedLayer(true) // 是否圆形裁剪 true or false
            .showCropFrame(false) // 是否显示裁剪矩形边框 圆形裁剪时建议设为false   true or false
            .showCropGrid(false) // 是否显示裁剪矩形网格 圆形裁剪时建议设为false    true or false
            .withAspectRatio(1, 1) // int 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
            .minimumCompressSize(100) // 小于100kb的图片不压缩
            .forResult(mSelectMediaListener)
    }
}
