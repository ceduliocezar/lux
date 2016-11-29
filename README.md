[![CircleCI](https://circleci.com/gh/ceduliocezar/lux.svg?style=svg)](https://circleci.com/gh/ceduliocezar/lux)
# lux

Fully test Android Client for [The Movie DB Api](https://developers.themoviedb.org/3).

[Images](./pics/)

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

- Git.
- Android Studio.
- Screengrab.

### Building

Build all dev and production variants.

```
./gradlew assemble
```

Artifacts will be placed at:

```
./app/build/outputs/apk
```

## Tests

There are two types of tests, Local Unit Tests and Android Instrumented Tests.

### Local Unit Test

Easy and fast way to test classes that don't rely too much on Android Platform as presenters, repositories and other small classes.

```
./gradlew test
```

### Android Instrumented Tests

Tests for components related to Android Platform as Activities, Fragments and Services.

```
./gradlew connectedAndroidTest
```

## Built With

* [Retrofit 2](https://square.github.io/retrofit/) - A type-safe HTTP client for Android and Java
* [google-gson](https://github.com/google/gson) -  A Java serialization/deserialization library that can convert Java Objects into JSON and back. 
* [RxAndroid](https://github.com/ReactiveX/RxAndroid) -  RxJava bindings for Android 

## Contributing

Contributions are welcome, please submit a pull request.

## Versioning

We use [SemVer](http://semver.org/) for versioning.

## Authors

* **Cedulio Cezar** - *Initial work* - [Cedulio Cezar](https://github.com/ceduliocezar/)

See also the list of [contributors](https://github.com/ceduliocezar/lux/graphs/contributors) who participated in this project.

## License

```
The MIT License (MIT)

Copyright (c) 2016  Cedulio Cezar

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

```

## Acknowledgments

* [The Movie DB Api](https://developers.themoviedb.org/3)
* [Flaticon](https://github.com/ReactiveX/RxAndroid) -  The largest database of free icons available in PNG, SVG, EPS, PSD and BASE 64 formats.