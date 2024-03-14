
# Fixer API - Automated tests

Framework was designed and created to test endpoints that are part of the 'Fixer' subscription from ApiLayer.



## Installation

Install project with maven

```bash
  mvn package
```

## Navigating the framework

## Navigating the framework
All of the following packages start from `test/iceo.assignment`

- `/resources/features` - Location of the feature files.
- `/stepsdefs` - Java classes that interpret and run Gherkin from feature files.
- `/steps` - Smaller sub-steps that are called from stepdefs. Useful for reporting but also for creating more modular tests.
- `/util` - Location of helper classes.
- `/api` - API helper classes.


## Running Tests

To run tests, either execute `TestRunner.java` locally or run the following maven command

```bash
  mvn verify
```


## Environment Variables

This framework has 2 sets of properties


### serenity.properties
Here we can configure serenity and also add wider used variables such as environment

### resources/environments/*.properties
Property files for each type of environment, where we can store tokens and urls.

## Authors

- [@george.codreanu](https://www.github.com/GeorgeCodreanu)

