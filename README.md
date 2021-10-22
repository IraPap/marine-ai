# Application

This is a Java based web service that navigates an imaginary robotic cleaner through an oil spill in the sea.

## Context
* Sea area dimensions as [X and Y coordinates](https://en.wikipedia.org/wiki/Cartesian_coordinate_system), identifying the top right corner of the area rectangle.
  This area is divided up into a grid using these dimensions; an area that has dimensions `X: 5` and `Y: 5` has 5 columns and 5 rows, so 25 possible cleaner positions.
  The bottom left corner is the point of origin for our coordinate system, so the bottom left corner of the area is defined by `X: 0` and `Y: 0`.
* Locations of patches of oil, also defined by `X` and `Y` coordinates identifying the bottom left corner of those grid positions.
* The initial cleaner position (X and Y coordinates like patches of oil)
* Navigation instructions (as [cardinal directions](https://en.wikipedia.org/wiki/Cardinal_direction) where e.g. `N` and `E` mean "go north" and "go east" respectively)
* The tide does not impact this simulation - the patches of oil remain in the same place throughout the execution of the program.
* The area will be rectangular, has no obstacles and all locations in the area will be clean (cleaning has no effect) except for the locations of the patches of oil presented in the program input.
* Navigating the cleaner onto a patch of oil removes the oil so that patch is then clean for the remainder of the program run.
  The cleaner is always on - there is no need to enable it.

## Goal

The goal of the service is to take the area dimensions, the locations of the oil patches, the initial location of the cleaner and the navigation instructions as input and to then output the following:

* The final cleaner position (X, Y)
* The number of patches of oil the robot cleaned up

If any of the inputs are invalid or if the navigation instructions move the cleaner outside the area, the service will return a `Bad Request` response.

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