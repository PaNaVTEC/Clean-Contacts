#!/bin/sh
instrumentationResult=$(adb shell 'am instrument -w me.panavtec.cleancontacts.mock.test/me.panavtec.cleancontacts.CleanContactsRunner ; printf "$?"')
printf "$instrumentationResult\n"
exitCode=$(printf "$instrumentationResult" | tail -1)
if [ $exitCode != "0" ]; then
  exit 1
fi