import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:flutter_facebook_keyhash/flutter_facebook_keyhash.dart';

void main() {
  const MethodChannel channel = MethodChannel('flutter_facebook_keyhash');

  TestWidgetsFlutterBinding.ensureInitialized();

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
    expect(await FlutterFacebookKeyhash.platformVersion, '42');
  });
}
