import org.example.Compression

fun main() {
    // Example usage
    val original = "AAAAABBB#####"
    val compressed = Compression.compress(original)
    val decompressed = Compression.decompress(compressed)

    println("Original: $original")
    println("Compressed: $compressed")
    println("Decompressed: $decompressed")
}