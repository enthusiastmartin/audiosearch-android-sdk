
## Overview

As of November 2017: Audiosearch.fm no longer exists.

Unofficial Audiosearch Android SDK with RxJava support.

Audiosearch.ch is a one-stop search and recommendation engine for podcasts.

## Usage

```java
AudioSearch audioSearch = AudioSearch.Builder.create()
    .applicationID( YOUR_APP_ID )
    .secret( YOUR_APP_SECRET)
    .build();
```

To perform a search:
```java

    audiosearch.search("query string")
        .subscribeOn(Schedulers.newThread())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeWith( < your observer> );

```



## Installation

```groovy
compile 'studio.stressedout.audiosearch:audiosearch:0.5.3-alpha
```


