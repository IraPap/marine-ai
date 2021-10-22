## Input

Program input will be received in a JSON payload with the format described here.

`areaSize` is a list of two integers > 0

`startingPosition` is a list of two integers >= 0 within the bounds of the `areaSize`

`oilPatches` is a list of nested lists of two integers >= 0 within the bounds of the `areaSize`

`navigationInstructions` is a String containing a combination of the following characters: "N", "S", "E", "W"

Example:

```json
{
  "areaSize" : [5, 5],
  "startingPosition" : [1, 2],
  "oilPatches" : [
    [1, 0],
    [2, 2],
    [2, 3]
  ],
  "navigationInstructions" : "NNESEESWNWW"
}
```

## Output

Service output will be returned as JSON.

Example (matching the input above):

```json
{
  "finalPosition" : [1, 3],
  "oilPatchesCleaned" : 1
}
```
Where `finalPosition` is the final coordinates of the cleaner and `oilPatchesCleaned` is the number of cleaned patches.

## Building the Project

Maven is used as the build tool. Use the below command to build the project

```bash
mvn clean package
```

`Dockerfile` is included in the project so you can also build a Docker image with

```
docker build -t "scleaner:1.0.0" .
```

## Running the Project

You can run the program using Maven with the command 

```
mvn spring-boot:run
```

If you have built the Docker image above, you can run it with
```
docker run -d -p 8080:8080 -t scleaner:1.0.0
```
