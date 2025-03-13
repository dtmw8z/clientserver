# Real-Time Chat Application

This project is a real-time chat application consisting of a server and multiple clients. The server supports multiple client connections and broadcasts messages sent by one client to all other connected clients. The application is built using Java and demonstrates basic networking concepts such as sockets, multithreading, and client-server communication.

### Prerequisites

Before running the application, ensure you have the following installed:


    - Java Development Kit (JDK): Version 8 or higher.

    - IDE : IntelliJ IDEA or any text editor of your choice.

    - Terminal or Command Prompt: For running the server and client.

### How to Run the Application

Clone the Repository

```git clone https://github.com/your-username/real-time-chat-app.git```

`cd real-time-chat-app`

**Start the Server**

The server will start and listen for client connections on port 7007.

**Start the Client**

You can start multiple clients to simulate a real-time chat environment.
You will be prompted to enter your name. After entering your name, you can start sending messages.

To disconnect from the chat, type `exit`.

### Usage

Server

    The server listens on port 7007 by default.

    It accepts multiple client connections and broadcasts messages to all connected clients.

    The server logs client connections and disconnections to the console.

Client

    When the client starts, it prompts the user to enter their name.

    After entering the name, the client connects to the server.

    The client can send messages by typing in the console.

    To disconnect, type exit.

## Example

**Server Console**

```

Server started on port 7007
Accepted connection from /127.0.0.1
Client connected: Client 1
Client 1 has joined the chat!
Accepted connection from /127.0.0.1
Client connected: Client 2
Client 2 has joined the chat!
Client 1: Hi CLient 2
Client 2: Hi Client 1
Client 1: How are you, Client 2?
Client 2: I am good Client 1
```

***Client Console (Client 1)***

```

Enter your name: Client 1
Connected to server at localhost
Welcome to the chatroom, Client 1!
Client 2 has joined the chat!
Hi CLient 2
Client 2: Hi Client 1
How are you, Client 2?
Client 2: I am good Client 1
```

***Client Console (Client 2)***

```
Enter your name: Client 2
Connected to server at localhost
Welcome to the chatroom, Client 2!
Client 1: Hi Client 2
Hi Client 1
Client 1: How are you, Client 2?
I am good Client 1
```

Inspired by the classic 'Hello, World!' of network programming - an Echo server.

Built as part of a [coding challenge](https://codingchallenges.fyi/challenges/challenge-realtime-chat/) to learn about sockets and multithreading in Java.
