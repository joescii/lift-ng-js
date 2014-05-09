# lift-ng-js

## Problem Statement

This project is designed to allow Lift applications utilizing an AngularJS front-end to easily manage AngularJS modules and updates to those modules while properly handling browser caching concerns.

## Configuration

Add the Sonatype.org Releases repo as a resolver in your `build.sbt` or `Build.scala` as appropriate.

```scala
resolvers += "Sonatype.org Releases" at "https://oss.sonatype.org/content/repositories/releases/"
```

Add **lift-ng-js** as a dependency in your `build.sbt` or `Build.scala` as appropriate.

```scala
libraryDependencies ++= {
  val liftEdition = "2.5" // Also supported: "2.6" and "3.0"

  Seq(
    // Other dependencies ...
    "net.liftmodules" %% ("ng-js_"+liftEdition) % "1.2.16" % "compile"
  )
}
```

And invoke `AngularJS.init()` in your `Boot` class.

```scala
package bootstrap.liftweb

class Boot {
  def boot {
    // Other stuff...
    
    // Modules to be included by default.  angular.js is assumed.
    val modules = Seq("animate", "cookies", "csp", "loader", "resource", "route", "sanitize", "touch")
    net.liftmodules.ng.AngularJS.init(modules)
  }
}
```

## Supported Versions

**lift-ng-js** is built and released to support Lift editions 2.5 and 2.6 with Scala versions 2.9.1, 2.9.1-1, 2.9.2, and 2.10; and Lift edition 3.0 with Scala version 2.10.4.  This project's scala version is purposefully set at the lowest common denominator to ensure each version compiles.

## Usage

Simply add the AngularJS snippet wherever you want to add the configured Angular javascript modules.

```html
<script data-lift="AngularJS"></script>
```

Set the `modules` parameter to override the list of modules configured in `Boot`.

```html
<script data-lift="AngularJS?modules=animate,cookies,loader,route"></script>
```

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

