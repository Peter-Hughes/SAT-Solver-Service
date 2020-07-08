# SAT Solver Service 

Provides api's to solver CNF problems using satisfiablity solvers.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites
**You will need to have JDK >= 1.8 on your local development machine [JDK](https://tutorials.visualstudio.com/Java/hello-world/install-jdk)

**If you are running on windows you will need [cygwin32](https://www.cygwin.com/) installed on your computer with `gcc-core` and `gcc+` packages

### Installing

Install gradle dependencies 

Run project from `Main.kt` server will start on port `9000`

Swagger documentation on available routes can be found at `http://localhost:9000/api/api-docs` once the project has been started

### Running Tests

The tests can be found under `src/test/kotlin`, then tests can be run from the `build.gradle` file by clicking the green arrow next to test

## Built With

* [Kotlin](https://kotlinlang.org/) - The language used
* [Gradle](https://gradle.org/) - Dependency Management
* [HTTP4k](https://www.http4k.org/) - Used as a lightweight HTTP toolkit
* [Arrow](https://arrow-kt.io/) - Functional toolkit for kotlin
* [JUnit](https://junit.org/junit5/docs/current/user-guide/) - Testing framework

## Authors

* **Peter Hughes** 

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgements
* **Ivor Spence**
