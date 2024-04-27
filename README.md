# SUPER

Nobody asked for it, nobody needs it, here it is. SUPER is a simple TCP wrapper library and a protocol.

## Table of Contents
- [Usage](#usage)
- [Contributing](#contributing)

## Installation

Clone the repository:

   ```bash
   git clone https://github.com/tatuaua/SUPER.git
   ```

or download the .zip

## Running

TODO

## Usage

### Starting a SUPER server 

```java
SUPERServer server = new SUPERServer();
server.addEndpoint("/", new MyEndpoint());
server.open(5002);
```

This starts a server on port 5002 and includes a base endpoint.

### Definining a SUPER endpoint

```java
public class MyEndpoint implements SUPEREndpoint {
@Override
public SUPERResponse get() {
    SUPERResponse resp = new SUPERResponse();
    resp.build(2, "Hello from SUPERServer");
    return resp;
}

@Override
public SUPERResponse post(String requestBody) {
    return new SUPERResponse("");
}
```

All SUPER endpoints have to return a SUPER response.

### Making client-side requests to a SUPER server

```java
String hostname = "localhost";
int port = 5002;

SUPERClient client = new SUPERClient();

client.connect(hostname, port);

SUPERRequest req = new SUPERRequest();
req.build("/", 0, null);

SUPERResponse response = new SUPERResponse();

try {
    response = client.makeRequest(req);
} catch (IOException e) {
    e.printStackTrace();
}
```

A request is made using the makeRequest function of a SUPER client.

## Contributing

You are free to open an issue or a pull request on our GitHub
