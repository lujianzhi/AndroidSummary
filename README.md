AndroidSummary
==============================================================
项目是对学到安卓知识的一些总结(根据网上的文章自己着手写代码进行学习)
--------------------------------------------------------------
###如何使用Bitmap
####1.获取手机的Destiny值
####2.使用bitmap加载手机drawable的图片
####3.从内存卡中读取图片，直接显示
####4.从内存卡中读取图片，经过压缩处理后显示
####5.使用inBitmap属性进行对Bitmap的复用
<br />
###安卓中几种常见的内存泄露情况
####1.Activity对象未被回收
#####1.静态变量引用Activity对象
#####2.静态View
#####3.内部类
#####4.匿名类
#####5.Handler
#####6.Threads和TimerTask
#####7.监听器
####2.集合对象造成的泄漏
####3.资源对象没关闭造成内存泄漏
####4.使用对象池避免频繁创建对象
<br />
<br />
<br />
<br />
This project includes my opinions about Android.And I do summary for these(coding by article on the net).
--------------------------------------------------------------
###How to use Bitmap
####1.get phone's Destiny
####2.load drawable resource by bitmap
####3.show sdcard's picture without any operation
####4.show sdcard's picture with photo compression
####5.use inBitmap to reuse Bitmap
<br />
###Common memory leaks situation in Android
####1.Activity not be recycled
#####1.static variables hold Activity
#####2.static View
#####3.inner class
#####4.anonymous class
#####5.Handler
#####6.Threads and TimerTask
#####7.listener
####2.Collcetion
####3.Resource not closed
####4.use object pool instead of new object frequently
