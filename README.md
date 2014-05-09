# lift-ng-js

## Problem Statement

This little project aims to solve the following problems:

1. Make stuff more awesome.
2. Remove the less awesome stuff from your project.

## Configuration

Add the Sonatype.org Releases repo as a resolver in your `build.sbt` or `Build.scala` as appropriate.

```scala
resolvers += "Sonatype.org Releases" at "https://oss.sonatype.org/content/repositories/releases/"
```

Add **lift-ng-js** as a dependency in your `build.sbt` or `Build.scala` as appropriate.

```scala
libraryDependencies ++= Seq(
  // Other dependencies ...
  "net.liftmodules" %% "liftngjs" % "0.0.1" % "compile"
)
```

## Scala Versions

This project is compiled, tested, and published for the following Scala versions:

1. 2.9.1
2. 2.9.1-1
3. 2.9.2
4. 2.9.3
5. 2.10.3


## Usage

To use **lift-ng-js**, you should import it and call it...

## Scaladoc API

The Scaladoc API for this project can be found [here](http://barnesjd.github.io/lift-ng-js/latest/api).

## Examples

```scala
package org.example

import net.liftmodules.ng._

case object MyObject {
  // ...
}
```

## Wishlist

Below is a list of features we would like to one day include in this project

1. Support more awesome.
2. Decimate the not-awesome.

## License

*lift-ng-js* is licensed under [APL 2.0](http://www.apache.org/licenses/LICENSE-2.0).

Copyright 2013 net.liftmodules

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

