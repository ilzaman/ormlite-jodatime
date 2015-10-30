# ormlite-jodatime

## What is it?

A set of [ORMLite](http://ormlite.com/) serialiser/deserialisers for dealing with 
[Joda Time](http://www.joda.org/joda-time/) entities. The following Joda Time classes are handled:

* `DateTimeZone`
* `Duration`
* `Period`
* `Instant`
* `DateTime`
* `MutableDateTime`
* `LocalDate`
* `LocalDateTime`
* `LocalTime`
* `Days`
* `Hours`
* `Minutes`
* `Months`
* `Seconds`
* `Weeks`
* `Years`

## Using it

````
OrmliteJodatimeUtils.registerTypes();
````
