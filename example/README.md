# sd_notify example application

* Demonstrates how to integrate with systemd'n notification facilities
  * An application will start over the period of 10 seconds
  * The ready state will only be communicated at the end of the startup to the service manager
* See the [Example.kt](src/main/kotlin/com/github/lemartin/sdservice/example/Example.kt) for details

## Running the application

_Please note that all command are run from directory of the root project._

* Build an operating system package


    ./gradlew example:buildDeb


* Install the deb


    sudo dpkg --install example/build/distributions/sd-service-example_1.0~SNAPSHOT_all.deb


* Start the application


    systemctl start --no-block sd-service-example


* Observe the status of the application using


    systemctl status sd-service-example

    
* Remove the deb


    dpkg -r sd-service-example