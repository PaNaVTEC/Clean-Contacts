# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /Users/panavtec/Documents/android-sdk-macosx/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
-dontwarn me.panavtec.**
-dontwarn com.squareup.javapoet.**

-keep,allowobfuscation @interface me.panavtec.presentation.common.DoNotStrip
-keep @me.panavtec.presentation.common.views.qualifiers.ThreadDecoratedView class *
-keep @me.panavtec.presentation.common.DoNotStrip class *

-keepnames class * extends me.panavtec.cleancontacts.presentation.Presenter

-keepclassmembers class * {
    @me.panavtec.presentation.common.DoNotStrip *;
}
-keepclassmembers class * {
    @javax.inject.Inject *;
}
-keepclassmembers class me.panavtec.presentation.common.views.ViewInjector {
    static <fields>;
}
