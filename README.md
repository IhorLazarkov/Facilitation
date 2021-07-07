
# Facilitation
Someday this project might grow in multiple-modules project. However, for there is only
one idea under development is **Lego Scanner**.

# Lego Scanner

It will open Lego.com and will store all propositions in **Sales** page into in-memory Apache.Derby database.
It should be run on daily basis just to collect historical data to see if there were any 
changed in prices or to see that propositions popped up.

The scanning part done via Selenium and Web App is done via Spring-Boot-Web

# How to build

> gradlew :lego-scanner:build

# How to launch the scanner

Navigate Run > Edit Configuration and select 'LegoScannerApp' configuration. 

# How to launch the Web App

Navigate Run > Edit Configuration and select 'LegoScannerWebApp' configuration. 