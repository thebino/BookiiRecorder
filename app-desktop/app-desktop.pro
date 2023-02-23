-dontshrink
-dontoptimize
-dontpreverify
-verbose

-dontwarn javax.management.**
-dontwarn java.lang.management.**
-dontwarn org.apache.log4j.**
-dontwarn org.apache.commons.logging.**
-dontwarn org.slf4j.**
-dontwarn org.json.**

-keep class org.** { *; }
