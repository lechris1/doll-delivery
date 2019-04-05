import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Disabled

internal class GraphTest {
    fun dijkstra(startLocation: String, endLocation: String, edges: List<Map<String, Any>>): Map<String, Any> {
        var graph = Graph(edges)
        return graph.dijkstra(startLocation, endLocation)
    }

    @Test
    fun givenTest() {
        var actual = dijkstra(
            startLocation = "Kruthika's abode",
            endLocation = "Craig's haunt",
            edges =
            listOf(
                mapOf("startLocation" to "Kruthika's abode", "endLocation" to "Mark's crib", "distance" to 9),
                mapOf("startLocation" to "Kruthika's abode", "endLocation" to "Greg's casa", "distance" to 4),
                mapOf("startLocation" to "Kruthika's abode", "endLocation" to "Matt's pad", "distance" to 18),
                mapOf("startLocation" to "Kruthika's abode", "endLocation" to "Brian's apartment", "distance" to 8),
                mapOf("startLocation" to "Brian's apartment", "endLocation" to "Wesley's condo", "distance" to 7),
                mapOf("startLocation" to "Brian's apartment", "endLocation" to "Cam's dwelling", "distance" to 17),
                mapOf("startLocation" to "Greg's casa", "endLocation" to "Cam's dwelling", "distance" to 13),
                mapOf("startLocation" to "Greg's casa", "endLocation" to "Mike's digs", "distance" to 19),
                mapOf("startLocation" to "Greg's casa", "endLocation" to "Matt's pad", "distance" to 14),
                mapOf("startLocation" to "Wesley's condo", "endLocation" to "Kirk's farm", "distance" to 10),
                mapOf("startLocation" to "Wesley's condo", "endLocation" to "Nathan's flat", "distance" to 11),
                mapOf("startLocation" to "Wesley's condo", "endLocation" to "Bryce's den", "distance" to 6),
                mapOf("startLocation" to "Matt's pad", "endLocation" to "Mark's crib", "distance" to 19),
                mapOf("startLocation" to "Matt's pad", "endLocation" to "Nathan's flat", "distance" to 15),
                mapOf("startLocation" to "Matt's pad", "endLocation" to "Craig's haunt", "distance" to 14),
                mapOf("startLocation" to "Mark's crib", "endLocation" to "Kirk's farm", "distance" to 9),
                mapOf("startLocation" to "Mark's crib", "endLocation" to "Nathan's flat", "distance" to 12),
                mapOf("startLocation" to "Bryce's den", "endLocation" to "Craig's haunt", "distance" to 10),
                mapOf("startLocation" to "Bryce's den", "endLocation" to "Mike's digs", "distance" to 9),
                mapOf("startLocation" to "Mike's digs", "endLocation" to "Cam's dwelling", "distance" to 20),
                mapOf("startLocation" to "Mike's digs", "endLocation" to "Nathan's flat", "distance" to 12),
                mapOf("startLocation" to "Cam's dwelling", "endLocation" to "Craig's haunt", "distance" to 18),
                mapOf("startLocation" to "Nathan's flat", "endLocation" to "Kirk's farm", "distance" to 3)
            ))

        var expected = mapOf("distance" to 31, "path" to "Kruthika's abode => Brian's apartment => Wesley's condo => Bryce's den => Craig's haunt")

        assertEquals(expected, actual, "Failed Given Test")
    }

    @Test
    fun G4GTests() {
        val edges = listOf(
            mapOf("startLocation" to "0", "endLocation" to "1", "distance" to 4),
            mapOf("startLocation" to "0", "endLocation" to "7", "distance" to 8),
            mapOf("startLocation" to "1", "endLocation" to "2", "distance" to 8),
            mapOf("startLocation" to "1", "endLocation" to "7", "distance" to 11),
            mapOf("startLocation" to "2", "endLocation" to "3", "distance" to 7),
            mapOf("startLocation" to "2", "endLocation" to "5", "distance" to 4),
            mapOf("startLocation" to "2", "endLocation" to "8", "distance" to 2),
            mapOf("startLocation" to "3", "endLocation" to "4", "distance" to 9),
            mapOf("startLocation" to "3", "endLocation" to "5", "distance" to 14),
            mapOf("startLocation" to "4", "endLocation" to "5", "distance" to 10),
            mapOf("startLocation" to "5", "endLocation" to "6", "distance" to 2),
            mapOf("startLocation" to "6", "endLocation" to "7", "distance" to 1),
            mapOf("startLocation" to "6", "endLocation" to "8", "distance" to 6),
            mapOf("startLocation" to "7", "endLocation" to "8", "distance" to 7)
        )

        var actual = dijkstra(
            startLocation = "0",
            endLocation = "8",
            edges = edges
            )

        var expected = mapOf("distance" to 14, "path" to "0 => 1 => 2 => 8")
        assertEquals(expected, actual, "Failed Test 0-8")

        actual = dijkstra(
            startLocation = "0",
            endLocation = "4",
            edges = edges
        )

        expected = mapOf("distance" to 21, "path" to "0 => 7 => 6 => 5 => 4")
        assertEquals(expected, actual, "Failed Test 0-4")

        actual = dijkstra(
            startLocation = "0",
            endLocation = "1",
            edges = edges
        )

        expected = mapOf("distance" to 4, "path" to "0 => 1")
        assertEquals(expected, actual, "Failed Test 0-1")
    }

    @Test
    fun emptyTest() {
        var actual = dijkstra(
            startLocation = "0",
            endLocation = "0",
            edges = listOf())

        var expected = mapOf("distance" to 0, "path" to "")
        assertEquals(expected, actual, "Result not empty")
    }

    @Test
    fun negativeEdgeTest() {
        var actual = dijkstra(
            startLocation = "Kruthika's abode",
            endLocation = "Craig's haunt",
            edges =
            listOf(
                mapOf("startLocation" to "Kruthika's abode", "endLocation" to "Mark's crib", "distance" to -9),
                mapOf("startLocation" to "Kruthika's abode", "endLocation" to "Greg's casa", "distance" to 4),
                mapOf("startLocation" to "Kruthika's abode", "endLocation" to "Matt's pad", "distance" to 18),
                mapOf("startLocation" to "Kruthika's abode", "endLocation" to "Brian's apartment", "distance" to 8),
                mapOf("startLocation" to "Brian's apartment", "endLocation" to "Wesley's condo", "distance" to 7),
                mapOf("startLocation" to "Brian's apartment", "endLocation" to "Cam's dwelling", "distance" to 17),
                mapOf("startLocation" to "Greg's casa", "endLocation" to "Cam's dwelling", "distance" to 13),
                mapOf("startLocation" to "Greg's casa", "endLocation" to "Mike's digs", "distance" to 19),
                mapOf("startLocation" to "Greg's casa", "endLocation" to "Matt's pad", "distance" to 14),
                mapOf("startLocation" to "Wesley's condo", "endLocation" to "Kirk's farm", "distance" to 10),
                mapOf("startLocation" to "Wesley's condo", "endLocation" to "Nathan's flat", "distance" to 11),
                mapOf("startLocation" to "Wesley's condo", "endLocation" to "Bryce's den", "distance" to 6),
                mapOf("startLocation" to "Matt's pad", "endLocation" to "Mark's crib", "distance" to 19),
                mapOf("startLocation" to "Matt's pad", "endLocation" to "Nathan's flat", "distance" to 15),
                mapOf("startLocation" to "Matt's pad", "endLocation" to "Craig's haunt", "distance" to 14),
                mapOf("startLocation" to "Mark's crib", "endLocation" to "Kirk's farm", "distance" to 9),
                mapOf("startLocation" to "Mark's crib", "endLocation" to "Nathan's flat", "distance" to 12),
                mapOf("startLocation" to "Bryce's den", "endLocation" to "Craig's haunt", "distance" to 10),
                mapOf("startLocation" to "Bryce's den", "endLocation" to "Mike's digs", "distance" to 9),
                mapOf("startLocation" to "Mike's digs", "endLocation" to "Cam's dwelling", "distance" to 20),
                mapOf("startLocation" to "Mike's digs", "endLocation" to "Nathan's flat", "distance" to 12),
                mapOf("startLocation" to "Cam's dwelling", "endLocation" to "Craig's haunt", "distance" to 18),
                mapOf("startLocation" to "Nathan's flat", "endLocation" to "Kirk's farm", "distance" to 3)
            ))

        var expected = mapOf("distance" to 0, "path" to "")

        assertEquals(expected, actual, "Negative Edges Not Allowed")
    }

    @Test
    fun oneEdgeTest() {
        var actual = dijkstra(
            startLocation = "Kruthika's abode",
            endLocation = "Mark's crib",
            edges =
            listOf(
                mapOf("startLocation" to "Kruthika's abode", "endLocation" to "Mark's crib", "distance" to 9)
            ))

        var expected = mapOf("distance" to 9, "path" to "Kruthika's abode => Mark's crib")

        assertEquals(expected, actual, "Negative Edges Not Allowed")
    }

    @Test
    fun nonExistentEdgeTest() {
        var actual = dijkstra(
            startLocation = "My's abode",
            endLocation = "Their's crib",
            edges =
            listOf(
                mapOf("startLocation" to "Kruthika's abode", "endLocation" to "Mark's crib", "distance" to 9)
            ))

        var expected = mapOf("distance" to 0, "path" to "")

        assertEquals(expected, actual, "No Such Edge")
    }

    @Test
    fun zeroWeightEdgeTest() {
        var actual = dijkstra(
            startLocation = "Kruthika's abode",
            endLocation = "Craig's haunt",
            edges =
            listOf(
                mapOf("startLocation" to "Kruthika's abode", "endLocation" to "Mark's crib", "distance" to 9),
                mapOf("startLocation" to "Kruthika's abode", "endLocation" to "Greg's casa", "distance" to 4),
                mapOf("startLocation" to "Kruthika's abode", "endLocation" to "Matt's pad", "distance" to 18),
                mapOf("startLocation" to "Kruthika's abode", "endLocation" to "Brian's apartment", "distance" to 8),
                mapOf("startLocation" to "Brian's apartment", "endLocation" to "Wesley's condo", "distance" to 7),
                mapOf("startLocation" to "Brian's apartment", "endLocation" to "Cam's dwelling", "distance" to 17),
                mapOf("startLocation" to "Greg's casa", "endLocation" to "Cam's dwelling", "distance" to 13),
                mapOf("startLocation" to "Greg's casa", "endLocation" to "Mike's digs", "distance" to 19),
                mapOf("startLocation" to "Greg's casa", "endLocation" to "Matt's pad", "distance" to 14),
                mapOf("startLocation" to "Wesley's condo", "endLocation" to "Kirk's farm", "distance" to 10),
                mapOf("startLocation" to "Wesley's condo", "endLocation" to "Nathan's flat", "distance" to 11),
                mapOf("startLocation" to "Wesley's condo", "endLocation" to "Bryce's den", "distance" to 6),
                mapOf("startLocation" to "Matt's pad", "endLocation" to "Mark's crib", "distance" to 19),
                mapOf("startLocation" to "Matt's pad", "endLocation" to "Nathan's flat", "distance" to 0),
                mapOf("startLocation" to "Matt's pad", "endLocation" to "Craig's haunt", "distance" to 14),
                mapOf("startLocation" to "Mark's crib", "endLocation" to "Kirk's farm", "distance" to 9),
                mapOf("startLocation" to "Mark's crib", "endLocation" to "Nathan's flat", "distance" to 12),
                mapOf("startLocation" to "Bryce's den", "endLocation" to "Craig's haunt", "distance" to 10),
                mapOf("startLocation" to "Bryce's den", "endLocation" to "Mike's digs", "distance" to 9),
                mapOf("startLocation" to "Mike's digs", "endLocation" to "Cam's dwelling", "distance" to 20),
                mapOf("startLocation" to "Mike's digs", "endLocation" to "Nathan's flat", "distance" to 12),
                mapOf("startLocation" to "Cam's dwelling", "endLocation" to "Craig's haunt", "distance" to 18),
                mapOf("startLocation" to "Nathan's flat", "endLocation" to "Kirk's farm", "distance" to 3)
            ))

        var expected = mapOf("distance" to 0, "path" to "")

        assertEquals(expected, actual, "Zero Edges Not Allowed")
    }
}