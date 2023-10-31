# Name: Dev Patel
# COMP 3240 Spring 2023 Programming Assignment 6
'''
Factorizes the number passed into n into its prime factors and returns those
factors as a list.

n - integer to factor

Returns an array list containing the integer prime factors of n
'''


def trial_division(n: int) -> list:
    factors = []
    divisor = 2
    while n > 1:
        if n % divisor == 0:
            factors.append(divisor)
            n //= divisor
        else:
            divisor += 1
    return factors


# Pollard's rho algorithm: uses randomized method to find a non-trivial factor of the input number
# then recursively applies itself to the factors until they become prime
#
# returns non-trivial factor of n
def pollard_rho(n, c=1):
    x = y = 2
    d = 1
    while d == 1:
        x = (x*x + c) % n
        y = (y*y + c) % n
        y = (y*y + c) % n
        d = math.gcd(abs(x-y), n)
    if d == n:
        return



'''
This is the entry point for the program when you are running it on your
local machine. This function will not be executed when it is graded by
Gradescope.
'''
numToFactorize = 72
factors = trial_division(numToFactorize)
print(factors)
