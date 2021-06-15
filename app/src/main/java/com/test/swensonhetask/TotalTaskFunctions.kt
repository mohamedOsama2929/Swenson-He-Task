package com.test.swensonhetask

import java.util.*


/**
 * I.
 * 3 1 3 9 = 12
 * ((3+1)/3)*9) = 12
 */

/**
 * II.
 * a function to determine whether two strings are anagrams or not
 */
fun areAnagram(str1: String, str2: String): Boolean {
    val s1 = str1.toCharArray()
    val s2 = str2.toCharArray()
    val n1 = s1.size
    val n2 = s2.size

    if (n1 != n2) return false

    Arrays.sort(s1)
    Arrays.sort(s2)

    for (i in 0 until n1) if (s1[i] != s2[i]) return false
    return true
}

/**
 * III.
 * a function to generate the nth Fibonacci number (1, 1, 2, 3, 5, 8, 13, 21, 34)
 * in recursive approach
 */
tailrec fun fibonacciRecursive(n: Int, a: Int = 0, b: Int = 1): Int =
    when (n) {
        0 -> a
        1 -> b
        else -> fibonacciRecursive(n - 1, b, a + b)
    }

/**
 * III.
 * a function to generate the nth Fibonacci number (1, 1, 2, 3, 5, 8, 13, 21, 34)
 * in iterative approach
 */
fun fibonacciIterative(n: Int) {
    var t1 = 0
    var t2 = 1

    for (i in 1..n) {
        val sum = t1 + t2
        t1 = t2
        t2 = sum
    }
}

