package com.dotsbit.flutter_facebook_keyhash.flutter_facebook_keyhash

import android.app.Activity
import android.content.Context
import androidx.annotation.NonNull;
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.Registrar
import 	android.content.pm.PackageInfo;
import 	android.content.pm.PackageManager;
import java.lang.ref.WeakReference

import android.util.Base64
import android.util.Log
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
/** FlutterFacebookKeyhashPlugin */
class FlutterFacebookKeyhashPlugin: FlutterPlugin, MethodCallHandler, ActivityAware {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private lateinit var channel : MethodChannel
  private lateinit var context: Context
  private lateinit var activity: Activity
  private var activityReference = WeakReference<Activity>(null)
  private var contextReference = WeakReference<Context>(null)

  override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    channel = MethodChannel(flutterPluginBinding.binaryMessenger, "flutter_facebook_keyhash")
    channel.setMethodCallHandler(this)
    context = flutterPluginBinding.applicationContext

  }

  override fun onDetachedFromActivityForConfigChanges() {
    activityReference.clear()
  }

  override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {
    activityReference = WeakReference(binding.activity)
  }

  override fun onDetachedFromActivity() {
    activityReference.clear()
  }

  override fun onAttachedToActivity(binding: ActivityPluginBinding) {
    activity = binding.activity;
  }

  override fun onMethodCall(@NonNull call: MethodCall, @NonNull result: Result) {
    if (call.method == "getFaceBookKeyHash") {

      try {
        val info: PackageInfo = activity.getPackageManager().getPackageInfo(activity.getPackageName(), PackageManager.GET_SIGNATURES)
        for (signature in info.signatures) {
          val md = MessageDigest.getInstance("SHA")
          md.update(signature.toByteArray())
          val hashKey = String(Base64.encode(md.digest(), 0))

          result.success("Android ${hashKey}")

        }
      } catch (e: NoSuchAlgorithmException) {
        result.notImplemented()

      } catch (e: Exception) {
        result.notImplemented()

      }


    } else {
      result.notImplemented()
    }
  }



  override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
    channel.setMethodCallHandler(null)
  }
}
