# flutter_facebook_keyhash

# Flutter-FaceBook-KeyHash

Fedoff copy and pasting print key hash function in kotlin code to get <b>KeyHash</b>. Here is <b>Flutter Facebook Keyhash </b> Package thats help you genrating keyhash without going into kotlin code.


## Very simple to use
-Just copy and paste below code int statefull widget.<br>
<code>
<pre>
  @override
  void initState() {
    super.initState();
    printKeyHash();
  }

  void printKeyHash() async{

   String? key=await FlutterFacebookKeyhash.getFaceBookKeyHash ??
          'Unknown platform version';
   print(key??"");

  }


<pre>
</code>
