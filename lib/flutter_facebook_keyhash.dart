import 'dart:async';

import 'package:flutter/services.dart';

class FlutterFacebookKeyhash {
  static const MethodChannel _channel =
      MethodChannel('flutter_facebook_keyhash');

  static Future<String?> get getFaceBookKeyHash async {
    final String? version = await _channel.invokeMethod('getFaceBookKeyHash');
    return version;
  }
}
