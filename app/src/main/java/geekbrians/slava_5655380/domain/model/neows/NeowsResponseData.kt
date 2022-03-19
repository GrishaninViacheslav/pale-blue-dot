package geekbrians.slava_5655380.domain.model.neows

data class NeowsResponseData(
    val links: Links,
    val element_count: Int,
    val near_earth_objects: Map<String, List<NeoData>>
)

data class Links(val next: String, val prev: String, val self: String)

data class NeoData(val name: String, val nasa_jpl_url: String)
