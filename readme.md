# Serververse

Serververse is an echo server built in Java, which echoes back any text that is sent to it. It was created using JDK 17
and
Gradle 7.4.

## Table of Contents

- [Getting Started](#getting-started)
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

Clone this repo:

```
git clone https://github.com/karen-olson/serververse
```

## Usage

### Building the Program

```cd``` into the program directory and enter ```gradle build```

### Launching the program

To run the program internally in IntelliJ, enter ```gradle run``` in the terminal.

### Linting

This project uses IntelliJ's built-in formatting capabilities, and does not use a 3rd party linter.

### Testing

To run tests, enter ```gradle test``` in the terminal.

### Ending the program

To stop the server, enter CTRL+C in the terminal.

### Making a Request

There are many ways you can make a request to the server.

#### Request Using Netcat

To make requests using netcat, enter ``nc <url>`` in the terminal.
To disconnect from the server when using netcat, enter CTRL+C or CTRL+D in the terminal.

## Future Implementation

- Create an HTTP server using this project as the starting point.
