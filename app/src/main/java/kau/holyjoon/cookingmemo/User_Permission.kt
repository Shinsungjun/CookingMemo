package kau.holyjoon.cookingmemo

import android.content.Context
import android.widget.Toast
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission

class User_Permission (val context : Context) {
    var permission = 0
    val permissionListener : PermissionListener = object : PermissionListener {
        override fun onPermissionGranted() {
            permission = 1
        }

        override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
            permission = 0
           // Toast.makeText(context ,"Permission Denied", Toast.LENGTH_SHORT).show()
        }
    }
    fun checkPer() {
        TedPermission.with(context)
            .setPermissionListener(permissionListener)
            .setRationaleMessage("앱의 기능을 사용하기 위해선 권한이 필요합니다.")
            .setDeniedMessage("[설정] -> [권한] 에서 권한을 허용할 수 있습니다.")
            .setPermissions(android.Manifest.permission.CAMERA, android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                android.Manifest.permission.READ_EXTERNAL_STORAGE)
            .check()
    }
}