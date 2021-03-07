package com.example.swipertest.util

import android.content.Context
import android.content.res.AssetManager
import android.widget.Toast
import java.io.BufferedReader
import java.io.InputStreamReader

object AssetsUtil {

    /**
     * 讀取 Assets 檔案內的字串
     * @param path: Assets 裡的檔案路徑
     * @return String
     */
    fun readFileString(context: Context, path: String): String {
        val stringBuilder = StringBuilder()
        try {
            val assetManager: AssetManager = context.assets
            val inputStreamReader = InputStreamReader(assetManager.open(path))
            val bf = BufferedReader(inputStreamReader)
            var line: String?
            while (bf.readLine().also { line = it } != null) {
                stringBuilder.append(line)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, "讀取檔案失敗！", Toast.LENGTH_SHORT).show()
        }
        return stringBuilder.toString()
    }

}