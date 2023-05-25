# error-prone-gradle-demo

> Error Prone is a static analysis tool for Java that catches common programming mistakes at compile-time

<div align="center">
  <picture>
    <img alt="Error Prone Support logo" src="https://error-prone.picnic.tech/assets/images/logo.svg" width="50%">
  </picture>
</div>

## Rationale

After watching a [presentation](https://www.youtube.com/watch?v=KPNimQMH0k4) showing off the possibilities of 
[Error Prone](https://errorprone.info/) I decided to give it a go and set up a little POC project reflecting
my current stack, namely Java 17 and Gradle 8. Common use cases implemented which are required for Real-World™
projects are the following:

* Enable or disable specific Error Prone checks (whitelist vs blacklist)
* Enable or disable specific Refaster rules (whitelist vs blacklist)
* Apply additional bug checks and Refaster rules from the [Error Prone Support](https://error-prone.picnic.tech/) project
* Apply custom Refaster rules defined inside the project
* Suppression of false positives
* Disable Error Prone in test code
* Multiple excluded paths
* Enable [patch mode](https://errorprone.info/docs/patching) by using a build flag (= Gradle property)

## Usage

```bash
./gradlew build [-PepAutoPatch]
```

## Links
* https://www.youtube.com/live/KPNimQMH0k4
* https://errorprone.info/
* https://error-prone.picnic.tech/
* https://github.com/tbroyer/gradle-errorprone-plugin

