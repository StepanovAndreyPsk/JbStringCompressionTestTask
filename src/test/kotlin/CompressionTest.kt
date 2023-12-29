import kotlin.test.Test
import kotlin.test.assertEquals
import org.example.Compression
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalArgumentException

class CompressionTest {
    @Test
    fun sampleTestCompression() {
        val originalString = "AAAAABBB#####"
        val expectedCompressedString = "A±5B±3#±5"
        assertEquals(expectedCompressedString, Compression.compress(originalString))
    }

    @Test
    fun sampleTestDecompression() {
        val originalString = "AAAAABBB#####"
        val decompressedString = Compression.decompress(Compression.compress(originalString))
        assertEquals(originalString, decompressedString)
    }

    @Test
    fun testStringWithSeparatorCharCompression() {
        val originalString = "±±±±±±±±±±"
        val expectedCompressedString = "±±10"
        assertEquals(expectedCompressedString, Compression.compress(originalString))
    }

    @Test
    fun testStringWithSeparatorCharDecompression() {
        val originalString = "±±±±±±±±±±"
        val decompressedString = Compression.decompress(Compression.compress(originalString))
        assertEquals(originalString, decompressedString)
    }

    @Test
    fun testCompressionOfALargeString() {
        val originalString = "AAAAAAAAAAAAAAAAAAAABBBBBBBBBBBBBBBCCCCCCCCCC"
        val expectedCompressedString = "A±20B±15C±10"
        assertEquals(expectedCompressedString, Compression.compress(originalString))
    }

    @Test
    fun testDecompressionOfALargeString() {
        val originalString = "AAAAAAAAAAAAAAAAAAAABBBBBBBBBBBBBBBCCCCCCCCCC"
        assertEquals(originalString, Compression.decompress(Compression.compress(originalString)))
    }

    @Test
    fun testIncorrectCounterValueInDecompression() {
        val decompressionInput = "A±5B±-10C±6"
        assertThrows<IllegalArgumentException> { Compression.decompress(decompressionInput) }
    }

    @Test
    fun testIncorrectInputFormatInDecompression() {
        val decompressionInput = "AAA"
        assertThrows<IllegalArgumentException> { Compression.decompress(decompressionInput) }
    }

    @Test
    fun testPartlyIncorrectInputFormatInDecompression() {
        val decompressionInput = "BD±423±9"
        assertThrows<IllegalArgumentException> { Compression.decompress(decompressionInput) }
    }

    @Test
    fun testEmptyStringCompression() {
        val originalString = ""
        val expectedCompressedString = ""
        assertEquals(expectedCompressedString, Compression.compress(originalString))
    }

    @Test
    fun testEmptyStringDecompression() {
        val originalString = ""
        assertEquals(originalString, Compression.decompress(Compression.compress(originalString)))
    }
}