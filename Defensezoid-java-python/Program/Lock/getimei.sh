adb shell "service call iphonesubinfo 1 | cut -c 52-66 | tr -d '.[:space:]'" && echo
