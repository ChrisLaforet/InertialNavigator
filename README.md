# InertialNavigator experiment

I have become fascinated with the way aircraft inertial navigation works and am experimenting with the logic and calculations behind it.  This codebase in Java is an attempt to wire together something to explore this logic and to play with calculations.

DISCLAIMER: THIS IS AN EXPERIMENTAL PROJECT AND IS NOT TO BE USED AS-IS FOR ANY REAL-WORLD NAVIGATION APPLICATIONS.  ANY USE OF THIS CODEBASE IS ENTIRELY AT YOUR OWN RISK.

# Testbed

The testbed for this is a Raspberry Pi 4 with 4G RAM and the X001G63URN (BerryGPS-IMU containing a BerryIMU v3 - see https://ozzmaker.com/berrygps-berrygps-imu-quick-start-guide/) from Ozzmaker.com which provides an IMU, GPS, and the 10D0F chip.  This exposes an accelerometer, gyroscope, and a barometric sensor in addition to a GPS receiver (of course).

The initial goals is to use a setup period to determine the motion of the Earth, determining direction, then using the GPS (or a manual entry) to seed the origin location.  The next goal will be to track changes in direction/accelerations and attempt to calculate resulting locations.  The GPS will be used to cross-check the results.  Needless to say, these motions need to be normalized to curvature of the Earth!  Subsequent goals will suggest themselves as the project moves forward.
