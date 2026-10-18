#!/bin/sh

##############################################################################
#
#  Gradle start up script for POSIX systems
#
##############################################################################

# Attempt to set APP_HOME
PRG="$0"
while [ -h "$PRG" ] ; do
    ls=`ls -ld "$PRG"`
    link=`expr "$ls" : '.*-> \(.*\)$'`
    if expr "$link" : '/.*' > /dev/null; then
        PRG="$link"
    else
        PRG=`dirname "$PRG"`"/$link"
    fi
done
SAVED="`pwd`"
cd "`dirname \"$PRG\"`/" >/dev/null
APP_HOME="`pwd -P`"
cd "$SAVED" >/dev/null

CLASSPATH=$APP_HOME/gradle/wrapper/gradle-wrapper.jar

# Determine the Java command to use
if [ -n "$JAVA_HOME" ] ; then
    JAVACMD="$JAVA_HOME/bin/java"
elif [ -x "$APP_HOME/.gradle/local-toolchain/jdk-17/bin/java" ] ; then
    JAVA_HOME="$APP_HOME/.gradle/local-toolchain/jdk-17"
    GRADLE_USER_HOME="$APP_HOME/.gradle/user-home"
    ANDROID_USER_HOME="$APP_HOME/.gradle/user-home/.android"
    export JAVA_HOME GRADLE_USER_HOME ANDROID_USER_HOME
    JAVACMD="$JAVA_HOME/bin/java"
else
    JAVACMD="java"
fi

exec "$JAVACMD" -Xmx64m -Xms64m -classpath "$CLASSPATH" org.gradle.wrapper.GradleWrapperMain "$@"
