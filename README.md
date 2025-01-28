# SUPER

Nobody asked for it, nobody needs it, here it is. SUPER is a simple UDP wrapper library and a protocol.

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
server.addEndpoint("/", new Endpoint());
server.open(5002);
```

This starts a server on port 5002 and includes a base endpoint.

### Definining a SUPER endpoint

```java
public class Endpoint implements SUPEREndpoint {

    @Override
    public SUPERResponse get() {
        SUPERResponse resp = new SUPERResponse();
        resp.build(2, "Hello from SUPERServer");
        return resp;
    }

    @Override
    public SUPERResponse post(String requestBody) {
        SUPERResponse resp = new SUPERResponse();
        resp.build(3, "You cant post here!");
        return resp;
    }

}
```

All SUPER endpoints have to return a SUPER response.

### Making client-side requests to a SUPER server

Get request:

```java
SUPERClient client = new SUPERClient();
client.connect("localhost", 5002);
SUPERRequest req = new SUPERRequest();
req.build("/", 0, null);
SUPERResponse response = client.makeRequest(req);

System.out.println("Response: ");
System.out.println(response.getResponseBody());
```

Post request:

```java
SUPERClient client = new SUPERClient();
client.connect("localhost", 5002);
SUPERRequest req = new SUPERRequest();
req.build("/", 1, "Some body");
SUPERResponse response = client.makeRequest(req);

System.out.println("Response: ");
System.out.println(response.getResponseBody());
```

SUPER only supports get and post requests. Everything else is pointless.

SUPER request types:
- 0 == Get
- 1 == Post

SUPER response types:
- 2 == Success
- 3 == Failure


## Contributing

You are free to open an issue or a pull request on our GitHub
