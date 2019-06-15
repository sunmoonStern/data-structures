# create data for debugging
N = 100000
print(N)
parent = []
for i in range(N):
    if i == 0:
        parent.append(str(-1))
    else:
        parent.append(str(0))
print(' '.join(parent))