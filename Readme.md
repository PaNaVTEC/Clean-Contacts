# CleanContacts [![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-CleanContacts-brightgreen.svg?style=flat)](http://android-arsenal.com/details/3/1649) [![Build Status](https://img.shields.io/travis/PaNaVTEC/Clean-Contacts.svg)](https://travis-ci.org/PaNaVTEC/Clean-Contacts) [![codecov.io](https://codecov.io/github/PaNaVTEC/Clean-Contacts/coverage.svg?branch=develop)](https://codecov.io/github/PaNaVTEC/Clean-Contacts?branch=develop)
Clean contacts is a sample project to illustrate Clean architecture in Android. It has also many other details that hopefully will be useful too.

## Talks
I gave a talk in Salamanca (Spain) about this implementation, here is the video: [Spanish](https://www.youtube.com/watch?v=Co8bJq_zbSQ&t=46m42s)

## Slides
[Spanish](http://es.slideshare.net/ChristianPanadero/my-way-to-clean-android-android-day-salamanca-edition), [English](http://es.slideshare.net/ChristianPanadero/my-way-to-clean-android-android-day-salamanca-edition-45930288)

I algo gave a 2nd version of the talk with the new features but unfortunately the video was not recorded or at lest not uploaded, anyway, the slides of that 2nd version are [here to download](http://es.slideshare.net/ChristianPanadero/my-way-to-clean-android-v2-english-droidcon-spain)

## How to start with this repository
The project is divided in 4 modules:

* App (Android): Contains UI, Dependency injection (Configuration) and implementation details of some figures like the Bus and Invoker.
* Presentation (Java): Contains presenters of (MVP) and the contract (View interface) to comunicate with.
* Domain (Java): Contains Interactors and bussines logic
* Data (Android): Data sources implementation (network, bdd, shared prefs...)
* **Bonus:** Desktop. Using the logic of this app and re-implementation details (App module and data module) I re-used code to create a JavaFX implementation of the first screen to show a contact list

## Things that you can find useful

### MVP
Model View presenter implemented in 2 modules. The Java module does not uses dependencies with the framework as R.string etc... 

### Navigation via ActionCommands
This is an idea of [Pedrovgs](https://github.com/pedrovgs/EffectiveAndroidUI), create small classes named "ActionCommands" that are used to navigate between activities, you can find this implementation in "DetailActionCommand"

### Use of abstractions
You can find some examples like ImageLoader or ErrorManager interfaces that are implemented by PicassoImageLoader and SnackBarErrorManager, this will allow to change the implementations in the future to use different ways to show images / show errors. Allways you can do it, use an Abstractions instead of a concrete implementation.

### Caching Strategy
In the BDD data source you can find a Caching Strategy (CS). The CS is a common logic to all data "CachingDataSources" and is a configuration of the data source so I added a constructor parameter with this collaborator to configure externally. Currently I added 2 implementations of CS, TTL (Time To Live) and NullSafe (just for check if is null) so the datasource requests data and checks if the objects are valid. If the objects are not valid the datasource throws a "InvalidCacheException" and repo handles the Exception and requests data to the next data source if needed.

### Material transitions and async image loading
In the contact list you, when you tap a contact you will navigate to Detail. In the contact list you have a thumbnail of the contact pic. When navigating to DetailActivity the image is a shared element that will move/scale to the Detail position that will later load a large picture.

### Coordinator to avoid flags
[Coordinator](http://panavtec.me/coordinator-as-a-library/) is a library that will help you to avoid flags, you will see in action on DetailActivity, it needs to coordinate the transition of the Main > Detail and the load from the BDD to show the contact.

### Dagger injection representing abstraction layers
You can find a dagger configuration that fits the abstraction layer in the app/di/ package 

### Assisted injection
This repo contains implementation for Android > 5 and < 5 (that just do nothing), For resolve this in Dagger I followed the tip of [Jesse Wilson](https://groups.google.com/forum/#!topic/dagger-discuss/QgnvmZ-dH9c/discussion) you can see the implementation in ActivityModule.

### No more buses to handle configuration changes
Reading the AOSP LoaderManager code gave me an idea of how to handle configuration cahnges without needing a bus. So I implemented the approach, you can see it [at this commit](https://github.com/PaNaVTEC/Clean-Contacts/commit/487b3db666df4db36df3ff667319958e4a6d70a5) and in [my blog there is a explanation](http://panavtec.me/clean-android-without-bus/). So now the interactor works with a tunned Callbacks to manage the returns threads.

### The interactor invoker is based on Futures 
The interactor invoker now uses Future and promises Java API and it has some advantages over the previous implementation. The invoker returns a Future<?> and you can cancel the the interactor and get the result of a Interactor synchronously.

### The UI thread is autommatically treated
The Presenter Base uses a "ViewInjector" wich is decorating the View interface with a Main thread return implementation using annotations at compile time, so every time you call "getView().XXXXX" in the child presenters they are using this decorated version and don't need to handle the return to UI thread by itselfs.

### Desktop app sharing common modules
I created a sample desktop app using JavaFX sharing presentation, domain/entities and localGateway modules. Just re-implementing the data sources and the UI module wich are implementation details. You can find the desktop app in the folder "desktop". If you are using IntelliJ and you want to run the sample, you will need this configuration:

![IntelliJ config](art/intellij_config.png)

Developed by
============
Carlos Morera de la Chica - <a href="https://twitter.com/CarlosMChica">@CarlosMChica</a>

Christian Panadero Martinez - <a href="http://panavtec.me">http://panavtec.me</a> - <a href="https://twitter.com/panavtec">@PaNaVTEC</a>

License
=======

    Copyright 2015 Christian Panadero Martinez

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
