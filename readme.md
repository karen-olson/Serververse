# Serververse

Serververse is a server application built in Java using the [Chain of Responsibility](https://refactoring.guru/design-patterns/chain-of-responsibility) design pattern. It can be run as an Echo Server, which echoes any text that is sent to it, or an HTTP server, which processes HTTP requests. It was created using JDK 17 and Gradle 7.4.

## Table of Contents

- [Getting Started](#getting-started)
    - [Installation](#installation)
- [Usage](#usage)
    - [Buiding the Program](#building-the-program)
    - [Making Requests](#making-requests)
    - [Linting](#linting)
    - [Testing](#testing)
- [Future Implementation](#future-implementation)

## Getting Started

### Installation

Clone this repo:

```
git clone https://github.com/karen-olson/serververse
```

Ensure that Java 17 and Gradle 7 are installed.

## Usage

### Building the Program

```cd``` into the program directory and enter ```gradle build```

### Making Requests

#### Make Requests to the Echo Server Over TCP

To make requests locally, first start the server. If you're using IntelliJ, it should auto-detect the EchoServer configuration. Run the server by pressing the green "play" button in either the application configuration menu or on the sidebar in `HTTPServerMain.java`.

Next, open a terminal instance to use netcat as your TCP client for making requests. Connect to the Echo server by entering ``nc localhost 5000``. You'll know the server connection is complete when you see an empty line in the terminal. Then, enter text and see it echoed back on the next line.

To disconnect from the server, enter CTRL+C or CTRL+D in the terminal, and press the red "stop" button in IntelliJ.

### Linting

This project uses IntelliJ's built-in formatting capabilities, and does not use a 3rd party linter.

### Testing

To run tests, enter ```gradle test``` in the terminal.

## Future Implementation

- Extend the HTTP server's functionality to handle more complex requests while maintaining SOLID design.
