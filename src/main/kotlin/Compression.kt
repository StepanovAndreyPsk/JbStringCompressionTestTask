package org.example

class Compression {
    companion object {
        private const val SEPARATOR = "±"
        fun compress(input: String): String {
            if (input.isEmpty()) {
                return ""
            }

            val compressedValue = StringBuilder()
            var currentChar = input[0]
            var counter = 1

            for (charIndex in 1 until input.length) {
                val newChar = input[charIndex]
                if (newChar == currentChar) {
                    counter++
                } else {
                    compressedValue.append(currentChar).append(SEPARATOR).append(counter)
                    currentChar = newChar
                    counter = 1
                }
            }

            compressedValue.append(currentChar).append(SEPARATOR).append(counter)

            return compressedValue.toString()
        }

        fun decompress(input: String): String {
            if (input.isEmpty()) {
                return ""
            }
            val decompressedValue = StringBuilder()
            val regex = "(?<char>.)$SEPARATOR(?<counter>\\d*)".toRegex()
            var matchResult = regex.find(input)
            require(matchResult != null) { "Incorrect input format for decompression" }

            var matchedSize = 0

            while(matchResult != null) {
                val char = matchResult.groups["char"]?.value
                require(char?.length == 1)
                val counterStr = matchResult.groups["counter"]?.value
                require(char != null && counterStr != null) { "Incorrect compression counter value" }
                matchedSize += char.length + counterStr.length + SEPARATOR.length
                val counter = counterStr.toIntOrNull()
                require(counter != null) { "Incorrect counter value" }
                decompressedValue.append(char.repeat(counter))
                matchResult = matchResult.next()
            }

            require(matchedSize == input.length) { "Incorrect input format for decompression" }

            return decompressedValue.toString()
        }
    }
}