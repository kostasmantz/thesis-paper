public static void sortPlacesForShortestPath(List<PlaceDto> places, PlaceDto startingPoint) {

    if (places == null || places.isEmpty()) {
        return;
    }
    List<PlaceDto> placeDtos = new ArrayList <>(places);
    List<PlaceDto> visitedPlaces = new LinkedList <>();
    
    double shortest = Integer.MAX_VALUE;
    PlaceDto shortestPlace = null;
    PlaceDto nextPlace = startingPoint;
    visitedPlaces.add(startingPoint);

    while (!placeDtos.isEmpty()) {
        for (PlaceDto placeDto: placeDtos) {
            double dist = LocationUtil.haversineDistanceInKm(nextPlace.getLatitude(), nextPlace.getLongitude(), placeDto.getLatitude(), placeDto.getLongitude());
            if (dist < shortest) {
                shortestPlace = placeDto;
                shortest = dist;
            }
        }
        placeDtos.remove(shortestPlace);
        visitedPlaces.add(shortestPlace);
        nextPlace = shortestPlace;
        shortest = Integer.MAX_VALUE;
    }

    places.clear();
    places.addAll(visitedPlaces);
}