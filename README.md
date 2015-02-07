Play With Scala.js Example
=================================

This is a barebones example that shows how to include Scala.js output into
an sbt-web project's asset pipeline. Whenever the play project is run or assets
are produced, fastOptJS is run and the output is put into the asset pipeline.

To try it out, use
```
activator
project sharedJVM
show web-assets:assets
```

However, I have not configured `play run` to detect changes to the Scala.js files yet.
