import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:flutter_facebook_keyhash/flutter_facebook_keyhash.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String _keyHash = 'Unknown';

  @override
  void initState() {
    super.initState();
    getKeyHash();
  }

  // Platform messages are asynchronous, so we initialize in an async method.
  Future<void> getKeyHash() async {
    String keyHash;
    // Platform messages may fail, so we use a try/catch PlatformException.
    // We also handle the message potentially returning null.
    try {
      keyHash = await FlutterFacebookKeyhash.getFaceBookKeyHash ??
          'Unknown platform KeyHash';
    } on PlatformException {
      keyHash = 'Failed to get Kay Hash.';
    }

    // If the widget was removed from the tree while the asynchronous platform
    // message was in flight, we want to discard the reply rather than calling
    // setState to update our non-existent appearance.
    if (!mounted) return;

    setState(() {
      _keyHash = keyHash;
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Center(
          child: Text('KayHash: $_keyHash\n'),
        ),
      ),
    );
  }
}
