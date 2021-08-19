ZodiacView
=================


[![Platform](https://img.shields.io/badge/platform-android-green.svg)](http://developer.android.com/index.html)
[![API](https://img.shields.io/badge/API-16%2B-blue)](https://developer.android.com/studio/releases/platforms#4.1)

This is an Android projects containing view with beautiful animation. Inspired by [Jordan Nelson](https://material.uplabs.com/posts/searching).

USAGE
-----

To start using ZodiacView in your project just follow next steps:

Step 1: add `mavenCentral()` to your repositories
Step 2: add `io.github.naivekook.zodiacview:zodiacview:1.1` to your dependencies

XML
-----

```xml
<io.github.naivekook.zodiacview.ZodiacView
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>
```

You must use the following properties in your XML to change your ZodiacView.


#####Properties:

* `app:zv_color_relation`       (color)     -> default "#49348b"
* `app:zv_color_star`           (color)     -> default "#49348b"
* `app:zv_color_bg`             (color)     -> default "#16151f"
* `app:zv_speed`                (float)     -> default 0.7f
* `app:zv_distance`             (int)       -> default 200
* `app:zv_star_count`           (int)       -> default 30
* `app:zv_star_size_max`        (int)       -> default 20
* `app:zv_star_size_min`        (int)       -> default 10
* `app:zv_relation_size`        (int)       -> default 5
* `app:zv_interaction_enabled`  (boolean)   -> default false

Kotlin
-----

```kotlin
val zodiacConfig = ZodiacConfig(
    starCount = 10,
    speed = 5f,
    colorStar = R.color.colorAccent,
    colorRelation = R.color.colorAccent,
    interactionEnabled = true
)
val zodiacView = ZodiacView.setup(this, zodiacConfig)
```


LICENCE
-----

ZodiacView by Vladimir Tanakov is licensed under a [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0).
