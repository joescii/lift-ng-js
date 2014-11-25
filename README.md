# lift-ng-js

This project is designed to allow Lift applications utilizing an AngularJS front-end to easily manage AngularJS modules.  This plugin allows you to update your Angular version in one location the same way you update your Scala and Java dependencies in your build file.  It also does the right thing by adding the version to the file names to properly handle browser caching concerns and serving the minified version in non-development modes.

## Configuration and usage

Add the Sonatype.org Releases repo as a resolver in your `build.sbt` or `Build.scala` as appropriate.

```scala
resolvers += "Sonatype.org Releases" at "https://oss.sonatype.org/content/repositories/releases/"
```

Add **lift-ng-js** as a dependency in your `build.sbt` or `Build.scala` as appropriate.

```scala
libraryDependencies ++= {
  val liftEdition = "2.5" // Also supported: "2.6" and "3.0"
  val ngVersion = "1.3.4"
  val liftNgJsVersion = "0.2"

  Seq(
    // Other dependencies ...
    "net.liftmodules" %% ("ng-js_"+liftEdition) % (liftNgJsVersion+"_"+ngVersion) % "compile"
  )
}
```

Invoke `AngularJS.init()` in your `Boot` class.

```scala
package bootstrap.liftweb

class Boot {
  def boot {
    // Other stuff...
    
    net.liftmodules.ng.AngularJS.init(
       // Modules to be included by default.  angular.js is assumed.
      "animate", "cookies", "loader", "resource", "route", "sanitize", "touch"
    )
  }
}
```

Simply add the AngularJS snippet wherever you want to add the configured Angular javascript modules.

```html
<script data-lift="AngularJS"></script>
```

Optionally set the `modules` parameter to override the list of modules configured in `Boot`.

```html
<script data-lift="AngularJS?modules=animate,cookies,loader,route"></script>
```

Optionally set the `min` parameter to force the minified js file to be served with `on`, `yes`, or `true`, OR to force the full js file to be served with `off`, `no`, or `false`.  Default behavior is to serve the minified version in all modes except `RunModes.Development`.

```html
<script data-lift="AngularJS?min=off"></script>
```


## Supported Scala Versions

**lift-ng-js** is built and released to support Lift edition 2.5 with Scala versions 2.9.1, 2.9.1-1, 2.9.2, and 2.10; Lift edition 2.6 with Scala versions 2.9.1, 2.9.1-1, 2.9.2, 2.10, 2.11; and Lift edition 3.0 with Scala version 2.10.  This project's scala version is purposefully set at the lowest common denominator to ensure each version compiles.

## Published Angular Versions
* 1.3.4
* 1.3.3
* 1.3.2
* 1.3.1
* 1.3.0
* 1.2.27
* 1.2.26
* 1.2.25
* 1.2.24
* 1.2.23
* 1.2.22
* 1.2.21
* 1.2.20
* 1.2.19
* 1.2.18
* 1.2.17
* 1.2.16

## Change log

* *0.2*: Minified js files now correctly reference the versioned `sourceMappingURL` file location.
* *0.1*: Initial release

## Wishlist

Eventually we'd like to get around to including the i18n resources based on the browser's preferred languages.

## License

*lift-ng-js* is licensed under [MIT](http://opensource.org/licenses/MIT).

   The MIT License (MIT)

    Copyright (c) 2014 net.liftmodules

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in
    all copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
    THE SOFTWARE.