print(100000)
for x in range(0,100000):
    left = 2 * x + 1
    right = 2 * x + 2
    if left >= 100000:
        left = -1
    if right >= 100000:
        right = -1
    print('{} {} {}'.format(x * 10000, left, right))