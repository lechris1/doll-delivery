class Graph(edges: List<Map<String, Any>>) {

    val locationMap : HashMap<String, Int> = hashMapOf() // map locations to numbers
    val idMap : HashMap<Int, String> = hashMapOf() // map ids to locations
    var vertexIndex = -1
    val numOfVertices = countVertices(edges)
    val matrix = Array(numOfVertices*numOfVertices) {0} // 1D matrix
    var isNonNaturalEdge = false // Don't allow for negative or zero edges

    // Initialize graph as adjacency matrix
    init {
        var row : Int
        var col : Int
        // Insert edges into adjacency matrix
        for (edge in edges) {
            row = locationMap[edge["startLocation"]] as Int
            col = locationMap[edge["endLocation"]] as Int

            // Insert edge into matrix
            matrix[getLocation(row, col)] = edge["distance"] as Int
            matrix[getLocation(col, row)] = edge["distance"] as Int

            if ((edge["distance"] as Int) <= 0) isNonNaturalEdge = true
        }
    }

    fun dijkstra(startLocation: String, endLocation: String) : Map<String, Any> {
        if (numOfVertices == 0 || isNonNaturalEdge || startLocation !in locationMap || endLocation !in locationMap) {
            return mapOf("distance" to 0, "path" to "")
        }

        var start = locationMap[startLocation] as Int
        var end = locationMap[endLocation]
        var path = arrayListOf(*(Array(numOfVertices) {-1})) // Shortest path
        var availableNodes = arrayListOf(*(Array(numOfVertices) {i -> i})) // Available nodes to add to path
        var distances = Array(numOfVertices) {Int.MAX_VALUE} // Distances from starting node
        distances[start] = 0

        // Start searching
        var nextShortest: Int
        while (availableNodes.isNotEmpty()) {
            nextShortest = findNextShortest(distances, availableNodes)

            if (nextShortest == end) {
                var pathString = getPath(path, distances, end)
                return mapOf("distance" to distances[end], "path" to pathString.substring(0, pathString.length-4))
            }

            availableNodes.remove(nextShortest)

            // Check if nextShortest is a dead end
            for (j in 0 until numOfVertices) {
                if (matrix[getLocation(nextShortest, j)] != 0 && j in availableNodes) {
                    // Check that this edge leads to a shorter path
                    if (matrix[getLocation(nextShortest, j)] + distances[nextShortest] < distances[j] ) {
                        distances[j] = matrix[getLocation(nextShortest, j)] + distances[nextShortest] // Update distance
                        path[j] = nextShortest // Next shortest is linked to previous last shortest
                    }
                }
            }
        }

        return mapOf("distance" to 0, "path" to "")
    }

    fun findNextShortest(distances: Array<Int>, availableNodes: ArrayList<Int>) : Int {
        var min = Int.MAX_VALUE
        var next = -1

        // Find shortest distance node that can be on the path
        for (i in 0 until numOfVertices) {
            if (distances[i] < min && i in availableNodes) {
                min = distances[i]
                next = i
            }
        }
        return next
    }

    fun getLocation(row: Int, col: Int) : Int = row * numOfVertices + col

    fun printAdjacencyMatrix() {
        for (i in 0 until numOfVertices) {
            var result : String = ""
            for (j in 0 until numOfVertices) {
                result += String.format("%" + "3" + "s", matrix[getLocation(i, j)].toString())
            }
            println(result)
        }
    }

    fun countVertices(edges: List<Map<String, Any>>) : Int {
        for (edge in edges) {
            // Map location string to integer
            if (edge["startLocation"] !in locationMap) {
                locationMap[edge["startLocation"] as String] = ++vertexIndex
                idMap[vertexIndex] = edge["startLocation"] as String
            }
            if (edge["endLocation"] !in locationMap) {
                locationMap[edge["endLocation"] as String] = ++vertexIndex
                idMap[vertexIndex] = edge["endLocation"] as String
            }
        }
        return vertexIndex+1
    }

    // Recursively finds path by piecing shortest previous paths
    fun getPath(path: ArrayList<Int>, distances: Array<Int>, end: Int) : String {
        if (path[end] == -1) {
            return idMap[end] + " => "
        }

        return getPath(path, distances, path[end]) + idMap[end] + " => "
    }

}