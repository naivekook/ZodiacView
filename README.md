ZodiacView
=================


[![Platform](https://img.shields.io/badge/platform-android-green.svg)](http://developer.android.com/index.html)
[![API](https://img.shields.io/badge/API-16%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=16)

This is an Android projects containing view with beautiful animation. Inspired by [Jordan Nelson](https://material.uplabs.com/posts/searching).

USAGE
-----

To start using ZodiacView in your project just follow next steps:

Step 1: add this block to your repositories
```groovy
maven {
	url 'https://dl.bintray.com/tanakovdev/maven';
}
```

Step 2: add this row to your dependencies
```groovy
compile 'dev.tanakov:zodiacview:1.0'
```

XML
-----

```xml
<com.dev.tanakov.zodiacview.ZodiacView
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>
```

You must use the following properties in your XML to change your ZodiacView.


#####Properties:

* `app:zv_color_relation`    (color)   -> default "#49348b"
* `app:zv_color_star`    (color)     -> default "#49348b"
* `app:zv_color_bg`    (color) -> default "#16151f"
* `app:zv_speed`          (float)   -> default 0.7f
* `app:zv_distance`    (int)     -> default 200
* `app:zv_star_count`   (int)     -> default 30
* `app:zv_star_size_max`   (int)     -> default 20
* `app:zv_star_size_min`   (int)     -> default 10
* `app:zv_relation_size`   (int)     -> default 5

JAVA
-----

```java
ZodiacView zodiacView = (ZodiacView)findViewById(R.id.yourZodiacView);
zodiacView.setColorBackground(Color.parseColor("#16151f"));
zodiacView.setColorRelation(Color.parseColor("#49348b"));
zodiacView.setColorStar(Color.parseColor("#49348b"));
zodiacView.setSpeed(0.7f);
zodiacView.setDistance(200);
zodiacView.setRelationSize(5);
zodiacView.setStarSizeMin(10);
zodiacView.setStarSizeMax(20);
zodiacView.setStarCount(30);
```


LICENCE
-----

ZodiacView by Vladimir Tanakov is licensed under a [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0).
