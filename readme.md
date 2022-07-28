# Serververse

Serververse is an echo server built in Java, which echoes back any text that is sent to it. It was created using JDK 17
and
Gradle 7.4.

## Table of Contents

- [Getting Started](#getting_started)
    - [Installation](#installation)
- [Usage](#usage)
    - [Buiding the Program](#building-the-program)
    - [Linting](#linting)
    - [Testing](#testing)
    - [Launching the Program](#launching-the-program)
    - [Making a Request](#making-a-request)
- [Future Implementation](#future-implementation)

## Getting Started

### Installation

Clone this repo from the terminal:

```
git clone https://github.com/karen-olson/serververse
```

## Usage

### Building the Program

From program directory:

```
./gradlew build
```

### Linting

This project uses IntelliJ's built-in formatting capabilities, and does not use a 3rd party linter.

### Testing

Run tests:

```
./gradlew test
```

### Launching the program

To run the program internally in IntelliJ, select the Run button or use ctrl + shift + R.

### Making a Request

There are many ways you can make a request to the server.

#### Request Using Netcat

To make requests using netcat, enter ``nc <url>`` on the command line.

## Future Implementation

- Create an HTTP server using this project as the starting point.
