array = [None]*9
data = input("Enter 5-bit data word: ")
if len(data)!=5:
    print("Error: Enter valid data bits")
else:
    array[2] = int(data[0])
    array[4] = int(data[1])
    array[5] = int(data[2])
    array[6] = int(data[3])
    array[8] = int(data[4])
    
    def isEven(sum):
        if sum%2 ==0:
            return 0
        else: 
            return 1
    
    p1 = isEven(array[2] + array[4] + array[6] + array[8])
    p2 = isEven(array[2] + array[5] + array[6])
    p4 = isEven(array[4] + array[5] + array[6])
    p8 = isEven(array[8])
    
    array[0] = p1
    array[1] = p2
    array[3] = p4
    array[7] = p8
    
    result = ''.join(map(str,array))
    print("Hamming code: ",result)
    
received =input("Enter 9-bit Hamming data for correction: ")
if len(received)!=9:
    print("Error: Wrong data")
else:
    for i in range(9):
        array[i] = int(received[i])
        
    e1 = isEven(array[2] + array[4] + array[6] + array[8] + array[0])
    e2 = isEven(array[2] + array[5] + array[6] + array[1])
    e3 = isEven(array[4] + array[5] + array[6] + array[3])
    e4 = isEven(array[8] + array[7])
    
    databinary = f"{e4}{e3}{e2}{e1}"
    datadecimal = int(databinary,2)
    
    print("Array: ",array)
    print("Error Position(Binary): ",databinary)
    print("Error Position(Decimal): ",datadecimal)   
    
    if datadecimal !=0:
        error_index = datadecimal - 1
        array[error_index]= 1 - array[error_index]
        print(f"Error detected at {datadecimal} position. So Correcting it")
    else:
        print("No errors detected!!")
    
    result= ''.join(map(str,array))
    print(f"Corrected Data: {result}")