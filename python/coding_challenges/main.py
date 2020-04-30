def get_min_does_not_appear_in_array(nums):

    try:
        marks = [False] * len(nums)
        
        for n in nums:
            marks[n-1] = True


        for i in range(len(marks)):
            if not marks[i]:
                return i+1

    except:
        return 1

    return 1

'''

A = [1, 3, 6, 4, 1, 2] of N = 7

marks = [false, false, false, false, false, false, false]

print(get_min_does_not_appear_in_array([1, 3, 6, 4, 1, 2]))

'''


def maximize_after_insert_of_five(n):
    num_str = str(n)

    if n >= 0:
        for i in range(len(num_str)):
            if num_str[i] > '5':
                continue
            else:
                return int(num_str[:i] + '5' + num_str[i:])

    else:

        for i in range(1, len(num_str)):
            if num_str[i] < '5':
                continue
            else:
                return int(num_str[:i] + '5' + num_str[i:])

        return int(num_str + '5')

    return -1

'''

test_nums = [268, 670, 0, -999, 8000, -8000, -1, 1]

for i in range(len(test_nums)):
    print(str(test_nums[i]) + ' -----> ' + str(maximize_after_insert_of_five(test_nums[i])))

'''



def process_binary_number(s):

    counter = 0
    
    while s != '' and int(s) != 0:
        counter += 1
        if s[-1] == '0':
            s = s[:-1]
        else:
            s = s[:-1] + '0'

    return counter


print(process_binary_number('011100'))
print(process_binary_number('1111010101111'))
print(process_binary_number('111'))
