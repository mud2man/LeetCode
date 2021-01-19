/* Slide window: Time:O(26 * n), Space:O(26 * n)
 * 1. We can prove the maximum number of occurrences of a substring must owned by the one with length==minSize
 * 2. Scan string with minSize-long slide window to update max
 */

package main

import ("fmt"
        "math")

func maxFreq(s string, maxLetters int, minSize int, maxSize int) int {
    length := minSize
    letterCount := make([]int, 26, 26)
    uniqueCharCount := 0
    string2Count := make(map[string]int)
    for i := 0; i < length - 1; i++{
        letterCount[s[i] - 'a']++
        if(letterCount[s[i] - 'a'] == 1){
            uniqueCharCount++
        }
    }

    max := 0
    for i := length - 1; i < len(s); i++{
        letterCount[s[i] - 'a']++
        if(letterCount[s[i] - 'a'] == 1){
            uniqueCharCount++
        }
        if(i - length >= 0){
            deleteChar := s[i - length]
            letterCount[deleteChar - 'a']--
            if(letterCount[deleteChar - 'a'] == 0){
                uniqueCharCount--
            }
        }
        if(uniqueCharCount <= maxLetters){
            substring := s[i - length + 1: i + 1]
            count, isFound := string2Count[substring]
            if(!isFound){
                string2Count[substring] = 1
            }else{
                string2Count[substring] = count + 1
            }
            max = int(math.Max(float64(max), float64(string2Count[substring])))
        }
    }
    return max
}

func main() {
    s := "aababcaab"
    maxLetters := 2
    minSize := 3
    maxSize := 4
    fmt.Println("s:", s, "maxLetters:", maxLetters, "minSize:", minSize, "maxSize:", maxSize)
    fmt.Println("max number:", maxFreq(s, maxLetters, minSize, maxSize))
}
